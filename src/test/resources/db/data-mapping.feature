Feature: Data mapping scenarios


   @data_mapping_ui_to_db
  Scenario: New User Sign Up from UI to DB flow
    Given I am on the homepage
     And I navigate to signup page
    When  I enter the following credentials as list of maps
      | username | firstname | lastname | email            | password   |
      | ray.ban  | Ray       | Ban      | rayban@gmail.com | rayban2022 |
    Then I should be able to sign up successfully
    And The database should also have correctly mapped info
      | username | firstname | lastname | email            | password   |
      | ray.ban  | Ray       | Ban      | rayban@gmail.com | rayban2022 |







  Scenario: New User Creation from DB to UI flow
    Given I am connected to the DB
    When I add a new user to the database with the following info
      |username| first | last | email | password|
      |daisyduck| Daisy | Duck | daisyduck@gmail.com| daisyduck99|
    Then I should be able to log in with the "daisyduck" as uasername and "daisyduck99" as password on the UI