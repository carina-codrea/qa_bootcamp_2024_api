package org.example.pages;

import org.example.utility.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends PageUtility {
    @FindBy(css = "div.subtotal button")
    private WebElement checkoutButton;
    private final By cartProductsBy = By.cssSelector("div.cart");
    private final By productNameBy = By.tagName("h3");
    public HeaderComponent headerComponent;
    public CartPage(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver,this);
    }

    private List<WebElement> getCartProducts(){
        return waitForAllElementsToAppear(cartProductsBy);

    }

    public boolean isProductInCart(String productName){
       return getCartProducts()
                .stream()
                .anyMatch(cartProduct -> cartProduct.findElement(productNameBy).getText().equalsIgnoreCase(productName));

    }

    public CheckoutPage goToCheckout(){
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

}
