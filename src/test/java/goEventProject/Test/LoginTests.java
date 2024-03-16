package goEventProject.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    WebDriver driver;
    String url = "https://goevent-platform.vercel.app/";
    @Test
    public void successfulUserLoginTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement loginBtn = driver.findElement(By.xpath("//a[@href='/sign-in']"));
        loginBtn.click();

        WebElement emailAddressOrUserInput = driver.findElement(By.id("identifier-field"));
        emailAddressOrUserInput.sendKeys("testing.goevent@gmail.com");

        WebElement continueToPasswordBtn = driver.findElement(By.xpath("//button[@data-localization-key='formButtonPrimary']"));
        continueToPasswordBtn.click();

        WebElement emailSetted = driver.findElement(By.xpath("//p[@class='cl-identityPreviewText \uD83D\uDD12\uFE0F cl-internal-1ptfnbv']"));
        Assert.assertEquals(emailSetted.getText(),"testing.goevent@gmail.com" );


        WebElement passwordInput = driver.findElement(By.id("password-field"));
        passwordInput.sendKeys("zZ.1121224421");

        WebElement continueToLoginBtn = driver.findElement(By.xpath("//button[@data-localization-key='formButtonPrimary']"));
        continueToLoginBtn.click();

        WebElement userAvatar = driver.findElement(By.xpath("//img[@title='Testing goEvent']"));
        Assert.assertTrue(userAvatar.isDisplayed());

        WebElement homeLink = driver.findElement(By.linkText("Home"));
        Assert.assertEquals(homeLink.getText(), "Home");

        WebElement createEventLink = driver.findElement(By.xpath("//a[@href='/events/create']"));
        Assert.assertEquals(createEventLink.getText(), "Create Event");

        WebElement myProfileLink = driver.findElement(By.xpath("//a[@href='/profile']"));
        Assert.assertEquals(myProfileLink.getText(), "My Profile");

        driver.close();
    }
}
