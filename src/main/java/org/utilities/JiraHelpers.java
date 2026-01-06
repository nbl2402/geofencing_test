package org.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class JiraHelpers {

    public static void createBug(String summary, String description)
    {
        String token = System.getProperty("JIRA_API_TOKEN");

        System.out.println("Token null? " + (token == null));
        System.out.println("Token length = " + (token == null ? 0 : token.length()));

        Response response =
                RestAssured
                        .given()
                        .auth().preemptive()
                        .basic(
                                System.getProperty("JIRA_EMAIL"),
                                System.getProperty("JIRA_API_TOKEN")
                        )
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(buildPayload(summary, description))
                        .post(System.getProperty("JIRA_BASE_URL") + "/rest/api/3/issue");

        System.out.println("====== JIRA PAYLOAD ======");
        System.out.println(buildPayload(summary, description));
        System.out.println("====== JIRA RESPONSE ======");
        System.out.println("Status: " + response.statusCode());
        System.out.println(response.asPrettyString());
    }

    private static String buildPayload(String summary, String description)
    {
        String template = readJsonTemplate("jira/CreateBug.json");

        return template
                .replace("${PROJECT_KEY}", System.getProperty("JIRA_PROJECT_KEY"))
                .replace("${SUMMARY}", summary)
                .replace("${DESCRIPTION}", sanitize(description));
    }

    private static boolean isJiraEnabled() {
        return Boolean.parseBoolean(System.getProperty("JIRA_ENABLED", "false"));
    }

    public static void reportIfFailed(ITestResult result)
    {

        if (!isJiraEnabled()) return;

        if (result.getStatus() != ITestResult.FAILURE) return;
        Throwable error = result.getThrowable();
        if (error != null && error.getMessage().contains("timeout")) return;

        List<String> steps = LogHelpers.getSteps();

        String stepsText = steps.stream()
                .map(step -> "- " + step)  // thêm dấu `-` cho đẹp
                .collect(Collectors.joining("\n"));

        String summary = "[AUTO-TEST] " + result.getMethod().getMethodName() + " failed";

        String description = """
        **Test:** %s
        
        **Step Details**
        %s
        
        **Error**
        %s
        """.formatted(
                result.getMethod().getMethodName(),
                stepsText,
                error != null ? extractErrorMessage(error): "N/A"
        );

        JiraHelpers.createBug(summary, description);
    }

    private static String readJsonTemplate(String filePath) {
        try (InputStream is = JiraHelpers.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {

            if (is == null) {
                throw new RuntimeException("Cannot find file: " + filePath);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read json template", e);
        }
    }

    private static String sanitize(String text) {
        return text.replace("\n", "\\n");
    }

    private static String extractErrorMessage(Throwable e) {
        if (e == null) return "";

        String msg = e.getMessage() != null ? e.getMessage() : e.toString();

        if (e instanceof AssertionError) {
            return msg.split("\n")[0].trim();
        }

        return msg.split("\n")[0].trim();
    }
}
