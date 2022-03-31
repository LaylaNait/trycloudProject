package com.trycloud.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class WebElementMethods {

    public static WebElement getDescendent(WebElement ancestor, By by){

        return ancestor.findElement(by);
    }

    public static WebElement getDescendent(WebElement ancestor, String descTag, String descendent){
        return ancestor.findElement(By.xpath("//" + descTag + "[contains(., '" + descendent + "')]"));
    }

    public static List<WebElement> getDescendents(WebElement ancestor, By by){
        return ancestor.findElements(by);
    }


    /**
     * Verifies if an item identified by its name (String) is in a list (WebElements)
     * @param wl : list of web elements
     * @param s : string identifying the item
     * @return : boolean
     */
    public static boolean isItemDisplayed(WebDriver driver, List<WebElement> wl, String s) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        List<String> sl = new ArrayList<>();

        for (WebElement e : wl){
            jse.executeScript("arguments[0].scrollIntoView(true);", e);
            sl.add(e.getText());
        }

        return sl.contains(s);
    }

    public static List<String> getText(List<WebElement> wl){

        List<String> sl = new ArrayList<>();
        for (WebElement e : wl)
            sl.add(e.getText());
        return sl;
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

    public static void clickItem(WebElement ancestor, String descTag, String name){

        ancestor.findElement(By.xpath("//" + descTag + "[contains(., '" + name + "')]")).click();
    }
}
