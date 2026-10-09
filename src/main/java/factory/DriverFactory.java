
package factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.ConfigReader;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

public final class DriverFactory {

    private static final String CONTACTS_APP_PACKAGE =
            ConfigReader.get("app.package");
    private static final String CONTACTS_APP_ACTIVITY =
            ConfigReader.get("app.activity");

    private static AndroidDriver driver;

    private DriverFactory() {

    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void initializeDriver() throws Exception {
        if (driver != null) {
            return;
        }

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ConfigReader.get("platform.name"));
        options.setAutomationName(ConfigReader.get("automation.name"));
        options.setUdid(ConfigReader.get("device.udid"));

        options.setNoReset(true);
        options.setNewCommandTimeout(Duration.ofSeconds(
                ConfigReader.getInt("new.command.timeout.seconds")
        ));

        driver = new AndroidDriver(
                URI.create(ConfigReader.get("appium.server.url")).toURL(),
                options
        );
    }

    public static void launchContactsApp() {
        driver.executeScript("mobile: startActivity", Map.of(
                "component", CONTACTS_APP_PACKAGE + "/" + CONTACTS_APP_ACTIVITY,
                "stop", true
        ));
    }

    public static void closeContactsApp() {
        if (driver != null) {
            driver.terminateApp(CONTACTS_APP_PACKAGE);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
