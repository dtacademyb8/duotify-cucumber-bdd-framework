Feature: Business rule conformity


  @business_rule @db_only
  Scenario: Verify the genres
    When  I send a request to retrieve genres from genres table
    Then It should be the following expected genres

      | rap        |
      | pop        |
      | techno     |
      | rnb        |
      | house      |
      | classical  |
      | jazz       |
      | electronic |
      | dance      |
      | reggae     |
      | reggaeton  |

  @business @db_only
  Scenario: Verify the users table for unique usernames
    When  I send a request to retrieve duplicate usernames
    Then  The result should be empty














