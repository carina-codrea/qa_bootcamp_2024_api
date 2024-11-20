package org.example.pages;

import codrea.utility.PageUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends PageUtility {
    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userPassword")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;


    public LandingPage(WebDriver driver) {
        super(driver);
        //uses this driver to initialize elements for this page
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");

    }

    public ProductsPage login(String email,String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        return new ProductsPage(driver);
    }


}
