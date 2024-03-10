package com.selenium.learn;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class DataProviderExample {

    @Test(dataProvider = "LoginDataProvider",dataProviderClass = DataProviderLogin.class)
    public void loginTest(String email,String pw)
    {
        System.out.println(email+", " +pw);

    }
}
