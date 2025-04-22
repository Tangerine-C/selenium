/*
 * Name: Thomas Cramer
 * Professor: D. Davenspathy
 * Date: 3/25/25
 * Assignment: Annotations and Assertions in Automation Testing
 *
 * Description:
 * This Selenium test script automates interactions with the FGCU Canvas website
 * and performs different logical tests using assertions. It includes:
 * 1. **Test 1**: Login to Canvas and verify successful login.
 * 2. **Test 2**: Navigate to the Calendar and verify it loads correctly.
 * 3. **Test 3**: Check if courses are listed in the Courses section.
 * 4. **Test 4**: Verify the Inbox functionality loads properly.
 * 5. **Test 5**: Check if an assignment submission option is available.
 * 6. **Test 6**: Verify Profile settings page loads correctly.
 * 7. **Test 7**: Test the search functionality within Canvas.
 * 8. **Test 8**: Logout from Canvas and ensure the login page appears.
 */


package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class CanvasAutomationTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.out.println("Initializing WebDriver and launching browser...");
        System.setProperty("webdriver.chrome.driverarm64", "C:/Users/thomascramer/Downloads/chromedriverarm64");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testCanvasLogin() {
        driver.get("https://fgcu.instructure.com/");

        // Locate and interact with login elements
        WebElement signInField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Sign in")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        signInField.sendKeys("EXAMPLEXXXX@eagle.fgcu.edu");
        passwordField.sendKeys("EXAMPLE");
        loginButton.click();

        // Prompt for user action
        System.out.println("A call will pop up, click that, then Canvas should open up.");

        // Assert login success
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));
        Assert.assertTrue(dashboard.isDisplayed(), "Login failed!");
    }

    @Test(priority = 2)
    public void testNavigateToCalendar() {
        driver.get("https://fgcu.instructure.com/calendar");

        // Assert calendar is visible
        WebElement calendarView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("calendar-view")));
        Assert.assertTrue(calendarView.isDisplayed(), "Failed to load Calendar!");
    }

    @Test(priority = 3)
    public void testCheckCourses() {
        driver.get("https://fgcu.instructure.com/courses");

        // Get list of courses
        List<WebElement> courses = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("course-list")));

        // Assert at least one course is listed
        Assert.assertFalse(courses.isEmpty(), "No courses found!");
    }

    @Test(priority = 4)
    public void testInboxFunctionality() {
        driver.get("https://fgcu.instructure.com/inbox");

        // Assert inbox is visible
        WebElement inboxContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inbox-container")));
        Assert.assertTrue(inboxContainer.isDisplayed(), "Inbox not loaded!");
    }

    @Test(priority = 5)
    public void testAssignmentSubmission() {
        driver.get("https://fgcu.instructure.com/courses/assignments");

        // Click the first available assignment
        WebElement assignment = wait.until(ExpectedConditions.elementToBeClickable(By.className("assignment-link")));
        assignment.click();

        // Locate submission button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAssignment")));
        Assert.assertTrue(submitButton.isDisplayed(), "Submit button not found!");
    }

    @Test(priority = 6)
    public void testProfileSettings() {
        driver.get("https://fgcu.instructure.com/profile");

        // Check if profile settings load
        WebElement profileHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertTrue(profileHeader.getText().contains("Profile"), "Profile page not loaded correctly!");
    }

    @Test(priority = 7)
    public void testSearchFunctionality() {
        driver.get("https://fgcu.instructure.com/");

        // Locate search bar and search for a course
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys("Software Testing" + Keys.ENTER);

        // Check if results appear
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-results")));
        Assert.assertTrue(searchResults.isDisplayed(), "Search results not found!");
    }

    @Test(priority = 8)
    public void testLogout() {
        driver.get("https://fgcu.instructure.com/");

        // Click profile dropdown and select logout
        WebElement profileDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("profile-dropdown")));
        profileDropdown.click();
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout")));
        logoutButton.click();

        // Assert redirected to login page
        WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form")));
        Assert.assertTrue(loginPage.isDisplayed(), "Logout failed!");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
