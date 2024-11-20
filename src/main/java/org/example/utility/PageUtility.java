package org.example.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageUtility {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By toastContainerBy = By.id("toast-container");


    public PageUtility(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public List<WebElement> waitForAllElementsToAppear(By by){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementToAppear(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(By by){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public String getToastMessageText(){
        WebElement toastContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainerBy));

        return toastContainer.getText();
    }
}
