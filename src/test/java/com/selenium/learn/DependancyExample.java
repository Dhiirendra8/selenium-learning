package com.selenium.learn;

import org.testng.annotations.Test;

public class DependancyExample {

    @Test(dependsOnMethods = {})
    void startcar() {
        System.out.println("car started");
    }

    @Test(dependsOnMethods = {"startcar"})
    void drivecar() {
        System.out.println("car drived");
    }

    @Test(dependsOnMethods = {"drivecar"})
    void stoptcar() {
        System.out.println("car stopped");
    }

    @Test(dependsOnMethods = {"drivecar", "stopcar"},alwaysRun = true)
    void parkcar() {
        System.out.println("car parked");
    }
}
