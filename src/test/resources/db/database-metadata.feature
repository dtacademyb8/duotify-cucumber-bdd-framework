Feature: Database metadata scenarios


  @db_only
  Scenario: Verify the column names for albums table
    When  I send a request to retrieve colum names for albums table
    Then It should be the following

      | id          |
      | title       |
      | artist      |
      | genre       |
      | artworkPath |

  @metadata @db_only
  Scenario: Verify the title column length of albums table
    When  I send a request to add a new title that is more than the expected length of 250
    Then  The data should be truncated to the expected length














  @data_mapping_db_to_ui
  Scenario: New User Creation, verify data mapping DB to UI flow
    When I add a new user to the database with the following info
      | username  | first | last | email               | password    |
      | peppapig3 | Peppa | Pig | peppapig3@gmail.com | peppapig99 |
    Then I should be able to log in with the same credentials on the UI
    And  The firstname, lastname and email also should be correct