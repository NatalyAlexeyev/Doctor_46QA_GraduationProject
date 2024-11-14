package com.phonebook;

import com.phonebook.pages.Contact;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        if (!app.getUserHelper().isLoginLinkPresent()){
            app.getUserHelper().logout();
        }
        app.getUserHelper().login("admin_admin_20242@gmail.com", "Password1@");
    }

    @Test
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test(dataProvider = "addContactString", dataProviderClass = DataProviders.class)
    public void addContactStringWithDataProviderTest(String name, String lastName, String phone, String email, String address, String desc){
        app.getContactHelper().clickAddLink();
        app.getContactHelper().fillInNewContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(desc));
        app.getContactHelper().clickSaveContactButton();
    }

    @Test(dataProvider = "addContactObject", dataProviderClass = DataProviders.class)
    public void addContactObjectWithDataProviderTest(Contact contact){
        app.getContactHelper().clickAddLink();
        app.getContactHelper().fillInNewContactForm(contact);
        app.getContactHelper().clickSaveContactButton();
    }

    @Test(dataProvider = "addContactFromCSV", dataProviderClass = DataProviders.class)
    public void addContactFromCSVWithDataProviderTest(Contact contact){
        app.getContactHelper().clickAddLink();
        app.getContactHelper().fillInNewContactForm(contact);
        app.getContactHelper().clickSaveContactButton();
    }

    @AfterMethod(enabled = false)
    public void postCondition(){
        app.getContactHelper().deleteOneContact();
    }
}
