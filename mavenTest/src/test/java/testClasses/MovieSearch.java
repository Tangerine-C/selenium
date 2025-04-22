package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieSearch extends BaseTest {

    @Test(priority = 1)
    public void searchAndOpenInceptionMovie() {
        initDriver();
        driver.get("https://www.imdb.com/");
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search")));
        searchBox.sendKeys("Inception");
        searchBox.sendKeys(Keys.ENTER);

        WebElement inceptionLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Inception') and contains(@href, '/title/tt1375666/')]")));
        inceptionLink.click();
    }

    @Test(priority = 2)
    public void validateMovieTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertTrue(title.getText().contains("Inception"), "Title does not match Inception");
    }

    @Test(priority = 3)
    public void validateMovieRating() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@data-testid,'hero-rating-bar__aggregate-rating')]//span[contains(@class,'sc')]")));
        Assert.assertTrue(rating.isDisplayed(), "Rating is not visible");
    }

    @Test(priority = 4)
    public void validateDirectorName() {
        WebElement director = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'/name/') and contains(text(),'Christopher Nolan')]")));
        Assert.assertTrue(director.isDisplayed(), "Director Christopher Nolan not found");
    }

    @Test(priority = 5)
    public void checkReleaseYear() {
        WebElement releaseYear = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href, 'releaseinfo') and contains(text(),'2010')]")));
        Assert.assertTrue(releaseYear.isDisplayed(), "Release year not found or incorrect");
    }

    @Test(priority = 6)
    public void checkGenreTags() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 4500);");
        WebElement genre = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//html//body//div[2]//main//div//section[1]//div//section//div//div[1]//section[8]//div[2]//ul[2]//li[2]//span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genre);
        Assert.assertTrue(genre.isDisplayed(), "Genres not visible");
    }

    @Test(priority = 7)
    public void viewAllCastAndCrew() throws InterruptedException {
        WebElement castCrewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.partialLinkText("All cast & crew")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", castCrewLink);
        Thread.sleep(1000);
        castCrewLink.click();
        WebElement castList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//html//body//div[2]//main//div//section//div//section//div//div[1]//section[3]//div[2]")));
        Assert.assertTrue(castList.isDisplayed(), "Cast list not loaded");
    }

    @Test(priority = 8)
    public void checkWatchlistButton() throws InterruptedException {
        driver.navigate().back(); // Go back to movie page from cast page
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -4500);");
        WebElement watchlistButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//html//body//div[2]//main//div//section[1]//section//div[3]//section//section//div[3]//div[2]//div[2]//div[3]//div[2]//div//button[1]")));
        Assert.assertTrue(watchlistButton.isDisplayed(), "Watchlist button not found");
        Thread.sleep(1500);
    }

    @Test(priority = 9)
    public void verifyDidYouKnowSection() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 6000);");
        WebElement didYouKnow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@data-testid='DidYouKnow']")));
        Assert.assertTrue(didYouKnow.isDisplayed(), "'Did You Know' section not visible");
    }
}
