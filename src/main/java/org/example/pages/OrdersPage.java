package org.example.pages;

import codrea.utility.PageUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends PageUtility {

    @FindBy(css = "tbody td:nth-child(3)")
    private List<WebElement> orderList;
    public HeaderComponent headerComponent;
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver,this);
    }

    private List<WebElement> getAllOrders(){
        return orderList;
    }

    public boolean verifyProductDisplay(String productName){
       return orderList
                .stream()
                .anyMatch(order -> order.getText().equalsIgnoreCase(productName));

    }
}
