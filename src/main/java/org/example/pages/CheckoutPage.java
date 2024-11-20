package org.example.pages;

import codrea.utility.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends PageUtility {
    @FindBy(css = "a[class*='submit']")
    private WebElement placeOrderButton;

    @FindBy(css = "input[placeholder='Select Country'")
    private WebElement countryInput;
    public HeaderComponent headerComponent;

    public final By countryBy = By.cssSelector("button.ta-item");
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver,this);
    }

    private void selectCountry(String country){
        countryInput.sendKeys(country);
        List<WebElement> countries = waitForAllElementsToAppear(countryBy);
        countries
                .stream()
                .filter(element -> element.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresent(WebElement::click);

    }

    public ConfirmationPage placeOrder(String country){
        selectCountry(country);
        placeOrderButton.click();

        return new ConfirmationPage(driver);
    }


}
