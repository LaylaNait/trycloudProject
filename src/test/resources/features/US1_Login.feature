Feature: login with valid credentials
  @login
  Scenario Outline: Verify login with valid credentials
    Given user on the login page "env"
    When user uses username "<username>" and passcode "<password>" and clicks login button
    Then verify the page title is "Dashboard - Trycloud"
    Examples:
      | username | password    |
      | user7    | Userpass123 |
      | user34   | Userpass123 |
      | user99   | Userpass123 |
