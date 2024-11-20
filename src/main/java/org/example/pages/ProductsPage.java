package org.example.pages;

import org.example.utility.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends PageUtility {
    public HeaderComponent headerComponent;
    private final By productsBy = By.cssSelector("div.card");
    private final By productTitleBy = By.tagName("b");
    private final By addToCartBy = By.cssSelector("button.w-10");

    private final By spinnerBy = By.tagName("ngx-spinner");


    public ProductsPage(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver,this);
    }

    private List<WebElement> getProductsList(){
        return waitForAllElementsToAppear(productsBy);
    }
    public void addProductToCart(String productName){
        getProductsList()
                .stream()
                .filter(product -> product.findElement(productTitleBy).getText().equalsIgnoreCase(productName))
                .findFirst()
                .ifPresent(product -> product.findElement(addToCartBy).click());

          waitForElementToDisappear(spinnerBy);


    }


}
