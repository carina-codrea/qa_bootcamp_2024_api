package org.example.testComponents;

import codrea.pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;

    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/globalData.properties");
        properties.load(fileInputStream);

        String browserName = System.getProperty("browser")!= null? System.getProperty("browser") : properties.getProperty("browser");

        switch (browserName){
            case "edge" -> driver = new EdgeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            case "chrome-headless" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
            }
            default -> driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//" + path), StandardCharsets.UTF_8);

        //convert string to hashmap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String,String>> data = objectMapper.readValue(jsonContent, new TypeReference<>() {
        });
        //this is a list which contains two hashmaps in our case {map,map}

        return data;

    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        String screenshotPath = null;

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;

            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

            FileHandler.copy(source,destination);

            String[] relativePath = destination.toString().split("reports");
            screenshotPath = ".//" + relativePath[1];

        } catch (Exception e) {

            System.out.println("Failure to take screenshot " + e);

        }

        return screenshotPath;
    }


    @BeforeMethod
    public void launchApplication() throws IOException {
       driver = initializeDriver();

       landingPage = new LandingPage(driver);
       landingPage.goTo();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
