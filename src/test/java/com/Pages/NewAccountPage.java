package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountPage extends BasePage {
    private final By selectTypeAccountId = By.id("type");

    private final By openNewAccountButton = By.xpath("//input[@value='Open New Account']");

    private final By successMessage = By.xpath("(//p[contains(text(),'Congratulations, your account is now open.')])");

    private final By newAccountId = By.id("newAccountId");
    public NewAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void setSelectTypeAccount(String option) throws InterruptedException {
        selectOption(option, selectTypeAccountId);
    }

    public void clickOpenNewAccountButton() throws InterruptedException {
        click(openNewAccountButton);
    }

    public String getSuccessMessage() {
        return this.getText(successMessage);
    }

    public String getNewAccountId() {
        return this.getText(newAccountId);
    }
}
