Feature: login with invalid credentials
Scenario Outline: Verify user login fail with invalid credentials
  Given user on the login page "env"
  When user uses username "<username>" and passcode "<password>" and clicks login button
Then verify "<message>" message should be displayed
Examples:
  | username | password    | message                     |
  | User9    | Wrong       | Wrong username or password. |
  | Wrong    | Userpass123 | Wrong username or password. |
  | Wrong    | Wrong       | Wrong username or password. |