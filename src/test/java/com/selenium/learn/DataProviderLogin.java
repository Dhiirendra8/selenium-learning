package com.selenium.learn;

import org.testng.annotations.DataProvider;

public class DataProviderLogin {

    @DataProvider(name = "LoginDataProvider")
    public Object[][] getdata()
    {
        Object[][] data ={{"abc@gmail.com","abc"},{"xyz@gmail.com","xyz"},{"mno@gmail.com","mno"}};
        return data;
    }
}
