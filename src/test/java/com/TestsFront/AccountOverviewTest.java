package com.TestsFront;

import com.ExtentReports.ExtentFactory;
import com.Pages.AccountOverviewPage;
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

public class AccountOverviewTest {

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
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);
        accountOverviewPage.setup();
        accountOverviewPage.open("https://parabank.parasoft.com/parabank/index.htm");
        accountOverviewPage.login();
    }

    @Test
    @Tag("VISION_GENERAL_DE_LA_CUENTA")
    @Tag("ACTIVIDAD_DE_LA_CUENTA")
    public void testAccountOverview() throws InterruptedException {
        ExtentTest test = extent.createTest("Proceso de Visión General de Cuenta");
        test.log(Status.INFO, "Comienza el Test");

        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);

        accountOverviewPage.clickAccountOverview();
        test.log(Status.PASS, "Ingresar a la pagina de Visión General de Cuentas");

        assertEquals("*Balance includes deposits that may be subject to holds", accountOverviewPage.getAccountMessage());
        test.log(Status.PASS, "Validación mensaje visualizado");
    }

    @Test
    @Tag("ACTIVIDAD_DE_LA_CUENTA")
    public void testAccountActivity() throws InterruptedException {
        ExtentTest test = extent.createTest("Proceso de Actividad de Cuenta");
        test.log(Status.INFO, "Comienza el Test");

        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);

        accountOverviewPage.clickAccountOverview();
        test.log(Status.PASS, "Ingresar a la pagina de Visión General de Cuentas");

        assertEquals("*Balance includes deposits that may be subject to holds", accountOverviewPage.getAccountMessage());
        test.log(Status.PASS, "Validación mensaje visualizado");

        accountOverviewPage.clickAccount();
        test.log(Status.PASS, "Ingresar a la pagina de la primer cuenta visible");

        assertEquals("Account Details", accountOverviewPage.getAccountDetailsTitle());
        test.log(Status.PASS, "Validación acceso a detalle de cuenta");

        accountOverviewPage.setSelectActivityPeriod("All");
        accountOverviewPage.setSelectTransactionType("All");
        test.log(Status.PASS, "Completar periodo de actividad y tipo de transaccion");

        accountOverviewPage.clickGoButton();
        test.log(Status.PASS, "Hacer click en botón de Ir");

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
