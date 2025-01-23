# Reddif Money Automation Framework

## Project Overview
This project is a test automation framework designed to automate the Reddif Money website using Java, Selenium, and TestNG. The framework is built on Maven, leveraging its powerful dependency management and build capabilities. 

## Prerequisites
Before running the automation, ensure the following steps are completed:

1. **Create an account on the Reddif Money website**: Register on the site to obtain valid credentials for test execution.
2. **Update credentials**: Replace the default credentials in the `ManageSessionTest.java` file with your Reddif Money account details.

## How to Run the Framework

### Option 1: Using IDE
1. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
2. Locate the `Runner.java` file.
3. Right-click on the file and select **Run as Java Application**.

### Option 2: Using Command Line
1. Open a terminal/command prompt.
2. Navigate to the project's root directory.
3. Execute the following Maven command:
   ```sh
   mvn exec:java
   
## Framework Highlights

* **Dynamic Test Data Handling**: The framework reads test data dynamically from JSON files and Excel spreadsheets. This allows for flexible test scenarios without hardcoding data.

* **TestNG XML Configuration**: TestNG XML files are generated programmatically, eliminating the need for manual test suite updates.

## Maven Dependencies

The framework uses the following plugins and libraries:

* <kbd> Selenium </kbd>
* <kbd> testng </kbd>
* <kbd> commons-io </kbd>
* <kbd> extentreports </kbd>
* <kbd> json-simple </kbd>
* <kbd> poi </kbd>, <kbd> poi-ooxml </kbd>, <kbd> poi-ooxml-schemas </kbd>
* <kbd> dom4j </kbd>
* <kbd> xmlbeans </kbd>

Ensure all dependencies are correctly defined in the `pom.xml` file and downloaded by Maven.

## Reporting

The framework integrates with ExtentReports to generate comprehensive test execution reports. Reports are created after each test run and provide detailed insights into the test results.

## Additional Notes

* The framework is modular and scalable, allowing easy addition of new tests and features.
* If you encounter issues or need to debug, ensure the test-output/ folder contains the latest TestNG reports for troubleshooting.
