package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage extends BasePage {
    private final By transferFundsTitle = By.xpath("(//h1[@class='title' and text()='Transfer Funds'])");
    private final By amountId = By.id("amount");
    private final By selectFromAccountId = By.id("fromAccountId");
    private final By selectToAccountId = By.id("toAccountId");
    private final By transferButton = By.xpath("//input[@class='button' and @type='submit' and @value='Transfer']");
    private final By successMessage = By.xpath("(//h1[@class='title' and text()='Transfer Complete!'])");
    public TransferFundsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public String getTransferFundsTitle() {
        return this.getText(transferFundsTitle);
    }

    public void writeAmount(String amount) throws InterruptedException {
        sendText(amount, amountId);
    }
    public void setSelectFromAccount(Integer index) throws InterruptedException {
        selectOptionByIndex(index, selectFromAccountId);
    }
    public void setSelectToAccount(Integer index) throws InterruptedException {
        selectOptionByIndex(index, selectToAccountId);
    }
    public void clickTransferButton() throws InterruptedException {
        click(transferButton);
    }

    public String getSuccessMessage() {
        return this.getText(successMessage);
    }
}
