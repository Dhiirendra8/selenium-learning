package com.selenium.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ParameterExample {
    WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "url"})
    void setup(String browser, String url) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Namrata/workspace/driver/chromedriver-win64/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:/Namrata/workspace/driver/geckodriver-v0.34.0-win32/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get(url);
    }

    @Test(priority = 1)
    void logoTest() {
        WebElement logo = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }

    @Test(priority = 2)
    void homePageTitle() {
        String title = driver.getTitle();
        //Assert.assertEquals("title","Amazon.in","Title is not matching");
        Assert.assertEquals(title, "facebook", "Title is not matching");
    }

    @AfterClass
    void closeBrowser() {
        driver.quit();
    }
}
