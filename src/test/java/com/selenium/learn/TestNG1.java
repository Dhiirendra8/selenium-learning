package com.selenium.learn;


import org.testng.annotations.*;

public class TestNG1 {
    int a = 5;
    int b = 10;
    int result;

    @BeforeTest
    void beforetest() {
        System.out.println("Execute before test");
    }

    @AfterTest
    void aftertest() {
        System.out.println("Execute after test");
    }

    @BeforeClass
    void beforeclass() {
        System.out.println("Execute before class");
    }

    @AfterClass
    void afterclass() {
        System.out.println("Execute after class");
    }

    @BeforeMethod
    void beforemethod() {
        System.out.println("Execute before method");
    }

    @AfterMethod
    void aftermethod() {
        System.out.println("Execute after method");
        System.out.println("----------------------------");
    }

    @Test(priority = 1)
    public void add() {
        result = a + b;
        System.out.println("Add result : " + result);
    }

    @Test(priority = 2)
    public void sub() {
        result = a - b;
        System.out.println("Sub result : " + result);
    }

    @Test(priority = 3)
    public void mul() {
        result = a * b;
        System.out.println("Mul result : " + result);
    }
}
