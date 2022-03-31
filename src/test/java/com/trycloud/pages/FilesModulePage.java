package com.trycloud.pages;

import com.trycloud.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilesModulePage {
    public FilesModulePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//label[@for='select_all_files']")
    public WebElement topCheckbox;

    @FindBy(xpath = "//td[@class='selection']")
   public List<WebElement> firstTableColumn;
    //5
    @FindBy(xpath = "//div[@id='app-content-files']//div[contains(@class, 'fileActionsMenu')]")
    public WebElement filesActionsMenu;

    @FindBy(xpath = "//td//a[@data-action='menu']")
   public List<WebElement> actionIcon;

    @FindBy(xpath = "//a//span[contains(.,'Add to favorites')]")
   public WebElement addToFavorites;
    //6
    @FindBy(xpath = "//a//span[contains(.,'Remove from favorites')]")
   public WebElement removeFromFavorites;

    @FindBy(xpath = "//a[contains(.,'Favorites')]")
   public WebElement favorite;
    //6
    @FindBy(xpath = "//div[@id='app-content-files']//*[@class='innernametext']")
   public List <WebElement> actualNamesOfFiles;
    //6 /7 /8 /9
    @FindBy(xpath = "//span[@class='icon icon-add']")
   public WebElement addIcon;
    //7
    @FindBy(xpath = "//span[.='Upload file']")
   public WebElement uploadFiles;

    @FindBy(xpath = "//span[.='New folder']")
   public WebElement newFolder;

    @FindBy(xpath = "//input[@id='view40-input-folder']")
   public WebElement inputFolderName;

    @FindBy(xpath = "//input[@class='icon-confirm']")
   public WebElement submitIcon;
    //8
    @FindBy(xpath = "//span[contains(.,'Delete file')]")
   public WebElement deleteFile;
    @FindBy(xpath = "//a[contains(.,'Deleted files')]")
   public WebElement deletedFiles;
    //9
    @FindBy(xpath = "//span[.='Details']")
    public WebElement detailsOption;

    @FindBy(xpath = "//a[contains(@id,'commentsTabView')]")
   public WebElement commentsBtn;

    @FindBy(xpath = "//div[@class='message']")
   public WebElement commentsInput;

    @FindBy(xpath = "//input[@class='submit icon-confirm has-tooltip']")
   public WebElement commentSubmitBtn;

    @FindBy(xpath = "//button[contains(.,'Settings')]")
    //10
   public WebElement settingBtn;
    @FindBy(xpath = "//div[@id='app-settings-content']//input[@type='checkbox']")
   public WebElement settingOptions;
    @FindBy(xpath = "//a[@class='icon-quota svg']/p")
   public WebElement usedStorageParagraph;
}
