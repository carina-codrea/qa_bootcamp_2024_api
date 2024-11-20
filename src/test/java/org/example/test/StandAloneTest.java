package org.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {


    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        String countryName = "Romania";

        WebDriver driver = new ChromeDriver();

        try{
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://rahulshettyacademy.com/client/");

            //login with correct credentials
            driver.findElement(By.id("userEmail")).sendKeys("selenium-tester@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("Pass123@");
            driver.findElement(By.id("login")).click();

            List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
            products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
                    .findFirst().ifPresent(desiredProduct -> desiredProduct.findElement(By.cssSelector("button[class*='w-10']")).click());

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

            driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

            List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cart h3"));

            Assert.assertTrue(cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName)));

            driver.findElement(By.cssSelector("div.subtotal button")).click();

            driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(countryName);

            List<WebElement> countries = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button[class*='ta-item']")));
            countries
                    .stream()
                    .filter(country -> country.findElement(By.tagName("span")).getText().equalsIgnoreCase(countryName))
                    .findFirst().ifPresent(WebElement::click);

            driver.findElement(By.cssSelector("div.actions a")).click();

            Assert.assertEquals(driver.findElement(By.cssSelector("h1.hero-primary")).getText(),"THANKYOU FOR THE ORDER.");


        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //driver.quit();
        }
    }
}
