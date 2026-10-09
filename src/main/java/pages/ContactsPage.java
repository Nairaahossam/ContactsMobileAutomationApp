
package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ContactsPage extends BasePage {

    @AndroidFindBy(uiAutomator =
            "new UiSelector().resourceId(\"com.android.contacts:id/fab\")"
                    + ".childSelector(new UiSelector().resourceId(\"android:id/icon\"))")
    private WebElement addContactButton;

    public ContactsPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Tap the add contact button")
    public void tapAddContact() {
        click(addContactButton);
    }

    @Step("Verify the contacts list is displayed")
    public boolean isContactsListDisplayed() {
        return isDisplayed(addContactButton);
    }

}
