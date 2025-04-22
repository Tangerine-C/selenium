package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class PopularMovies extends BaseTest {

    @Test(priority = 1)
    public void openMenu() throws InterruptedException {
        initDriver(); // Needed in case the test is run independently
        driver.get("https://www.imdb.com/");
        Thread.sleep(1000);
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='imdbHeader-navDrawerOpen']")));
        menuButton.click();

        System.out.println("Menu opened successfully.");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void openPopularMovies() {
        // Wait for and click the "Most Popular Movies" item
        WebElement popularMovies = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Most Popular Movies']/ancestor::a")));
        popularMovies.click();

        System.out.println("Popular Movies opened successfully.");
    }

    @Test(priority = 3)
    public void openMovie() throws InterruptedException {
        WebElement minecraftMovie = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h3[text()='A Minecraft Movie']/ancestor::a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", minecraftMovie);
        Thread.sleep(1000);
        // Wait for the Minecraft movie link to be clickable and click it
        minecraftMovie.click();
        System.out.println("Minecraft movie page opened.");
    }

    @Test(priority = 4)
    public void openPhotosTab() throws InterruptedException {
        WebElement photosButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/title/tt3566834/mediaviewer/rm3468004098/?ref_=tt_ph_sm']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", photosButton);
        Thread.sleep(1000);
        // Wait and click on the "Photos" button by its visible text
        photosButton.click();

        System.out.println("Photos tab opened.");
    }

    @Test(priority = 5)
    public void closePhotoViewer() {
        // Wait for and click the "Close" link to exit the photo gallery
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[data-testid='mv-breadcrumb-close-button']")));
        closeButton.click();

        System.out.println("Photo viewer closed.");
    }

    @Test(priority = 6)
    public void clickShareButton() throws InterruptedException {
        // Wait for and click the "Share on social media" button
        WebElement shareButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='share-button']")));
        shareButton.click();

        System.out.println("Share on social media button clicked.");
        driver.navigate().refresh();
        Thread.sleep(500);
    }


}
