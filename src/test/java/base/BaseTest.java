
package base;

import factory.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

public class BaseTest {

    @BeforeMethod
    public void startDriverAndLaunchApp() throws Exception {
        DriverFactory.initializeDriver();
        DriverFactory.launchContactsApp();
    }

    @AfterMethod(alwaysRun = true)
    public void closeAppAndQuitDriver(ITestResult result) {
        try {
            if (!result.isSuccess() && DriverFactory.getDriver() != null) {
                Allure.addAttachment(
                        "Screenshot on failure",
                        "image/png",
                        new ByteArrayInputStream(
                                DriverFactory.getDriver().getScreenshotAs(OutputType.BYTES)
                        ),
                        "png"
                );
            }

            DriverFactory.closeContactsApp();
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
