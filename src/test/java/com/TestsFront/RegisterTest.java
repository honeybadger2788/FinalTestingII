package com.TestsFront;

import com.ExtentReports.ExtentFactory;
import com.Pages.RegisterPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
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
		wait = new WebDriverWait(driver, Duration.ofMillis(2000));
		RegisterPage registerPage = new RegisterPage(driver, wait);
		registerPage.setup();
		registerPage.open("https://parabank.parasoft.com/parabank/index.htm");
	}

	@Test
	@Tag("PROCESO_DE_REGISTRO")
	public void testRegisterProcess() throws InterruptedException {
		ExtentTest test = extent.createTest("Proceso de Registro Exitoso");
		test.log(Status.INFO, "Comienza el Test");

		RegisterPage registerPage = new RegisterPage(driver, wait);

		//Guarda el usuario registrado en un txt para recuperarlo en los otros test para el proceso de login
		String username = "noe"+System.currentTimeMillis();
		try (PrintWriter out = new PrintWriter("./target/username.txt")) {
			out.println(username);
		} catch (FileNotFoundException ignored){}

		registerPage.clickRegister();
		test.log(Status.PASS, "Ingresar a la pagina de Registro");

		registerPage.writeFirstName("Filmer");
		registerPage.writeLastName("Longdon");
		registerPage.writeAddress("001 Boyd Terrace");
		registerPage.writeCity("Tapon");
		registerPage.writeState("Porto");
		registerPage.writeZipCode("6035");
		registerPage.writePhone("493-491-2825");
		registerPage.writeSSN("424-46-6138");
		registerPage.writeUsername(username);
		registerPage.writePassword("12345678");
		registerPage.repeatPassword("12345678");
		test.log(Status.PASS, "Completar los datos de Registro");

		registerPage.clickRegisterButton();

		assertEquals("Your account was created successfully. You are now logged in.", registerPage.getSuccessMessage());
		test.log(Status.PASS, "Validación cuenta creada exitosamente");

		test.log(Status.INFO, "Finaliza el Test");
	}

	@AfterEach
	public void close() {
		RegisterPage registerPage = new RegisterPage(driver, wait);
		registerPage.closeBrowser();
	}

	@AfterAll
	public static void report() {
		extent.flush();
	}
}
