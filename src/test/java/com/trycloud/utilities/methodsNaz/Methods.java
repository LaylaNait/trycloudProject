package com.trycloud.utilities.methodsNaz;

import com.github.javafaker.Faker;
import com.trycloud.utilities.BrowserUtils;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    //3
    public static List<String> getElementsTextDashboard() {
        List<String> result = new ArrayList<>();
        List<WebElement> menu = Driver.getDriver().findElements(By.xpath("//*[@id='appmenu']/li[@data-id]"));
        Actions act = new Actions(Driver.getDriver());
        for (int i = 0; i < menu.size(); i++) {
            act.moveToElement(menu.get(i)).perform();
            result.add(menu.get(i).getText());
        }
        return result;

    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void logIn(String username, String password) {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
        Driver.getDriver().findElement(By.xpath("//input[@name='user']")).sendKeys(username);
        Driver.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        Driver.getDriver().findElement(By.xpath("//input[@id='submit-form']")).click();
    }
    //4
    public static boolean verifyAllSelected() {
        WebElement checkboxToSelectAll = Driver.getDriver().findElement(By.xpath("//label[@for='select_all_files']"));
        List<WebElement> rows = Driver.getDriver().findElements(By.xpath("//tbody[@id='fileList']//tr/td[1]"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebElement fileSummary = Driver.getDriver().findElement(By.xpath("//td[@class='filesummary']"));
        js.executeScript("arguments[0].scrollIntoView()", fileSummary);
        boolean allSelected = false;
        for (WebElement row : rows) {
            if (row.isEnabled()) {
                allSelected = true;
            }

        }
        return checkboxToSelectAll.isEnabled() && allSelected == true;
    }
    //5
    public static String clickOnActionDots() {
        int num = 2;
        String result = "";
        Actions act = new Actions(Driver.getDriver());
        List<WebElement> listOfTableNames = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        act.moveToElement(listOfTableNames.get(num)).perform();
        result = listOfTableNames.get(num).getText();
        Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(num).click();
        return result;
    }
//5
    public static void verifyChosenFile(String addedFile) {
        Actions act = new Actions(Driver.getDriver());
        BrowserUtils.sleep(2);
        String fileName = "";
        for (int i = 0; i < Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).size(); i++) {
            if (Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).get(i).getText().equalsIgnoreCase(addedFile)) {
                fileName = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).get(i).getText();
                act.moveToElement(Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i)).perform();
                //next actions to remove from Favorite
                Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i).click();
                Driver.getDriver().findElement(By.xpath("//a//span[contains(.,'Remove from favorites')]")).click(); //removeFromFavourites
            }
        }
        Assert.assertEquals(addedFile, fileName);

    }
    public static String chooseFavoriteAndRemove() {
        Actions act = new Actions(Driver.getDriver());
        List<WebElement> listOfTableNames = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        String removed = "";
        act.moveToElement(listOfTableNames.get(0)).perform();
        removed = listOfTableNames.get(0).getText();
        Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(0).click();
        BrowserUtils.sleep(2);

        return removed;
    }
    //6
    public static void verifyRemovedFromFavorites(String removed) {
        String actualFavorite = "";
        for (int i = 0; i < Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).size(); i++) {
            if (!Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).get(i).getText().equalsIgnoreCase(removed)) {
                actualFavorite = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")).get(i).getText();
                // act.moveToElement(Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i)).perform();
                // Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i).click();
                // dashboardPage.removeFromFavourites.click();
            }
        }
        System.out.println("removed = " + removed);
        System.out.println("actualFavorite = " + actualFavorite);
        Assert.assertFalse(removed.equalsIgnoreCase(actualFavorite));


    }
    //6
    public static void uploadFIleToFiles(String fileToUpload) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        BrowserUtils.sleep(5);
        WebElement progressBar = Driver.getDriver().findElement(By.xpath("//div[@id='uploadprogressbar']"));
        Driver.getDriver().findElement(By.xpath("//input[@id='file_upload_start']")).sendKeys(fileToUpload);

        wait.until(ExpectedConditions.invisibilityOf(progressBar));
        //BrowserUtils.sleep(5);
        List<String> uploadedFiles = new ArrayList<>();
        for (WebElement element : Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"))) {
            uploadedFiles.add(element.getText());
        }


    }
    //6
    public static void verifyFileUploadedToFiles(String expectedFileName) {

        BrowserUtils.sleep(4);
        //delete by name add list with FORI
        List<WebElement> actionMenus = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']"));
        List<WebElement> textOfFiles = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        List<String> tableList = new ArrayList<>();
        for (WebElement textOfFile : textOfFiles) {
            tableList.add(textOfFile.getText());
        }
        Assert.assertTrue(tableList.contains(expectedFileName));
        for (int i = 0; i < actionMenus.size(); i++) {
            if (textOfFiles.get(i).getText().equalsIgnoreCase(expectedFileName)) {
                actionMenus.get(i).click();
                Driver.getDriver().findElement(By.xpath("//span[.='Delete file']")).click();
                break;
            }
        }


    }
    //7
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }
    //7
    public static List<String> verifyFolderNameIsDisplayedOnThePage() {

        List<String> result =Methods.getElementsText(Driver.getDriver().findElements
                (By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")));
       // System.out.println("result = " + result);
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView()",Driver.getDriver().findElement(By.xpath("//td[@class='filesummary']")));
        //Assert.assertTrue(result.contains(folderName));

        BrowserUtils.sleep(3);
        return result;
    }
    ///7
    public static void clickAnyFolder() {
        Driver.getDriver().findElements(By.xpath("//tr[@data-type='dir']")).get(0).click();
    }
    //7
    public static void verifyUploadFile(String fileName) {
        BrowserUtils.sleep(5);
        List<String> strings = new ArrayList<>();
        for (WebElement element : Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"))) {
            strings.add(element.getText());
        }
        System.out.println("list of files = " + strings);
        Assert.assertTrue(strings.contains(fileName));
        BrowserUtils.sleep(4);
        for (WebElement dot : Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']"))) {
            BrowserUtils.sleep(4);
            dot.click();
           Driver.getDriver().findElement(By.xpath("//span[.='Delete file']")).click();
            break;
        }


    }
    //8
    public static String chooseFileFolderAndRemove() {
        List<WebElement> listOfTableNames = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        String removed = "";
        Actions act = new Actions(Driver.getDriver());
        act.moveToElement(listOfTableNames.get(0)).perform();
        removed = listOfTableNames.get(0).getText();
        Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(0).click();
        BrowserUtils.sleep(2);

        return removed;
    }
    public static void verifyDeletedFile(String expectedFileRemoved) {
        //String expectedFileRemoved="";
        BrowserUtils.sleep(3);
        List<WebElement> actionMenus = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']"));
        List<WebElement> textOfFiles = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        BrowserUtils.sleep(3);
        List<String> tableList = new ArrayList<>();
        for (WebElement textOfFile : textOfFiles) {
            tableList.add(textOfFile.getText());
        }

        System.out.println("expectedFileRemoved = " + expectedFileRemoved);
        System.out.println("deleted files:"+tableList);
        Assert.assertTrue(tableList.contains(expectedFileRemoved));

        /*
        Assert.assertTrue(tableList.contains(expectedFileRemoved));
        for (int i = 0; i < actionMenus.size(); i++) {
            if(textOfFiles.get(i).getText().equalsIgnoreCase(expectedFileRemoved)){
                actionMenus.get(i).click();
                deleteFile.click();
                break;
            }
        }
        */

    }
    //9
    public static void clickOnFile(int num) {
        Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(num).click();
    }
    public static String userWritesComment() {
        Faker faker = new Faker();
        Driver.getDriver().findElement(By.xpath("//a[@id='commentsTabView']")).click();
        String inputMessage = faker.chuckNorris().fact();
        Driver.getDriver().findElement(By.xpath("//div[@data-placeholder='New comment …']")).sendKeys(inputMessage);

        return inputMessage;

    }
    public static void verifyCommentIsDisplayed(String inputMessage) {
        BrowserUtils.sleep(2);
        List<WebElement> commentSectionContent = Driver.getDriver().findElements(By.xpath("//ul[@class='comments']/li/div[@class='message']"));
        List<String> textOfComments = new ArrayList<>();
        for (WebElement element : commentSectionContent) {
            textOfComments.add(element.getText());
        }
        System.out.println("inputMessage = " + inputMessage);
        System.out.println("textOfComments = " + textOfComments);
        Assert.assertTrue(textOfComments.contains(inputMessage));
       Driver.getDriver().findElement(By.xpath("//a[@class='app-sidebar__close icon-close']")).click();

    }
    //10
    public static void uploadFile(String fileToUpload) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        BrowserUtils.sleep(5);
        Driver.getDriver().findElement(By.xpath("//input[@id='file_upload_start']")).sendKeys(fileToUpload);
        wait.until(ExpectedConditions.invisibilityOf(Driver.getDriver().findElement(By.xpath("//div[@id='uploadprogressbar']"))));
    }
    //11
    public static void searchForUser(String userName) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        Driver.getDriver().findElement(By.xpath("//input[@placeholder='Search conversations or users']")).sendKeys(userName);
        String locatorForUserName = "//a[@aria-label='Conversation, " + userName + "']";
        WebElement searchResultWithUser = Driver.getDriver().findElement(By.xpath(locatorForUserName));

        wait.until(ExpectedConditions.visibilityOf(searchResultWithUser));

        searchResultWithUser.click();

    }
    //12
    public static String writeMessage() {
        Faker faker = new Faker();
        Actions act = new Actions(Driver.getDriver());
        // WebElement inputForMessage =Driver.getDriver().findElement(By.xpath("//div[@placeholder='Write message, @ to mention someone …']"));
        String testMessage = faker.chuckNorris().fact();
        Driver.getDriver().findElement(By.xpath("//div[@placeholder='Write message, @ to mention someone …']")).sendKeys(testMessage);
        return testMessage;

    }
    public static void submitMessageToUser() {
        Actions act = new Actions(Driver.getDriver());
        BrowserUtils.sleep(2);
        act.moveToElement(Driver.getDriver().findElement(By.xpath("//button[@aria-label='Send message']"))).perform();
        Driver.getDriver().findElement(By.xpath("//button[@aria-label='Send message']")).click();

    }
    public static void verifyMessageInLog(String testMessage) {
        List<WebElement> listOfMessages = Driver.getDriver().findElements(By.xpath("//div[@class='message__main__text']"));
        // wait.until(ExpectedConditions.visibilityOf(listOfMessages.get(0)));
        List<String> text = new ArrayList<>();
        for (WebElement listOfMessage : listOfMessages) {
            text.add(listOfMessage.getText());
        }
        //message is  not submitted
        Assert.assertTrue(text.contains(testMessage));
    }
    //13
    public static void verifyContactsListSize() {
        int expectedSize = 2;

        int actualSize = Driver.getDriver().findElements(By.xpath("//div[@id='contacts-list']/div/div")).size();
        System.out.println("actualSize = " + actualSize);
        boolean sizeCheck = false;
        if(actualSize==2){
            Assert.assertFalse(sizeCheck);
        }
        Assert.assertFalse(expectedSize==actualSize);
    }



}
