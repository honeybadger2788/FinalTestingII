package com.TestsFront;

import com.ExtentReports.ExtentFactory;
import com.Pages.AccountOverviewPage;
import com.Pages.NewAccountPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewAccountTest {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);
        newAccountPage.setup();
        newAccountPage.open("https://parabank.parasoft.com/parabank/index.htm");
        newAccountPage.login();
    }

    @Test
    public void testOpenNewAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Proceso de Apertura de Nueva Cuenta");
        test.log(Status.INFO, "Comienza el Test");

        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        NewAccountPage newAccountPage = new NewAccountPage(driver, wait);

        newAccountPage.clickOpenNewAccount();
        test.log(Status.PASS, "Ingresar a la pagina de Abrir Nueva Cuenta");

        newAccountPage.setSelectTypeAccount("SAVINGS");
        test.log(Status.PASS, "Selecciona tipo de cuenta SAVINGS");

        newAccountPage.clickOpenNewAccountButton();

        assertEquals("Congratulations, your account is now open.", newAccountPage.getSuccessMessage());
        test.log(Status.PASS, "Validación cuenta creada exitosamente");

        test.log(Status.INFO, "Finaliza el Test");
    }

    @AfterEach
    public void close() {
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);
        accountOverviewPage.closeBrowser();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
