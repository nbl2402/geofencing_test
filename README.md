# geofencing_test demo

## Objective
- This repository allows user to run test case that build for geofencing mobile application. The framework is configured to connect with **Jenkins** and **Jira** to support **CI/CD**. Test cases are being run directly / via agent (_local system will be an agent_). In case test case fails, a bug ticket will be created in Jira.

---
## What I have done
- Build framework based on **Appium** + **Selenium** following **Page Object Model**
- Deploy Jenkins in Docker locally to run test cases
- Integrated scripts with Jira to automatically create bug ticket whenever test case fails

---
## How to run
1. Run from Jenkins
- Build Jenkins Server (_FreeStyle Job_) locally using DockerFile.
- Configure **Build Steps** as below
    ```
    mvn clean test \  
    -DJIRA_ENABLED=true \
    -DJIRA_BASE_URL=<jira-url> \
    -DJIRA_EMAIL=<jira-email> \
    -DJIRA_PROJECT_KEY=AUTO \
    -DJIRA_API_TOKEN=$JIRA_API_TOKEN
    ```
- Set the local system as an agent
- Build and Run the Job

2. Run locally via Maven  
- Execute following command on your local machine
     ```
        mvn clean test \  
        -DJIRA_ENABLED=true \
        -DJIRA_BASE_URL=<jira-url> \
        -DJIRA_EMAIL=<jira-email> \
        -DJIRA_PROJECT_KEY=AUTO \
        -DJIRA_API_TOKEN=$JIRA_API_TOKEN
     ```

---
## Test Cases  
### **_TC 01: Verify Google Map displays in Home Screen_**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 1: Launch geofencing app  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 2: Select Access Type option -> Only This Time  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Checked Point:** Validate Google Maps is shown  

    
### **_TC 02: Verify user is redirected to Direction screen_**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 1: Launch geofencing app  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 2: Select Access Type option - Only This Time  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 3: Tap on marker point  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 4: Tap on Direction button  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Checked Point:** Validate location fields display correctly

---
## Optimization:
- Decrease waiting time to minimize total test execution time
- Using CSV file to contain data test
- Include installation and deployment Android Emulator within Docker
- Capture Screenshot whenever test case **fails** or **is skipped**
    
