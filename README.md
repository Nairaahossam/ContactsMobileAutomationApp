# Contacts Mobile Automation App

## Overview

This project is a Java-based mobile automation framework for testing contact creation in the built-in Android Contacts application. It uses Appium with UiAutomator2 and TestNG, follows the Page Object Model (POM), reads test data from Excel, and integrates with Allure for reporting.

## Technology Stack

- **Language:** Java 17
- **Mobile Automation:** Appium Java Client
- **Android Automation Driver:** UiAutomator2
- **Test Framework:** TestNG
- **Build Tool:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Test Data:** Excel (`.xlsx`) using Apache POI
- **Reporting:** Allure Reports
- **IDE:** IntelliJ IDEA

## Project Structure

```text
ContactsMobileAutomationApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BasePage.java
│   │   │   ├── factory/
│   │   │   │   └── DriverFactory.java
│   │   │   ├── pages/
│   │   │   │   ├── ContactsPage.java
│   │   │   │   ├── CreateNewContactPage.java
│   │   │   │   └── ContactDetailsPage.java
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java
│   │   │       └── ExcelReader.java
│   │   └── resources/
│   │       └── config.properties
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   └── tests/
│       │       └── CreateContactTest.java
│       └── resources/
│           └── testdata/
│               └── ContactsData.xlsx
├── allure-report/
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Automated Test Scenario

**Create a Contact with Valid Details**

The test data is loaded from the `Contacts` worksheet in `src/test/resources/testdata/ContactsData.xlsx`.

The test performs the following steps:

1. Starts an Appium session and opens the Android Contacts application.
2. Opens the form for creating a contact.
3. Enters the contact name, company, address, phone number, and email from Excel.
4. Saves the contact.
5. Verifies that the expected contact name is displayed on the contact details screen.
6. Deletes the contact created by the test.
7. Verifies that the contacts list screen is displayed again.

> Note: The current post-delete check verifies that the contacts list indicator is displayed. It does not independently verify that the created contact is absent from the list.

## Framework Design

- **DriverFactory:** Creates and manages the Appium Android driver, launches the Contacts activity, closes the app, and ends the driver session.
- **BaseTest:** Starts the driver before each test and closes the app/session afterward. It attaches a screenshot to Allure when a test fails.
- **BasePage:** Contains shared page functionality, including explicit waits, clicking elements, checking visibility, and initializing PageFactory elements.
- **ContactsPage:** Represents the contacts list screen and provides the add-contact action.
- **CreateNewContactPage:** Represents the contact creation form, enters contact data, saves the contact, and checks the displayed contact name.
- **ContactDetailsPage:** Represents the contact details screen and contains the contact deletion flow.
- **ConfigReader:** Reads settings from `config.properties`, allowing system properties to override values from the file.
- **ExcelReader:** Reads worksheet rows and converts them to `Object[][]` data for TestNG.
- **CreateContactTest:** Defines the test scenario, connects the Excel data provider, and performs assertions.
- **testng.xml:** Identifies the TestNG test suite and test class.

## Prerequisites

Install and configure the following:

- Java JDK 17
- Maven
- Node.js and npm
- Appium Server
- Appium UiAutomator2 driver
- Android SDK and Android Debug Bridge (`adb`)
- A connected Android device with USB debugging enabled
- Allure Commandline, if you want to generate and view the report locally

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Nairaahossam/ContactsMobileAutomationApp.git
cd ContactsMobileAutomationApp
```

### 2. Configure the Device and Appium Server

Update `src/main/resources/config.properties` for your environment:

```properties
appium.server.url=http://127.0.0.1:4723
platform.name=Android
automation.name=UiAutomator2
device.udid=YOUR_ANDROID_DEVICE_UDID
new.command.timeout.seconds=60
wait.timeout.seconds=10
app.package=com.android.contacts
app.activity=com.android.contacts.activities.PeopleActivity
```

Replace `YOUR_ANDROID_DEVICE_UDID` with the device identifier shown by:

```bash
adb devices
```

Make sure the Appium server URL, Android device, SDK configuration, and app package/activity are correct for your environment.

### 3. Start Appium

```bash
appium
```

Keep the Appium server running while the tests execute.

### 4. Download Maven Dependencies and Run Tests

Open the project in IntelliJ IDEA and reload the Maven project, or run:

```bash
mvn clean test
```

The Maven command runs the suite configured in `testng.xml`.

## Test Data

The Excel workbook is located at:

`src/test/resources/testdata/ContactsData.xlsx`

The worksheet name expected by the test is `Contacts`. It should contain these columns in this order:

| Column | Description |
|---|---|
| `name` | Contact name |
| `company` | Company |
| `address` | Address |
| `phone` | Phone number |
| `email` | Email address |

The first row contains column headers; subsequent non-empty rows are provided to TestNG. Keep phone numbers formatted as text in Excel so leading zeros are preserved.

## Allure Reporting

The Maven configuration writes Allure results to:

`target/allure-results`

After the test run, generate and open a report with Allure Commandline:

```bash
allure serve target/allure-results
```

Alternatively, the project includes an `allure-report/` directory generated by a previous report-generation run. Regenerate the report after new test executions if you want it to reflect the latest results.

## Notes

- The driver configuration uses Android Contacts package/activity values configured in `config.properties`.
- The test uses Excel data through a TestNG `@DataProvider`; contact values are not passed directly as hardcoded method arguments in the test.
- The current locators and contact-field ordering are based on the Android Contacts UI used during implementation and may need adjustment for a different device, Android version, language, or Contacts app variant.
- Ensure that the Excel file contains non-sensitive test data before publishing the repository publicly.

## Author

**Naira Hossam**

GitHub: [Nairaahossam](https://github.com/Nairaahossam)
