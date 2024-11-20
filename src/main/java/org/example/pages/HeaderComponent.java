package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {

    private final WebDriver driver;
    @FindBy(css = "button[routerlink*='dashboard']")
    private WebElement homeHeader;
    @FindBy(css = "button[routerlink*='orders']")
    private WebElement ordersHeader;
    @FindBy(css = "button[routerlink*='cart']")
    private WebElement cartHeader;
    @FindBy(xpath = "//button[text()=' Sign Out ']")
    private WebElement signOutHeader;

    public HeaderComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public LandingPage signOut(){
        signOutHeader.click();
        return new LandingPage(driver);
    }

    public CartPage goToCart(){
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrders(){
        ordersHeader.click();
        return new OrdersPage(driver);
    }

    public ProductsPage goToHome(){
        homeHeader.click();
        return new ProductsPage(driver);
    }

}
