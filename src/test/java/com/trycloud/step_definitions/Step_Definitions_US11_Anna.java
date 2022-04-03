package com.trycloud.step_definitions;

import com.trycloud.pages.*;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import com.trycloud.utilities.TrycloudUtililities;
import com.trycloud.utilities.Waiter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step_Definitions_US11_Anna {

    LoginPage loginPage = new LoginPage();
    TalkModulePage talkModulePage = new TalkModulePage();
    LandingPages landingPages = new LandingPages();
    Waiter waiter = new Waiter(Driver.getDriver());


    @Given("user on the dashboard page")
    public void user_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
        loginPage.login(ConfigurationReader.getProperty("username1"),ConfigurationReader.getProperty("password"));
    }
    @Then("verify the page title is {string}")
    public void verify_the_page_title_is(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }

    @When("the user clicks the {string} module")
    public void the_user_clicks_the_module(String module) {
        TrycloudUtililities.clickItem(Driver.getDriver(),waiter, landingPages.topMenuWithAllModules,module);
    }


    @When("user searches {string} from the search box")
    public void user_search_user_from_the_search_box(String itemToSearch) {
        talkModulePage.usersNamesChat.get(0).findElement(By.xpath("//ul//a[contains(@aria-label,'Conversation, " + itemToSearch + "')]")).click();
    }

    @When("user writes a message {string}")
    public void user_write_a_message(String message) throws InterruptedException {
       // Thread.sleep(3000);
        talkModulePage.messageBox.sendKeys(message);
    }

    @When("user clicks to submit button")
    public void user_clicks_to_submit_button() throws InterruptedException {
       // Thread.sleep(3000);
        talkModulePage.submitBtnForChat.click();
    }
    @Then("user should be able to see the {string} is displayed on the conversation log")
    public void the_user_should_be_able_to_see_the_message_is_displayed_on_the_conversation_log(String message) {
        List<String> messages = new ArrayList<>();
        for(WebElement e : talkModulePage.messageOnConversationLog){
            messages.add(e.getText());
        }
        Assert.assertTrue(messages.contains(message));

    }
}
