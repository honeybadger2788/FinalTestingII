package com.TestsFront;

import com.ExtentReports.ExtentFactory;
import com.Pages.TransferFundsPage;
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

public class TransferFundsTest {
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
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.setup();
        transferFundsPage.open("https://parabank.parasoft.com/parabank/index.htm");
        transferFundsPage.login();
    }

    @Test
    public void testTransferFunds() throws InterruptedException {
        ExtentTest test = extent.createTest("Proceso de Transferencia de Fondos");
        test.log(Status.INFO, "Comienza el Test");

        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);

        transferFundsPage.clickTransferFunds();
        test.log(Status.PASS, "Ingresar a la pagina de Transferencia de Fondos");

        Thread.sleep(2000); // para dar tiempo a que carguen las cuentas
        transferFundsPage.writeAmount("5000");
        transferFundsPage.setSelectFromAccount(0);
        transferFundsPage.setSelectToAccount(1);
        transferFundsPage.clickTransferButton();
        test.log(Status.PASS, "Completar los datos de Transferencia");

        assertEquals("Transfer Complete!", transferFundsPage.getSuccessMessage());
        test.log(Status.PASS, "Validación transferencia realizada exitosamente");

        test.log(Status.INFO, "Finaliza el Test");
    }

    @AfterEach
    public void close() {
        TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
        transferFundsPage.closeBrowser();
    }

    @AfterAll
    public static void report() {
        extent.flush();
    }
}
