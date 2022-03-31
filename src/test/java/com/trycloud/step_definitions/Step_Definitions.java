package com.trycloud.step_definitions;

import com.trycloud.pages.FilesModulePage;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static com.trycloud.utilities.TrycloudUtililities.clickModule;

public class Step_Definitions {
 LoginPage loginPage = new LoginPage();
    @Given("user on the login page {string}")
    public void user_on_the_login_page(String env) {
        Driver.getDriver().get(ConfigurationReader.getProperty(env));
    }
    @When("user uses username {string} and passcode {string} and clicks login button")
    public void user_uses_username_and_passcode_and_clicks_login_button(String username, String password) {
        loginPage.login(username,password);
    }
    @Then("verify the page title is {string}")
    public void verify_the_page_title_is(String title) {
        Assert.assertTrue( Driver.getDriver().getTitle().contains(title));
    }


    @When("user clicks the {string} module")
    public void user_clicks_the_module(String subModule) {
        FilesModulePage filesModulePage = new FilesModulePage();
        Map<String, WebElement> map = new HashMap<>() {{
            put("Favorites", filesModulePage.favorite);
            put("Deleted f", filesModulePage.deletedFiles);
            put("Settings", filesModulePage.settingBtn);
        }};

        clickModule(subModule, map);
    }
    public static void clickOnActionIconFromAnyFilesOnThePage(String option){
        String addToFavorites;
        FilesModulePage filesModulePage =new FilesModulePage();
        for (int i = 0; i < filesModulePage.actionIcon.size(); i++) {
            filesModulePage.actionIcon.get(i).click();
            try {
                filesModulePage.filesActionsMenu.findElement(By.xpath("//a[contains(.,'" + option + "')]")).isDisplayed();
                addToFavorites = filesModulePage.actualNamesOfFiles.get(i).getText();
                break;
            }catch (NoSuchElementException e){
                filesModulePage.actionIcon.get(i).click();
            }
        }
        filesModulePage.filesActionsMenu.findElement(By.xpath("//a[contains(.,'" + option + "')]")).click();
    }


}
