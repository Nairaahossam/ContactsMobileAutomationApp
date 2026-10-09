
package pages;
import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CreateNewContactPage extends BasePage {

    @AndroidFindBy(className = "android.widget.EditText")
    private List<WebElement> inputFields;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement saveButton;

    @AndroidFindBy(id = "com.android.contacts:id/custom_title")
    private WebElement contactNameTitle;

    public CreateNewContactPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Enter contact details")
    public void enterContactDetails(String name, String company, String address, String phone, String email) {
        wait.until(ExpectedConditions.visibilityOfAllElements(inputFields));

        inputFields.get(0).sendKeys(name);
        inputFields.get(1).sendKeys(company);
        inputFields.get(2).sendKeys(address);
        inputFields.get(3).sendKeys(phone);
        inputFields.get(4).sendKeys(email);
    }

    @Step("Save the contact")
    public void saveContact() {
        click(saveButton);
    }

    @Step("Verify contact details are displayed")
    public boolean isContactDetailsDisplayed(String contactName) {
        return isDisplayed(contactNameTitle)
                && contactNameTitle.getText().equals(contactName);
    }

}
