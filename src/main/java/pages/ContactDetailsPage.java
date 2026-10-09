
package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ContactDetailsPage extends BasePage {

    @AndroidFindBy(id = "com.android.contacts:id/action_bar_more")
    private WebElement moreOptionsButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"حذف جهة الاتصال\")")
    private WebElement deleteContactOption;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement confirmDeleteButton;

    public ContactDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Delete the contact")
    public void deleteContact() {
        click(moreOptionsButton);
        click(deleteContactOption);
        click(confirmDeleteButton);
    }

}
