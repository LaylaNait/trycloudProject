Feature: As a user, I should be able to update settings.

  Background:
    Given user on the dashboard page
    When user clicks the "Files" module

  Scenario: Verify users are able to update settings
    And user clicks the "Settings" sub-module on the left side
    Then user should be able to click any button
    And logout

  Scenario: Verify users are able to see the app storage usage
    And user checks the current storage usage
    And user clicks the add icon on the top
    And user clicks the "Upload file" option
    And user uploads a file "TestUpload001.png"
    And user refreshes the page
    Then user should be able to see storage usage is increased
    And logout
