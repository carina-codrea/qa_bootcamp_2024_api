package org.example.test;

import codrea.pages.*;
import codrea.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainTest extends BaseTest {
    @Test
    public void login() throws InterruptedException {
        landingPage.login("selenium-tester@gmail.com","Pass123@");
        Assert.assertEquals(landingPage.getToastMessageText(),"Login Successfully");

    }

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String,String> data){
        ProductsPage productsPage = landingPage.login(data.get("username"),data.get("password"));
        Assert.assertEquals(landingPage.getToastMessageText(),"Login Successfully");

        productsPage.addProductToCart(data.get("product"));
        Assert.assertEquals(productsPage.getToastMessageText(),"Product Added To Cart");
        CartPage cartPage = productsPage.headerComponent.goToCart();

        Assert.assertTrue(cartPage.isProductInCart(data.get("product")));
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        ConfirmationPage confirmationPage = checkoutPage.placeOrder(data.get("country"));

        Assert.assertEquals(confirmationPage.getConfirmationMessage(),"THANKYOU FOR THE ORDER.");

    }

    @Test(dependsOnMethods = "submitOrder")
    public void verifyProductDisplay(){
        ProductsPage productsPage = landingPage.login("selenium-tester@gmail.com","Pass123@");
        OrdersPage ordersPage = productsPage.headerComponent.goToOrders();
        Assert.assertTrue(ordersPage.verifyProductDisplay("ZARA COAT 3"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
      List<HashMap<String,String>> hashMapList = getJsonDataToMap("src/test/resources/submitOrder.json");
      Object[][] data = new Object[hashMapList.size()][];

        for (int i = 0; i <hashMapList.size() ; i++) {
            data[i] = new Object[] {hashMapList.get(i)};
        }

        return data;
    }


}
