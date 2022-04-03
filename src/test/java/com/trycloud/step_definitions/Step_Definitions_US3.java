package com.trycloud.step_definitions;

import com.trycloud.pages.LandingPages;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Step_Definitions_US3 {

    LandingPages landingPages = new LandingPages();
    LoginPage loginPage = new LoginPage();

    @Given("user on the login page.")
    public void userOnTheLoginPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
    }

    @When("user uses username {string} and passcode {string} and clicks login button")
    public void userUsesUsernameAndPasscodeAndClicksLoginButton(String username, String password) {
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginBtn.click();
    }

    @Then("Verify the user see the following modules:")
    public void verify_the_user_see_the_following_modules(List<String> expectedModules) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(landingPages.topMenuWithAllModules.get(0)).perform();
        List<String> actualModules = new ArrayList<>();

        for (WebElement each : landingPages.topMenuWithAllModules) {
            actualModules.add(each.getText());
        }
        System.out.println("actualModules = " + actualModules);
        Assert.assertEquals(expectedModules, actualModules);

    }


    @And("close browser")
    public void closeBrowser() {
        Driver.closeDriver();
    }
}
