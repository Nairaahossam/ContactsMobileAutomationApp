package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public abstract class BasePage {

    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(
            ConfigReader.getInt("wait.timeout.seconds")
    );

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void click(WebElement element) {
        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        ).click();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(element)
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
