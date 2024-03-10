package com.selenium.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssertionExample {
    WebDriver driver;
    @BeforeTest
    void setup()
    {
        System.setProperty("webdriver.chrome.driver","C:/Namrata/workspace/driver/chromedriver-win64/chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver","C:/Namrata/workspace/driver/geckodriver-v0.34.0-win-aarch64/geckodriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.amazon.in");
//        driver.get("https://www.facebook.com");
    }

    @Test(priority = 1)
    void logoTest()
    {
        WebElement logo=driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logo.isDisplayed(),"Logo is not displayed");
    }

    @Test(priority = 2)
    void homePageTitle()
    {
        String title=driver.getTitle();
        //Assert.assertEquals("title","Amazon.in","Title is not matching");
        Assert.assertEquals(title,"facebook","Title is not matching");
    }

    @AfterClass
    void closeBrowser()
    {
        driver.quit();
    }
}
