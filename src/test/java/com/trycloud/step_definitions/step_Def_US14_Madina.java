package com.trycloud.step_definitions;

import com.trycloud.pages.LandingPages;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

public class step_Def_US14_Madina {

    LandingPages landingPages = new LandingPages();

    @Given("user on the dashboard page")
    public void user_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
        new  LoginPage().loginWithConfigurationProp();

    }
    @When("the user clicks the magnifier icon      on the right top")
    public void the_user_clicks_the_magnifier_icon_on_the_right_top() {
        landingPages.homePageSearchBtn.click();

    }
    @When("users search any existing")
    public void users_search_any_existing() {
        landingPages.homePageSearchBox.sendKeys("Calendar"+ Keys.ENTER);

    }
    @Then("verify the app displays the expected result option")
    public void verify_the_app_displays_the_expected_result_option() {
        landingPages.expectedResultOption.isDisplayed();

    }
    @And("close browser")
    public void closeBrowser() {
        Driver.closeDriver();
    }





}



