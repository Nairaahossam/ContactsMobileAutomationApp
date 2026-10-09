# Contacts Mobile Automation App

## Overview
This project is a mobile automation testing framework developed to automate the process of creating a new contact in the Android Contacts application.

The project uses Appium with Java and TestNG, following the Page Object Model (POM) design pattern to keep the test code organized, reusable, and maintainable.

## Tech Stack
- **Programming Language:** Java 17
- **Mobile Automation:** Appium
- **Automation Framework:** TestNG
- **Mobile Driver:** UiAutomator2
- **Build Tool:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Test Reporting:** Allure Reports
- **IDE:** IntelliJ IDEA

## Project Structure

```text
ContactsMobileAutomationApp/
├── src/
│   ├── main/java/
│   │   ├── factory/
│   │   │   └── DriverFactory.java
│   │   └── pages/
│   │       ├── ContactsPage.java
│   │       ├── CreateNewContactPage.java
│   │       └── ContactDetailsPage.java
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java
│       └── tests/
│           └── CreateContactTest.java
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Test Scenario

**Create a Contact with Valid Details**

The automated test performs the following steps:

1. Launches the Android Contacts application on a connected Android device.
2. Opens the Create Contact form.
3. Enters the contact's name, company, address, phone number, and email address.
4. Saves the contact.
5. Verifies that the contact details are displayed after saving.

## Prerequisites
Before running the tests, make sure you have:

- Java JDK 17 installed.
- Maven installed or configured through IntelliJ IDEA.
- Node.js and npm installed.
- Appium Server installed.
- Appium UiAutomator2 driver installed.
- Android SDK and ADB configured.
- An Android device connected with USB debugging enabled.

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/Nairaahossam/ContactsMobileAutomationApp.git
   ```

2. Open the project in IntelliJ IDEA.

3. Configure the Android SDK and connect your Android device.

4. Start the Appium server:

   ```bash
   appium
   ```

5. Verify that the device is connected:

   ```bash
   adb devices
   ```

6. Update the device capabilities in `DriverFactory.java` to match your device configuration.

7. Reload the Maven project to download the required dependencies.

## Running the Tests

You can run the test suite using the `testng.xml` file from IntelliJ IDEA.

Alternatively, if Maven is available in your terminal, run:

```bash
mvn clean test
```

## Allure Reporting

The project is configured with Allure TestNG integration.

After executing the tests, generate and open the Allure report using:

```bash
allure serve target/allure-results
```

The report provides a visual summary of test execution results.

## Design Approach

The framework follows the Page Object Model (POM):

- **DriverFactory:** Initializes and manages the Appium driver.
- **BaseTest:** Handles test setup and driver teardown.
- **ContactsPage:** Contains interactions with the contacts list screen.
- **CreateNewContactPage:** Contains interactions with the contact creation form.
- **ContactDetailsPage:** Represents the contact details screen.
- **CreateContactTest:** Implements the contact creation test scenario.

This separation improves maintainability, readability, and reusability.

## Author

**Naira Hossam**

GitHub: [Nairaahossam](https://github.com/Nairaahossam)
