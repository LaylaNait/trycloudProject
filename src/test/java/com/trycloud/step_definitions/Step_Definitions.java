package com.trycloud.step_definitions;

import com.trycloud.pages.FilesModulePage;
import com.trycloud.pages.LandingPages;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trycloud.utilities.TrycloudUtililities.clickModule;


public class Step_Definitions { 
 LoginPage loginPage = new LoginPage();

public class Step_Definitions {
    LoginPage loginPage = new LoginPage();
    FilesModulePage filesModulePage = new FilesModulePage();
    LandingPages landingPages = new LandingPages();
    Waiter waiter = new Waiter(Driver.getDriver());


    @Given("user on the login page {string}")
    public void user_on_the_login_page(String env) {
        Driver.getDriver().get(ConfigurationReader.getProperty(env));
    }

    @When("user uses username {string} and passcode {string} and clicks login button")
    public void user_uses_username_and_passcode_and_clicks_login_button(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("verify the page title is {string}")
    public void verify_the_page_title_is(String title) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(title));
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

    public static void clickOnActionIconFromAnyFilesOnThePage(String option) {
        String addToFavorites;
        FilesModulePage filesModulePage = new FilesModulePage();
        for (int i = 0; i < filesModulePage.actionIcon.size(); i++) {
            filesModulePage.actionIcon.get(i).click();
            try {
                filesModulePage.filesActionsMenu.findElement(By.xpath("//a[contains(.,'" + option + "')]")).isDisplayed();
                addToFavorites = filesModulePage.actualNamesOfFiles.get(i).getText();
                break;
            } catch (NoSuchElementException e) {
                filesModulePage.actionIcon.get(i).click();
            }
        }
        filesModulePage.filesActionsMenu.findElement(By.xpath("//a[contains(.,'" + option + "')]")).click();
    }




    //US8

    String fileToBeDeleted;

    @Given("user on the dashboard page")
    public void userOnTheDashboardPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
        loginPage.loginWithConfigurationProp();
    }

    @When("the user clicks the {string} module")
    public void theUserClicksTheModule(String moduleName) {
        TrycloudUtililities.clickItem(Driver.getDriver(), waiter, landingPages.topMenuWithAllModules, moduleName);
    }

    @And("user clicks action-icon from any file on the page")
    public void userClicksActionIconFromAnyFileOnThePage() {
        for (int i = 0; i < filesModulePage.actionIcon.size(); i++) {
            BrowserUtils.sleep(2);
            fileToBeDeleted = filesModulePage.actualNamesOfFiles.get(0).getText();
            filesModulePage.actionIcon.get(0).click();
            break;
        }
    }

    @And("user chooses the {string} option")
    public void userChoosesTheOption(String option) {
        BrowserUtils.sleep(3);
        filesModulePage.actionIcon.get(0).findElement(By.xpath("//a[contains(.,'" + option + "')]")).click();
    }

    @When("user clicks the {string} sub-module on the left side")
    public void userClicksTheSubModuleOnTheLeftSide(String subModule) throws InterruptedException {
        filesModulePage.filesLeftSideSubmodulesList.findElement(By.xpath("//a[contains(.,'" + subModule + "')]")).click();
    }

    @Then("verifies the deleted file is displayed on the page")
    public void verifiesTheDeletedFileIsDisplayedOnThePage() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(20000, 0);");
        js.executeScript("window.scrollBy(20000, 0);");


        List<String> filesNameInTrash = new ArrayList<>();
        for (int i = 0; i < filesModulePage.allTrashBinFiles.size(); i++) {
            js.executeScript("arguments[0].scrollIntoView(true);", filesModulePage.allTrashBinFiles.get(i));
            js.executeScript("window.scrollBy(1000, 0);");
            filesNameInTrash.add(filesModulePage.allTrashBinFiles.get(i).getText());

        }
        Assert.assertTrue(filesNameInTrash.contains(fileToBeDeleted));
    }


}
