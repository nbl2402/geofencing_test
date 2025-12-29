package org.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DriverFactory {

    public static AndroidDriver createAndroidDriver()
    {
        String deviceName = getConnectedDevices().get(0);
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(deviceName)
                .setApp(System.getProperty("user.dir") + "/src/test/resources/app/app-debug.apk")
                .setAppPackage("com.eebax.geofencing")
                .setAppActivity(".MapsActivity")
                .setNoReset(false)
                .setAutoGrantPermissions(false);
        try {
            return new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while (true) {
                if (!((line = reader.readLine()) != null)) break;
                line = line.trim();
                if (line.isEmpty() || line.startsWith("List of devices")) continue;
                if (line.endsWith("device")) {
                    String deviceName = line.split("\\s+")[0];
                    devices.add(deviceName);
                }
            }
            return devices;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
