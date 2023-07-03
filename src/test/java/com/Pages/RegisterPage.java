package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private final By firstNameId = By.id("customer.firstName");
    private final By lastNameId = By.id("customer.lastName");
    private final By addressId = By.id("customer.address.street");
    private final By cityId = By.id("customer.address.city");
    private final By stateId = By.id("customer.address.state");
    private final By zipCodeId = By.id("customer.address.zipCode");
    private final By phoneId = By.id("customer.phoneNumber");
    private final By ssnId = By.id("customer.ssn");
    private final By usernameId = By.id("customer.username");
    private final By passwordId = By.id("customer.password");
    private final By repeatedPasswordId = By.id("repeatedPassword");
    private final By registerButton = By.xpath("//input[@type='submit' and @class='button' and @value='Register']");
    private final By successMessage = By.xpath("(//p[contains(text(),'Your account was created successfully. You are now logged in.')])");
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void writeFirstName(String firstname) throws InterruptedException {
        sendText(firstname, firstNameId);
    }

    public void writeLastName(String lastname) throws InterruptedException {
        sendText(lastname, lastNameId);
    }

    public void writeAddress(String address) throws InterruptedException {
        sendText(address, addressId);
    }

    public void writeCity(String city) throws InterruptedException {
        sendText(city, cityId);
    }

    public void writeState(String state) throws InterruptedException {
        sendText(state, stateId);
    }

    public void writeZipCode(String zipCode) throws InterruptedException {
        sendText(zipCode, zipCodeId);
    }

    public void writePhone(String phone) throws InterruptedException {
        sendText(phone, phoneId);
    }

    public void writeSSN(String ssn) throws InterruptedException {
        sendText(ssn, ssnId);
    }

    public void writeUsername(String username) throws InterruptedException {
        sendText(username, usernameId);
    }

    public void writePassword(String clave) throws InterruptedException {
        sendText(clave, passwordId);
    }

    public void repeatPassword(String clave) throws InterruptedException {
        sendText(clave, repeatedPasswordId);
    }

    public void clickRegisterButton() throws InterruptedException {
        click(registerButton);
    }
    public String getSuccessMessage() {
        return this.getText(successMessage);
    }
}
