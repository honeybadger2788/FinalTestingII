package com.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    private final By usernameTextField = By.name("username");
    private final By passwordTextField = By.name("password");
    private final By loginButton = By.xpath("//input[@class='button' and @type='submit' and @value='Log In']");
    protected By register = By.xpath("//a[text()='Register']");
    protected By openNewAccount = By.xpath("//a[text()='Open New Account']");
    protected By accountsOverview = By.xpath("//a[text()='Accounts Overview']");
    protected By transferFunds = By.xpath("//a[text()='Transfer Funds']");
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void setup() {
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        driver.quit();
    }

    protected WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void login() {
        String username = null;
        try(BufferedReader in = new BufferedReader(new FileReader("./target/username.txt"))){
            username = in.readLine();
        } catch (IOException ignored) {}

        sendText(username, usernameTextField);
        sendText("12345678", passwordTextField);
        click(loginButton);
    }
    protected void sendText(String inputText, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    protected void sendKey(CharSequence pressKeys, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(pressKeys);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.findElement(locator).getText();
    }

    public void clickRegister() throws InterruptedException {
        this.click(register);
    }
    public void clickOpenNewAccount() throws InterruptedException {
        this.click(openNewAccount);
    }
    public void selectOption(String option, By locator) throws InterruptedException {
        Select select = new Select(this.findElement(locator));
        select.selectByVisibleText(option);
    }
    public void selectOptionByIndex(Integer index , By locator) throws InterruptedException {
        Select select = new Select(this.findElement(locator));
        select.selectByIndex(index);
    }

    public void clickAccountOverview() throws InterruptedException {
        this.click(accountsOverview);
    }

    public void clickTransferFunds() throws InterruptedException {
        this.click(transferFunds);
    }
}
