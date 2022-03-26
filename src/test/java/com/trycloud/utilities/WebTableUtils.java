package com.trycloud.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class WebTableUtils {

  /*  Method #1 info:
            • Name: returnOrderDate ()
• Return type: String
• Arg1: WebDriver driver
• Arg2: String costumerName
    This method should accept a costumerName and return the costumer order date
    as a String.
*/

  public static String returnOrderData(WebDriver driver, String customerName){
      String locator = "//td[.='"+customerName+"']/following-sibling::td[3]";
      WebElement customerDateCell=driver.findElement(By.xpath(locator));
      return customerDateCell.getText();

  }

public static void orderVerify(WebDriver driver, String customerName, String expectedOrderDate){

    String locator = "//td[.='"+customerName+"']/following-sibling::td[3]";
    WebElement customerDateCell=driver.findElement(By.xpath(locator));
    String actualOrderDate =customerDateCell.getText();
    Assert.assertEquals(actualOrderDate,expectedOrderDate);
}




}
