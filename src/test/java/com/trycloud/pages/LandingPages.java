package com.trycloud.pages;

import com.trycloud.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPages {

    public LandingPages() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

     //3
    @FindBy(xpath = "//*[@id='appmenu']/li[@data-id]")
   public List <WebElement> topMenuWithAllModules;
    //14
    @FindBy(xpath = "//*[@id='header']//a/span[contains(.,'Magnify icon')]")
    public WebElement homePageSearchBtn;
    @FindBy(xpath = "//input[@type='search']")
    public   WebElement homePageSearchBox;
    @FindBy(xpath = "//li/a[@class='unified-search__result unified-search__result--focused']")
    public WebElement expectedResultOption;
    @FindBy(xpath = "//div[@id='expand']")
    public WebElement userBtn;
    @FindBy(xpath = "//a[contains(.,'Log out')]")
    WebElement logOutBtn;

    public void logout(){
        userBtn.click();
        logOutBtn.click();
    }
}
