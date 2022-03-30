package com.trycloud.utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
//By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")
public class TrycloudUtililities {
    public static void verifyRemovedFromFavorites(String removed,By locator) {
        String actualFavorite = "";
        for (int i = 0; i < Driver.getDriver().findElements(locator).size(); i++) {
            if (!Driver.getDriver().findElements(locator).get(i).getText().equalsIgnoreCase(removed)) {
                actualFavorite = Driver.getDriver().findElements(locator).get(i).getText();
                // act.moveToElement(Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i)).perform();
                // Driver.getDriver().findElements(By.xpath("//td//a[@data-action='menu']")).get(i).click();
                // dashboardPage.removeFromFavourites.click();
            }
        }
        System.out.println("removed = " + removed);
        System.out.println("actualFavorite = " + actualFavorite);
        Assert.assertFalse(removed.equalsIgnoreCase(actualFavorite));


    }
}
