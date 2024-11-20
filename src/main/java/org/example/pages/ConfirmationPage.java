package org.example.pages;

import org.example.utility.PageUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends PageUtility {
    @FindBy(css = "h1.hero-primary")
    private WebElement confirmationMessage;

    public HeaderComponent headerComponent;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver,this);
    }

    public String getConfirmationMessage(){
        return  confirmationMessage.getText();
    }
}
