package LocatorsAndFunctions.Advance;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UploadArchivo {
	WebDriver driver;
	String url = "https://demo.guru99.com/test/upload/";
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(url);
		
	}
	@Test
	public void uploadTest() {
		WebElement uploadInput = driver.findElement(By.id("uploadfile_0"));
		uploadInput.sendKeys("D:\\EducacionIT-70885\\ProyectoIntegrador\\src\\test\\resources\\inicioSesionValido.xlsx");
		
		WebElement submitButton = driver.findElement(By.id("submitbutton"));
		submitButton.click();
		
		Wait<WebDriver> waitSubmit = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement result = waitSubmit.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("res"))));

	    System.out.println(result.getText());
		
	}
	@AfterMethod
	public void finTest(ITestContext context) throws IOException, InvalidFormatException {
		driver.close();
		
	}
}
