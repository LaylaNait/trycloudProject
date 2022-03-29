package com.trycloud.step_definitions;

import com.github.javafaker.Faker;
import com.trycloud.pages.FilesModulePage;
import com.trycloud.pages.LoginPage;
import com.trycloud.utilities.BrowserUtils;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.Driver;
import com.trycloud.utilities.methodsNaz.Methods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Step_Defs_Naz {
    LoginPage loginPage = new LoginPage();
    FilesModulePage filesModulePage = new FilesModulePage();
    Faker faker = new Faker();
    String folderName = faker.cat().name();


    @Given("user on the login page")
    public void user_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));
    }

    @When("user use username {string} and {string}")
    public void user_use_username_and(String username, String password) {
        loginPage.login(username, password);
    }

    @When("user click the login button")
    public void user_click_the_login_button() {
        //login.click inside of login method

    }

    @Then("verify the user should be at the {string} page")
    public void verify_the_user_should_be_at_the_page(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }


    @When("user enter invalid {string} and {string}")
    public void userEnterInvalidAnd(String username, String password) {
        loginPage.login(username, password);
    }


    @Then("verify {string} should be displayed")
    public void verifyShouldBeDisplayed(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.errorMessage.getText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);


    }


    @Then("Verify the user see the following modules:")
    public void verifyTheUserSeeTheFollowingModules(List<String> expectedModuleNames) {
        List<String> actualModuleNames = Methods.getElementsTextDashboard();
        Assert.assertEquals(expectedModuleNames, actualModuleNames);

    }

    @Given("{string} {string} on the dashboard page")
    public void onTheDashboardPage(String username, String password) {
        Methods.logIn(username, password);
    }

    @When("the user clicks the Files module")
    public void theUserClicksTheFilesModule() {
        Methods.hover(filesModulePage.filesModule);
        filesModulePage.filesModule.click();

    }

    @Then("verify the page title is {string}")
    public void verifyThePageTitleIs(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @And("user click the top-left checkbox of the table")
    public void userClickTheTopLeftCheckboxOfTheTable() {
        filesModulePage.topCheckbox.click();
    }

    @Then("verify all the files are selected")
    public void verifyAllTheFilesAreSelected() {
        Assert.assertTrue(Methods.verifyAllSelected());
    }

    String addedFileName;

    @When("the user clicks action-icon from any file on the page")
    public void theUserClicksActionIconFromAnyFileOnThePage() {
        addedFileName = Methods.clickOnActionDots();
    }

    @And("user choose the Add to favorites option")
    public void userChooseTheAddToFavoritesOption() {
        filesModulePage.addToFavorites.click();
    }

    @And("user click the Favorites sub-module on the left side")
    public void userClickTheFavoritesSubModuleOnTheLeftSide() {
        filesModulePage.favorite.click();
    }

    @Then("Verify the chosen file is listed on the table")
    public void verifyTheChosenFileIsListedOnTheTable() {
        Methods.verifyChosenFile(addedFileName);
    }

    String fileToRemove;

    @When("the user clicks action-icon from any file on the page to Remove")
    public void theUserClicksActionIconFromAnyFileOnThePageToRemove() {
        fileToRemove = Methods.chooseFavoriteAndRemove();
    }

    @And("user choose the Remove from favorites option")
    public void userChooseTheRemoveFromFavoritesOption() {
        filesModulePage.removeFromFavorites.click();
    }

    @Then("Verify that the file is removed from the Favorites sub-module’s table")
    public void verifyThatTheFileIsRemovedFromTheFavoritesSubModuleSTable() {
        Methods.verifyRemovedFromFavorites(fileToRemove);
    }

    @And("user uploads {string} with the upload file option")
    public void userUploadsWithTheUploadFileOption(String fileToUpload) {
        Methods.uploadFIleToFiles(fileToUpload);
    }

    @Then("Verify the {string} is displayed on the page with files")
    public void verifyTheIsDisplayedOnThePageWithFiles(String fileName) {
        Methods.verifyFileUploadedToFiles(fileName);
    }

    @And("user clicks the add icon on the top")
    public void userClicksTheAddIconOnTheTop() {
        filesModulePage.addIcon.click();
    }

    @And("user click New Folder")
    public void userClickNewFolder() {
        filesModulePage.newFolder.click();
    }


    @And("user write a folder name")
    public void userWriteAFolderName() {
        Driver.getDriver().findElement(By.xpath("//input[@value='New folder']")).clear();
        BrowserUtils.sleep(3);
        Driver.getDriver().findElement(By.xpath("//input[@value='New folder']")).sendKeys(folderName);
        // filesModulePage.inputFolderName.sendKeys(Keys.DELETE);
        //  filesModulePage.inputFolderName.clear();
        //filesModulePage.inputFolderName.sendKeys(folderName);
    }

    @When("the user click submit icon")
    public void theUserClickSubmitIcon() {
        Driver.getDriver().findElement(By.xpath("//input[@value='New folder']")).sendKeys(Keys.ENTER);
        BrowserUtils.sleep(3);
        // filesModulePage.submitIcon.clear();
    }

    @Then("Verify the folder folder name is displayed on the page")
    public void verifyTheFolderFolderNameIsDisplayedOnThePage() {
        List<String> result = Methods.getElementsText(Driver.getDriver().findElements
                (By.xpath("//td//a[@data-action='menu']/../../span/span[@class='innernametext']")));

        System.out.println("result = " + result);
        //JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        // js.executeScript("arguments[0].scrollIntoView()",Driver.getDriver().findElement(By.xpath("//td[@class='filesummary']")));
        Assert.assertTrue(result.contains(folderName));
    }


    @And("user choose a folder from the page")
    public void userChooseAFolderFromThePage() {
        Methods.clickAnyFolder();
    }

    @When("the user uploads a {string} with the upload file option")
    public void theUserUploadsAWithTheUploadFileOption(String fileToUpload) {
        BrowserUtils.sleep(3);
        Driver.getDriver().findElement(By.xpath("//input[@id='file_upload_start']")).sendKeys(fileToUpload);
    }

    @Then("Verify the {string} is displayed on the page")
    public void verifyTheIsDisplayedOnThePage(String fileName) {
        Methods.verifyUploadFile(fileName);
    }

    String fileFolderRemoved;


    @And("user click action-icon from any file on the page to Delete file or folder")
    public void userClickActionIconFromAnyFileOnThePageToDeleteFileOrFolder() {
        fileFolderRemoved = Methods.chooseFileFolderAndRemove();
    }

    @And("user choose the Delete file option")
    public void userChooseTheDeleteFileOption() {
        Driver.getDriver().findElement(By.xpath("//li[@class=' action-delete-container']")).click();
    }

    @When("the user clicks the Deleted files sub-module on the left side")
    public void theUserClicksTheDeletedFilesSubModuleOnTheLeftSide() {
        Driver.getDriver().findElement(By.xpath("//a[.='Deleted files']")).click();
    }

    @Then("Verify the deleted file is displayed on the page.")
    public void verifyTheDeletedFileIsDisplayedOnThePage() {
        Methods.verifyDeletedFile(fileFolderRemoved);
    }

    @And("user click action-icon from any file on the page")
    public void userClickActionIconFromAnyFileOnThePage() {
        Methods.clickOnFile(2);
    }

    @And("user choose the Details option")
    public void userChooseTheDetailsOption() {
        filesModulePage.detailsOption.click();
    }

    String inputMessage;

    @And("user write a comment inside the input box")
    public void userWriteACommentInsideTheInputBox() {
        inputMessage = Methods.userWritesComment();
    }

    @And("user click the submit button to post it")
    public void userClickTheSubmitButtonToPostIt() {
        //submit comment
        Driver.getDriver().findElement(By.xpath("//input[@data-original-title='Post']")).click();
    }

    @Then("Verify the comment is displayed in the comment section")
    public void verifyTheCommentIsDisplayedInTheCommentSection() {
        Methods.verifyCommentIsDisplayed(inputMessage);
    }

    @And("user clicks Settings on the left bottom corner")
    public void userClicksSettingsOnTheLeftBottomCorner() {
        filesModulePage.settingBtn.click();
    }

    @Then("the user should be able to click any buttons")
    public void theUserShouldBeAbleToClickAnyButtons() {
        //ShowRichWorkspaces
        Driver.getDriver().findElement(By.xpath("//div[@id='files-setting-richworkspace']")).click();
        //Show recommendations
        Driver.getDriver().findElement(By.xpath("//div[@id='recommendations-setting-enabled']")).click();
        //Show hidden files
        Driver.getDriver().findElement(By.xpath("//div[@id='files-setting-showhidden']")).click();
        //viaWebDAV
        Driver.getDriver().findElement(By.xpath("//a[.='Use this address to access your Files via WebDAV ↗']")).click();

    }

    String initialUsageAmount;

    @And("user checks the current storage usage")
    public void userChecksTheCurrentStorageUsage() {
        /*
        @FindBy(xpath = "//a[@class='icon-quota svg']/p")
            public WebElement usageAmount;
         */
        initialUsageAmount = Driver.getDriver().findElement(By.xpath("//a[@class='icon-quota svg']/p")).getText();
    }

    @And("user picks uploads {string} with the upload file option")
    public void userPicksUploadsWithTheUploadFileOption(String fileToUpload) {
        Methods.uploadFile(fileToUpload);
    }

    @And("user refresh the page")
    public void userRefreshThePage() {
        Driver.getDriver().navigate().refresh();
    }

    @Then("the user should be able to see storage usage is increased")
    public void theUserShouldBeAbleToSeeStorageUsageIsIncreased() {
        String actualUsageAmount=Driver.getDriver().findElement(By.xpath("//a[@class='icon-quota svg']/p")).getText();
        Assert.assertTrue(!initialUsageAmount.equals(actualUsageAmount));
        System.out.println("initialUsageAmount = " + initialUsageAmount);
        System.out.println("actualUsageAmount = " + actualUsageAmount);
    }

    @When("the user clicks the Talk module")
    public void theUserClicksTheTalkModule() {
        //Talks module

        WebElement TalkModule = Driver.getDriver().findElement(By.xpath("//ul[@id='appmenu']/li[@data-id='spreed']"));
        Methods.hover(TalkModule);
        TalkModule.click();
    }

    @And("user search {string} from the search box")
    public void userSearchFromTheSearchBox(String user) {
        Methods.searchForUser(user);
    }
    String messageText;

    @And("user write a message")
    public void userWriteAMessage() {
        messageText=Methods.writeMessage();
    }

    @And("user clicks to submit button")
    public void userClicksToSubmitButton() {
        Methods.submitMessageToUser();
    }

    @Then("the user should be able to see the message is displayed on the conversation log")
    public void theUserShouldBeAbleToSeeTheMessageIsDisplayedOnTheConversationLog() {
        Methods.verifyMessageInLog(messageText);
    }

    @When("the user clicks the Contacts module")
    public void theUserClicksTheContactsModule() {
        /*
         @FindBy(xpath = "//ul[@id='appmenu']/li[@data-id='contacts']")
    public WebElement ContactsModule;
         */
        Methods.hover(Driver.getDriver().findElement(By.xpath("//ul[@id='appmenu']/li[@data-id='contacts']")));
        Driver.getDriver().findElement(By.xpath("//ul[@id='appmenu']/li[@data-id='contacts']")).click();
    }

    @Then("verify the contact names are in the list")
    public void verifyTheContactNamesAreInTheList() {
        Methods.verifyContactsListSize();
    }

    @When("the user clicks the magnifier icon on the right top")
    public void theUserClicksTheMagnifierIconOnTheRightTop() {
        /*
        @FindBy(xpath = "//span[@aria-label='Magnify icon']")
    public WebElement magnifierIcon;
         */
        Driver.getDriver().findElement(By.xpath("//span[@aria-label='Magnify icon']")).click();
    }
    String actualSearchQ;

    @And("users search any existing {string} name")
    public void usersSearchAnyExistingName(String searchQuery) {
        actualSearchQ=searchQuery;
        /*
        @FindBy(xpath = "//input[@type='search']")
    public WebElement searchInput;
         */
       Driver.getDriver().findElement(By.xpath("//input[@type='search']")).sendKeys(searchQuery);
    }

    @Then("verify the app displays the {string} option")
    public void verifyTheAppDisplaysTheOption(String expectedResult) {
        //check each user files and users for extensive search
        Assert.assertEquals(expectedResult,actualSearchQ);
    }
}
