package Parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParallelExecutionExample {

    WebDriver driver;

    @Test
    void logoTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/Namrata/workspace/driver/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in");
        WebElement logo=driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logo.isDisplayed(),"Logo is not displayed");
        Thread.sleep(5000);
    }

    @Test
    void homePageTitle() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/Namrata/workspace/driver/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
        Thread.sleep(5000);
    }

    @AfterClass
    void closeBrowser()
    {
        driver.quit();
    }

}
