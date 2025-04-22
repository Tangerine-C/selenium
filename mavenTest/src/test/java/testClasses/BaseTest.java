package testClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.Instant;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public void initDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
