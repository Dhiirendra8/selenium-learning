package com.selenium.learn;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNG2 {

    @BeforeSuite
    void beforesuit() {
        System.out.println("Execute before suit");
    }

    @AfterSuite
    void aftersuit() {
        System.out.println("Execute after suit");
    }

    @Test(priority = 1)
    void login() {
        System.out.println("login test");
    }

    @Test(priority = 4)
    void close() {
        System.out.println("close test");
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    void page1() {
        System.out.println("page1 test");
    }

    @Test(priority = 3)
    void page2() {
        System.out.println("page2 test");
    }
}
