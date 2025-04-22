package testClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;

import javax.swing.*;
    //NOTE!!! LINE 130 CONTAINS AN ABSOLUTE PATH, PLEASE UPDATE IT WITH YOUR OWN ABS. PATH TO THE SCREENSHOT FOLDER.
    //SIMILARLY, UPDATE THE LOGIN INFORMATION IF YOU PREFER YOUR OWN ACCOUNT TO BE USED AS CAPTCHA COULD BE AN ISSUE.
public class LoginAndProfileTests extends BaseTest {

    @Test(priority = 1)
    public void loginToIMDb() {
        initDriver();

        driver.get("https://www.imdb.com/");

        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.ipc-btn.imdb-header__signin-text")));
        signInBtn.click();

        WebElement signInIMDb = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Sign in with IMDb")));
        signInIMDb.click();

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        WebElement signInSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInSubmit")));

        email.sendKeys("imdb8320@gmail.com");
        password.sendKeys("testtest");
        signInSubmit.click();

        wait.until(ExpectedConditions.urlContains("imdb.com"));
        System.out.println("Logged in successfully.");
    }
    @Test(priority = 2)
    public void changeLanguages() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for(int i=0; i<9; i++){
            String languageMenuXPath = "";
            String idPrefix = "";
            switch(i){
                case 0:
                    languageMenuXPath = "//label[@aria-label='Toggle language selector']//span[text()='EN']";
                    idPrefix = "fr-CA";
                    break;
                case 1,2:
                    if(i == 1){
                        languageMenuXPath = "//label[@aria-label='Basculer vers le sélecteur de langue']//span[text()='FR']";
                        idPrefix = "fr-FR";
                    }
                    else{
                        languageMenuXPath = "//label[@aria-label='Sélecteur de langue']//span[text()='FR']";
                        idPrefix = "de-DE";
                    }
                    break;
                case 3:
                    languageMenuXPath = "//label[@aria-label='Sprachauswahl ein-/ausschalten']//span[text()='DE']";
                    idPrefix = "hi-IN";
                    break;
                case 4:
                    languageMenuXPath = "//label[@aria-label='भाषा चयनकर्ता को टॉगल करें']//span[text()='HI']";
                    idPrefix = "it-IT";
                    break;
                case 5:
                    languageMenuXPath = "//label[@aria-label='Attiva/disattiva il selettore della lingua']//span[text()='IT']";
                    idPrefix = "pt-BR";
                    break;
                case 6:
                    languageMenuXPath = "//label[@aria-label='Seletor de idioma']//span[text()='PT']";
                    idPrefix = "es-ES";
                    break;
                case 7,8:

                    if(i == 7){
                        languageMenuXPath = "//label[@aria-label='Alternar selector de idioma']//span[text()='ES']";
                        idPrefix = "es-MX";
                    }
                    else{
                        languageMenuXPath = "//label[@aria-label='Alternar el selector de idioma']//span[text()='ES']";
                        idPrefix = "en-US";
                    }


            }
            Thread.sleep(1000);
            WebElement languageMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(languageMenuXPath)));
            languageMenu.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-menu-id='nav-language-selector']")));
            Thread.sleep(500);
            WebElement languageOption = driver.findElement(By.id("language-option-" + idPrefix));
            js.executeScript("arguments[0].click();", languageOption);
        }


    }

    @Test(priority = 3)
    public void screenshot() throws InterruptedException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String savePath = ".//src//test//java//testClasses//screenshots//test_screenshot.png"; //REPLACE <name> in Users\\<name>\\Downloads

        try{
            FileHandler.copy(screenshotFile, new File(savePath));
            System.out.println("Saved screenshot to " + savePath);
        } catch(IOException e){
            System.out.println("Could not save screenshot:" + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void uploadUserImage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@aria-label='Toggle Acount Menu']//span[text()='John']"))); //"John" needs to be changes if your username isn't John
        profileButton.click();

        WebElement profileSettings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account settings']")));
        profileSettings.click();

        WebElement editSettings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Edit profile']")));
        editSettings.click();
        Thread.sleep(1000);
        WebElement upload = driver.findElement(By.cssSelector("input[data-testid='upe-image-select-fileInput']"));
        js.executeScript("arguments[0].style.display = 'block';", upload);
        String imagePath = "C:\\Users\\matth\\Downloads\\Compressed\\selenium-first-branch\\selenium-first-branch\\mavenTest\\src\\test\\java\\testClasses\\screenshots\\test_screenshot.png"; //Use the screenshot, REQUIRED TO BE ABSOLUTE
        Thread.sleep(500);
        upload = driver.findElement(By.cssSelector("input[data-testid='upe-image-select-fileInput']"));
        upload.sendKeys(imagePath);

        WebElement uploadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='upe-image-upload-prompt-save']//span[text()='Save']")));
        uploadButton.click();
        Thread.sleep(4000);
    }

    @Test(priority = 5)
    public void changeBioThenRevertChangesToUser() throws InterruptedException {
        WebElement bioEditBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ipc-btn ipc-btn--half-padding ipc-btn--center-align-content ipc-btn--default-height ipc-btn--core-base ipc-btn--theme-base ipc-btn--button-radius ipc-btn--on-accent2 ipc-text-button sc-2cb3d622-9 eLrCFw']//span[text()='Edit']")));
        bioEditBtn.click();

        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@aria-describedby='textarea__help-text']")));
        textArea.sendKeys("Hey, This is for a Software Testing Course 2025! Sorry!");

        WebElement bioEditSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='prompt-saveButton']//span[text()='Save changes']")));
        bioEditSubmit.click();

        //Now Clear
        Thread.sleep(3000);

        WebElement deleteImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='upe-image-delete']//span[text()='Delete image']")));
        deleteImage.click();
        WebElement deleteImageConfirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='prompt-saveButton']//span[text()='Delete']")));
        deleteImageConfirmation.click();
        Thread.sleep(3000);

        bioEditBtn.click();
        //FIND THE ELEMENTS AGAIN (THEY ARE STALE AFTER SUBMISSION)
        textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@aria-describedby='textarea__help-text']")));
        textArea.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        bioEditSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='prompt-saveButton']//span[text()='Save changes']")));
        bioEditSubmit.click();
    }
}
