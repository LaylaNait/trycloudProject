package com.trycloud.utilities;

import com.trycloud.pages.FilesModulePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrycloudUtililities {

    public static void verifyRemovedFromFavorites(String removed,By locator) {
        String actualFavorite = "";
        for (int i = 0; i < Driver.getDriver().findElements(locator).size(); i++) {
            if (!Driver.getDriver().findElements(locator).get(i).getText().equalsIgnoreCase(removed)) {
                actualFavorite = Driver.getDriver().findElements(locator).get(i).getText();

            }
        }
        System.out.println("removed = " + removed);
        System.out.println("actualFavorite = " + actualFavorite);
        Assert.assertFalse(removed.equalsIgnoreCase(actualFavorite));
    }
    public static boolean isItemDisplayed(WebDriver driver, List<WebElement> wl, String s){

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        List<String> sl = new ArrayList<>();

        for (WebElement e : wl)
            sl.add(e.getText());
        return sl.contains(s);
    }
    /**
     * Clicks an item identified by its name (String) among a list (WebElements)
     * @param driver : WebDriver
     * @param waiter : Waiter
     * @param wl : list of web elements
     * @param name : string identifying the item
     */
    public static void clickItem(WebDriver driver, Waiter waiter, List<WebElement> wl, String name){
        Actions a = new Actions(driver);
        a.moveToElement(wl.get(0)).perform();
        for (int i = 0; i < wl.size(); i++){
            waiter.fluentWaitForElement(wl.get(i));
            a.moveToElement(wl.get(i)).perform();
            if (wl.get(i).getText().equals(name)){
                wl.get(i).click();
                break;
            }
        }
    }
    public static void uploadFile(String filePath, WebElement uploadLink, WebElement progressBar) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        BrowserUtils.sleep(5);

        uploadLink.sendKeys(filePath);

        wait.until(ExpectedConditions.invisibilityOf(progressBar));

    }
    public static List<String> getText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static void clickModule (String subModule,Map<String,WebElement>map){
     try {
         map.get(subModule).click();
     }catch (Exception e){
        e.printStackTrace();
    }}
   /* public static String chooseFileFolderAndRemove(List<WebElement> listOfTableNames) {
   = Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']"));
        String removed = "";
        Actions act = new Actions(Driver.getDriver());
        act.moveToElement(listOfTableNames.get(0)).perform();
        removed = listOfTableNames.get(0).getText();
        Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(0).click();
        BrowserUtils.sleep(2);

        return removed;
    }*/




    public static void searchForUser(String userName,WebElement userSearch) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        userSearch.sendKeys(userName);
        //  Driver.getDriver().findElement(By.xpath("//input[@placeholder='Search conversations or users']")).sendKeys(userName);
        String locatorForUserName = "//a[@aria-label='Conversation, " + userName + "']";
        WebElement searchResultWithUser = Driver.getDriver().findElement(By.xpath(locatorForUserName));

        wait.until(ExpectedConditions.visibilityOf(searchResultWithUser));

        searchResultWithUser.click();

    }

}

