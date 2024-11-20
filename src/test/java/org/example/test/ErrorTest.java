package org.example.test;

import codrea.testComponents.BaseTest;
import codrea.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorTest extends BaseTest {
    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void loginWithIncorrectCredentials(HashMap<String,String> data){
        landingPage.login(data.get("username"),data.get("password"));
        System.out.println(landingPage.getToastMessageText());
        Assert.assertEquals(landingPage.getToastMessageText(),"Incorrect email or password.");

    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data;
        try {
            List<HashMap<String,String>> hashMapList = getJsonDataToMap("src/test/resources/loginWithIncorrectCredentials.json");
            data = new Object[hashMapList.size()][];

            for (int i = 0; i <hashMapList.size() ; i++) {
                data[i] = new Object[] {hashMapList.get(i)};
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       return data;
    }
}
