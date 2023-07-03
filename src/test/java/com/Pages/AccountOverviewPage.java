package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage extends BasePage {
    private final By accountMessage = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to holds')]");
    private final By account = By.xpath("//table[@id='accountTable']//tbody//a");
    private final By accountDetailsTitle = By.xpath("//h1[@class='title' and text()='Account Details']");
    private final By selectActivityPeriodId = By.id("month");
    private final By selectTransactionTypeId = By.id("transactionType");
    private final By goButton = By.xpath("//input[@class='button' and @type='submit' and @value='Go']");
    public AccountOverviewPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }
    public String getAccountMessage() {
        return this.getText(accountMessage);
    }
    public void clickAccount() {
        click(account);
    }
    public String getAccountDetailsTitle() {
        return this.getText(accountDetailsTitle);
    }
    public void setSelectActivityPeriod(String option) throws InterruptedException {
        selectOption(option, selectActivityPeriodId);
    }
    public void setSelectTransactionType(String option) throws InterruptedException {
        selectOption(option, selectTransactionTypeId);
    }
    public void clickGoButton() throws InterruptedException {
        click(goButton);
    }
}
