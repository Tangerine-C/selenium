package testClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TicketsTest extends BaseTest {

    @Test(priority = 1)
    public void visitTickets() throws InterruptedException {
        initDriver();
        WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imdbHeader-navDrawerOpen")));
        menuButton.click();

        WebElement ticketButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Showtimes & Tickets")));
        ticketButton.click();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void changeZip() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        WebElement elementToHover = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='location-button']")));
        actions.moveToElement(elementToHover).click().perform();

        WebElement zipButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("text-input__0")));
        zipButton.click();
        zipButton.sendKeys(Keys.COMMAND + "a");
        zipButton.sendKeys(Keys.DELETE);
        zipButton.sendKeys("33907");
        Thread.sleep(2000);
        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='postal-submit-button']")));
        save.click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void changeDate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        WebElement elementToHover = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid='date-button']")));
        actions.moveToElement(elementToHover).click().perform();
        Thread.sleep(5000);
        actions.sendKeys(Keys.RIGHT).perform();
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void viewTheaters() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        WebElement theatersLink = driver.findElement(By.xpath("//h3[text()='Theaters']/parent::a"));
        actions.moveToElement(theatersLink).click().perform();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void favoriteTheater() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        List<WebElement> favButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("button[data-testid='favorite-icon-button']")
        ));

        favButtons.get(0).click();
        Thread.sleep(2000);

        WebElement regalLink = driver.findElement(By.xpath("//a[text()='Regal Belltower']"));
        regalLink.click();

        Thread.sleep(2000);
    }
}
