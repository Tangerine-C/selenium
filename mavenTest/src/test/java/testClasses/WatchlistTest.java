package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.time.Duration;

public class WatchlistTest extends BaseTest {



    @Test(priority = 1)
    public void openWatchlist() throws InterruptedException {
        initDriver();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebElement watchlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Watchlist")));
        watchlist.click();
    }

    @Test(priority = 2)
    public void togglePublic(){
        WebElement publicToggle = driver.findElement(By.cssSelector("label[for='hero-list-subnav-privacy-switch']"));
        publicToggle.click();
    }

    @Test(priority = 3)
    public void createList() throws InterruptedException {
        //initDriver();
        WebElement createListButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[2]//main//div//section//section//div[3]//section//div[2]//div[2]//a//span//div[1]")));

        createListButton.click();

        Thread.sleep(Duration.ofSeconds(2));

        WebElement nameField = driver.findElement(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//div[1]//div//div//div//div//input"));
        nameField.clear();
        nameField.sendKeys("Test_Watchlist");

        Thread.sleep(Duration.ofSeconds(2));
        WebElement DescriptionField = driver.findElement(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//div[2]//div//div//div//div//textarea"));
        DescriptionField.clear();
        DescriptionField.sendKeys("Test_Description");
        Thread.sleep(Duration.ofSeconds(2));
        WebElement createButton = driver.findElement(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//button//span"));
        createButton.click();
        Thread.sleep(Duration.ofSeconds(2));
    }

    @Test(priority = 4)
    public void editList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addField = driver.findElement(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//div[1]//div[2]//div//div[1]//div//div//div//div//div//input"));
        addField.clear();
        addField.sendKeys("Star Wars: Episode IV - A New Hope (1977)");

        Thread.sleep(Duration.ofSeconds(1));

        WebElement watchlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[2]//nav//div[2]//div[5]//a//span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", watchlist);
        watchlist.click();

        Thread.sleep(Duration.ofSeconds(5));



    }

    @Test(priority = 5)
    public void deleteList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement listsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[2]//div[2]//div[2]//div//section[2]//div//div[1]//div//a//h3")));
        listsButton.click();

        WebElement manageList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//div[3]//ul//li[1]//div[3]//button")));
        manageList.click();

        WebElement deleteList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section//div[3]//ul//li[1]//div[3]//div//div//div//div[2]//ul//li[1]//span")));
        deleteList.click();

        WebElement confirmDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html//body//div[4]//div[2]//div//div[2]//div//div//div//button[2]//span")));
        confirmDelete.click();
    }

}
