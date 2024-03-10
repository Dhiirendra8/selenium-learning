package Parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ParallelExecutionExample2 {

    WebDriver driver;

    @Test
    void loginTest()
    {
        System.setProperty("webdriver.chrome.driver","C:/Namrata/workspace/driver/chromedriver-win64/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://facebook.com");
        WebElement id = driver.findElement(By.id("email"));
        id.clear();
        id.sendKeys("Admin");
        WebElement pw=driver.findElement(By.id("pass"));
        pw.clear();
        pw.sendKeys("admin123");
        WebElement loginButton=driver.findElement(By.name("login"));
        loginButton.click();

    }
}
