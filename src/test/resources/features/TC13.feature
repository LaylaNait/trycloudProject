Feature: all 14 features
  #1
  Scenario Outline: Verify login with valid credentials
    Given user on the login page
    When user use username "<username>" and "<password>"
    And user click the login button
    Then verify the user should be at the "Dashboard - Trycloud" page
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
    #2 negative testing
  Scenario Outline: Verify user login fail with invalid credentials
    Given user on the login page
    When user enter invalid "<username>" and "<password>"
    And user click the login button
    Then verify "<message>" should be displayed
    Examples:
      | username | password    | message                     |
      | User9    | Wrong       | Wrong username or password. |
      | Wrong    | Userpass123 | Wrong username or password. |
      | Wrong    | Wrong       | Wrong username or password. |
#3
  Scenario Template: Verify users accessing all the main modules of the app.
    Given user on the login page
    When user use username "<username>" and "<password>"
    And user click the login button
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
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
#4
  Scenario Template: verify users can access to Files module
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    Then verify the page title is "Files - Trycloud"
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |


  Scenario Template: verify users can select all the uploaded files from the page
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user click the top-left checkbox of the table
    Then verify all the files are selected
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
#5
  Scenario Template: Verify users to add files to Favorites
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    When the user clicks action-icon from any file on the page
    And user choose the Add to favorites option
    And user click the Favorites sub-module on the left side
    Then Verify the chosen file is listed on the table
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
      #6
  Scenario Template: verify users to remove files to Favorites
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    When the user clicks action-icon from any file on the page to Remove
    And user choose the Remove from favorites option
    And user click the Favorites sub-module on the left side
    Then Verify that the file is removed from the Favorites sub-module’s table
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
      #6
  Scenario Template: verify users to upload a file from Files
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    #When the user clicks the add icon on the top
    And user uploads "<fileToUpload>" with the upload file option
    # files to  upload
    Then Verify the "<fileName>" is displayed on the page with files
    Examples:
      | username | password    | fileToUpload                                                                     | fileName              |
      | user4    | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\Office_Hours_12012021.txt | Office_Hours_12012021 |
      | user34   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\Framework.png             | Framework             |
      | user64   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\forLoopExercises.java     | forLoopExercises      |
      | user94   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\forLoopExercises.java     | forLoopExercises      |

#7
  Scenario Template: As a user, I should be able to add the folder
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user clicks the add icon on the top
    And user click New Folder
    And user write a folder name
    When the user click submit icon
    Then Verify the folder folder name is displayed on the page
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |

  Scenario Template: As a user, I should be able to upload a file inside a folder
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user choose a folder from the page
    #And user clicks the add icon on the top
    When the user uploads a "<fileToUpload>" with the upload file option
    Then Verify the "<fileName>" is displayed on the page
    Examples:
      | username | password    | fileToUpload                                                                     | fileName              |
      | user4    | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\Office_Hours_12012021.txt | Office_Hours_12012021 |
      | user34   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\Framework.png             | Framework             |
      | user64   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\forLoopExercises.java     | forLoopExercises      |
      | user94   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\\filesToUploadTC7\\forLoopExercises.java     | forLoopExercises      |
#8
  Scenario Template: Verify users delete a file/folder
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user click action-icon from any file on the page to Delete file or folder
    And user choose the Delete file option
    When the user clicks the Deleted files sub-module on the left side
    Then Verify the deleted file is displayed on the page.
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
#9
  Scenario Template: Verify users to write comments to files/folder
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user click action-icon from any file on the page
    And user choose the Details option
    And user write a comment inside the input box
    And user click the submit button to post it
    Then Verify the comment is displayed in the comment section
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
    #10
  Scenario Template: Verify users update settings
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user clicks Settings on the left bottom corner
    Then the user should be able to click any buttons
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |

  Scenario Template: Verify users to see the app storage usage
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Files module
    And user checks the current storage usage
    And user picks uploads "<fileToUpload>" with the upload file option
    And user refresh the page
    Then the user should be able to see storage usage is increased
    Examples:
      | username | password    | fileToUpload                                        |
      | user4    | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\QA\\4Lambda.pdf |
      | user34   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\QA\\4Lambda.pdf |
      | user64   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\QA\\4Lambda.pdf |
      | user94   | Userpass123 | C:\\Users\\nateb\\OneDrive\\Desktop\QA\\4Lambda.pdf |
    #11
  Scenario Template: verify users to access to Talks module
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Talk module
    Then verify the page title is "Talk - Trycloud"
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |

  Scenario Template: verify users to send a message
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Talk module
    And user search "<user>" from the search box
    And user write a message
    And user clicks to submit button
    Then the user should be able to see the message is displayed on the conversation log
    Examples:
      | username | password    | user      |
      | user4    | Userpass123 | test_user |
      | user34   | Userpass123 | test_user |
      | user64   | Userpass123 | test_user |
      | user94   | Userpass123 | test_user |
    #12
  Scenario Template: verify user access to Talks module
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Contacts module
    Then verify the page title is "Contacts - Trycloud"
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
    #13
  Scenario Template: verify users can see all the contact names on the contact list
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the Contacts module
    Then verify the contact names are in the list
#(Pre-condition: there should be at least 2 contact names are displayed
#On the contact list so that view list function can be tested)
    Examples:
      | username | password    |
      | user4    | Userpass123 |
      | user34   | Userpass123 |
      | user64   | Userpass123 |
      | user94   | Userpass123 |
    #14
  Scenario Template: Verify users can search any files/folder/users from the search box.
    Given "<username>" "<password>" on the dashboard page
    When the user clicks the magnifier icon on the right top
    And users search any existing "<file/folder/user>" name
    Then verify the app displays the "<expectedResult>" option
    Examples:
      | username | password    | file/folder/user | expectedResult |
      | user4    | Userpass123 | test_user        | test_user      |
      | user34   | Userpass123 | test_user        | test_user      |
      | user64   | Userpass123 | test_user        | test_user      |
      | user94   | Userpass123 | test_user        | test_user      |