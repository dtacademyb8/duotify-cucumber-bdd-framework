@ui
Feature: User sign up feature
  As an unregistered user, I should be able to signup using signup page.




  @smoke @regression @signup @DT_52365
  Scenario: Non-registered user signup positive
    Given I am on the homepage
    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully

 @signup
  Scenario: Non-registered user signup negative
     Given I am on the homepage
     When I navigate to signup page and enter invalid credentials
     Then I should not be able to sign up


  @smoke
  Scenario: Non-registered user signup no credentials
    Given I am on the homepage
    When I navigate to signup page and enter no credentials
    Then I should not be able to sign up




