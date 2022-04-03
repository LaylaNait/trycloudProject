package com.trycloud.step_definitions;

import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US1 {

    LoginPage LoginPage = new LoginPage();


    @When("user use username {string} and passcode {string} and user click on the login button")
    public void userUseUsernameAndPasscodeAndUserClickOnTheLoginButton(String string1, String string2) {
        LoginPage.login(string1, string2);
    }


    @Then("verify the user should be at the {string} page")
    public void verifyTheUserShouldBeAtThePage(String expectedText) {
        System.out.println(Driver.getDriver().getTitle());
        String actualTitlePage = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitlePage.contains(expectedText));


    }
}

