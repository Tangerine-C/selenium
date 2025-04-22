package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActorSearch extends BaseTest {

    @Test(priority = 1)
    public void searchAndOpenDenzelWashingtonProfile() {
        initDriver();
        driver.get("https://www.imdb.com/");
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search")));
        searchBox.sendKeys("Denzel Washington");
        searchBox.sendKeys(Keys.ENTER);

        WebElement actorLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'/name/nm0000243')]")));
        actorLink.click();
    }

    @Test(priority = 2)
    public void validateActorName() {
        WebElement nameHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[data-testid='hero__pageTitle']")));
        Assert.assertTrue(nameHeader.getText().contains("Denzel Washington"), "Actor name does not match");
    }

    @Test(priority = 3)
    public void validateKnownForSection() throws InterruptedException {
        WebElement knownFor = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='nm_flmg_kwn_for']")));
        Assert.assertTrue(knownFor.isDisplayed(), "'Known For' section not found");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1500);");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void openKnownForMovie() {
        WebElement knownForMovie = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='nm_kwn_for_0']//a[contains(@href,'/title/')]")));
        knownForMovie.click();
    }

    @Test(priority = 5)
    public void validateMoviePageOpened() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertTrue(title.isDisplayed(), "Movie title not displayed");
    }

    @Test(priority = 6)
    public void validateMovieGenres() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 4500);");
        WebElement genres = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@data-testid='storyline-genres']")));
        Assert.assertTrue(genres.isDisplayed(), "Genres not found on movie page");
    }

    @Test(priority = 7)
    public void validateDirectorOnMoviePage() {
        WebElement director = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@data-testid='title-pc-principal-credit']//a[contains(@href,'/name/')]")));
        Assert.assertTrue(director.isDisplayed(), "Director information not found");
    }

    @Test(priority = 8)
    public void validateRatingOnMoviePage() {
        WebElement rating = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@data-testid,'hero-rating-bar__aggregate-rating')]//span[contains(@class,'sc')]")));
        Assert.assertTrue(rating.isDisplayed(), "Rating not visible");
    }

    @Test(priority = 9)
    public void validateCastSection() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1500);");
        WebElement castSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@data-testid='title-cast']")));
        Assert.assertTrue(castSection.isDisplayed(), "Cast section not found");
    }

    @Test(priority = 10)
    public void validateDidYouKnowTriviaSection() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 2000);");
        WebElement triviaSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[@data-testid='DidYouKnow']")));
        Assert.assertTrue(triviaSection.isDisplayed(), "'Did You Know' trivia section is missing");
    }
}
