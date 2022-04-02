Feature: As a user, I should be accessing all the main modules of the app.

  @wip
  Scenario Outline: Verify users accessing all the main modules of the app.
    Given user on the login page.
    When user uses username "<username>" and passcode "<password>" and clicks login button
    Then Verify the user see the following modules:
      | Dashboard |
      | Files     |
      | Photos    |
      | Activity  |
      | Talk      |
      | Contacts  |
      | Circles   |
      | Calendar  |
      | Deck      |

    And close browser

    Examples:
      | username | password    |
      | user7    | Userpass123 |
      | user34   | Userpass123 |
      | user99   | Userpass123 |