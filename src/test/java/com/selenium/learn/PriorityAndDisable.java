package com.selenium.learn;

import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class PriorityAndDisable {

    @Test(priority = 30)
    void m1() {
        System.out.println("Method1");
    }

    @Test(priority = 35)
    void m2() {
        System.out.println("Method2");
    }

    @Test(priority = 55)
    void m3() {
        System.out.println("Method3");
    }

    @Test(priority = 88, enabled = false)
    void m4() {
        System.out.println("Method4");
    }

}
