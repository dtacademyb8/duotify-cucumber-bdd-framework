Feature: A feature

  Background:
    Given I am on the homepage


  Scenario: A feature file scenario

    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully




    Scenario: Verify homepage album names
      Given I am on the homepage
      When I enter "lara.alissa" as a username and "lara12345" as password
      Then The names of the albums should be the following
        | Werk                |
        | Fenix               |
        | I Am...Sasha Fierce |
        | Ultimatum           |
        | Oscillation         |
        | Marisa              |
        | Clouds              |
        | Cruel Summer        |
        | Escape              |




