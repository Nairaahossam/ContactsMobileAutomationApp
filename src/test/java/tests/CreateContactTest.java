
package tests;

import base.BaseTest;
import factory.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactDetailsPage;
import pages.ContactsPage;
import pages.CreateNewContactPage;
import utils.ExcelReader;

@Epic("Contacts App")
@Feature("Contact Management")
public class CreateContactTest extends BaseTest {

    private static final String CONTACTS_DATA_FILE = "testdata/ContactsData.xlsx";
    private static final String CONTACTS_SHEET = "Contacts";

    @DataProvider(name = "contactsData")
    public Object[][] contactsData() {
        return ExcelReader.readSheet(CONTACTS_DATA_FILE, CONTACTS_SHEET);
    }

    @Test(
            dataProvider = "contactsData",
            description = "Create a new contact with valid details"
    )
    @Story("Create contact")
    @Severity(SeverityLevel.CRITICAL)
    @Description("creates a contact using data from the Excel sheet, " + "verifies it was saved, then deletes it.")
    public void createContactWithValidDetails(String contactName, String company, String address, String phone, String email) {
        ContactsPage contactsPage =
                new ContactsPage(DriverFactory.getDriver());

        contactsPage.tapAddContact();

        CreateNewContactPage createContactPage =
                new CreateNewContactPage(DriverFactory.getDriver());

        createContactPage.enterContactDetails(contactName, company, address, phone, email);

        createContactPage.saveContact();

        Assert.assertTrue(
                createContactPage.isContactDetailsDisplayed(contactName),
                "Contact details were not displayed after saving"
        );

        ContactDetailsPage contactDetailsPage =
                new ContactDetailsPage(DriverFactory.getDriver());

        contactDetailsPage.deleteContact();

        Assert.assertTrue(
                contactsPage.isContactsListDisplayed(),
                "Contacts list was not displayed after deleting the contact"
        );
    }


}
