Feature: A feature

  Background:
    Given I am on the homepage

  @smoke
  Scenario: A feature file scenario

    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully


  @signup
  Scenario: A feature file scenario 2

    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully




