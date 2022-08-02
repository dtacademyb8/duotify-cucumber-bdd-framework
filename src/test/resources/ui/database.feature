Feature: Database features



  @db
  Scenario: Verify business logic of customers table
    When I retrieve the customers table info
    Then it should not contain duplicate customer emails


  @db
  Scenario: Verify business logic of users table
    When I retrieve the users table info
    Then it should not contain duplicate user emails




