package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @Test(priority = 1)
    public void openMenu() throws InterruptedException {
        //-------------------------------------------------------
        // Needed in case the test is run independently   
        //initDriver();
        //driver.get("https://www.imdb.com/");
        //-------------------------------------------------------
        Thread.sleep(1000);
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("imdbHeader-navDrawerOpen")));
        menuButton.click();

        System.out.println("Menu opened successfully.");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void closeMenu() {
        WebElement menuClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@title='Close Navigation Drawer']")));
        menuClose.click();

        System.out.println("Menu closed successfully.");
    }

    @Test(priority = 3)
    public void movieAndTVElements() throws InterruptedException {
        String elementXPath = "";
        for(int i = 0; i<13; i++) {
            elementXPath = switch (i) {
                case 0 -> "//Span[text()='Release Calendar']";
                case 1 -> "//Span[text()='Top 250 Movies']";
                case 2 -> "//Span[text()='Most Popular Movies']";
                case 3 -> "//Span[text()='Browse Movies by Genre']";
                case 4 -> "//Span[text()='Top Box Office']";
                case 5 -> "//Span[text()='Showtimes & Tickets']";
                case 6 -> "//Span[text()='Movie News']";
                case 7 -> "//Span[text()='India Movie Spotlight']";
                case 8 -> "//Span[text()=\"What's on TV & Streaming\"]"; //This one requires special care since it contains a "'" (apostrophe)
                case 9 -> "//Span[text()='Top 250 TV Shows']";
                case 10 -> "//Span[text()='Most Popular TV Shows']";
                case 11 -> "//Span[text()='Browse TV Shows by Genre']";
                case 12 -> "//Span[text()='TV News']";
                default -> elementXPath;
            };
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbHeader-navDrawerOpen")));
            menuButton.click();

            WebElement menuOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
            menuOption.click();
            Thread.sleep(500);
        }
    }
    @Test(priority = 4)
    public void awardsEventsAndWatchElements() throws InterruptedException {
            String elementXPath = "";
            for(int i = 0; i<14; i++) {
                elementXPath = switch (i) {
                    case 0 -> "//Span[text()='Oscars']";
                    case 1 -> "//Span[text()='Cannes Film Festival']";
                    case 2 -> "//Span[text()='Star Wars']";
                    case 3 -> "//Span[text()='SXSW Film Festival']";
                    case 4 -> "//Span[text()='STARmeter Awards']";
                    case 5 -> "//Span[text()='Awards Central']";
                    case 6 -> "//Span[text()='Festival Central']";
                    case 7 -> "//Span[text()='All Events']";
                    case 8 -> "//Span[text()='What to Watch']"; //This one requires special care since it contains a "'" (apostrophe)
                    case 9 -> "//Span[text()='Latest Trailers']";
                    case 10 -> "//Span[text()='IMDb Originals']";
                    case 11 -> "//Span[text()='IMDb Picks']";
                    case 12 -> "//Span[text()='IMDb Spotlight']";
                    case 13 -> "//Span[text()='IMDb Podcasts']";
                    default -> elementXPath;
                };
                WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbHeader-navDrawerOpen")));
                menuButton.click();

                WebElement menuOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
                menuOption.click();
                Thread.sleep(500);
            }
    }
    @Test(priority = 5)
    public void celebsAndCommunityElements() throws InterruptedException {
        String elementXPath = "";
        for(int i = 0; i<6; i++) {
            elementXPath = switch (i) {
                case 0 -> "//Span[text()='Born Today']";
                case 1 -> "//Span[text()='Most Popular Celebs']";
                case 2 -> "//Span[text()='Celebrity News']";
                case 3 -> "//Span[text()='Help Center']";
                case 4 -> "//Span[text()='Contributor Zone']";
                case 5 -> "//Span[text()='Polls']";
                default -> elementXPath;
            };
            if (i == 4 || i == 5){
                Thread.sleep(500);
                driver.navigate().back();
            }
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbHeader-navDrawerOpen")));
            menuButton.click();

            WebElement menuOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
            menuOption.click();
            Thread.sleep(500);
        }
    }
}
