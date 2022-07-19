@ui
Feature: User sign up feature
  As an unregistered user, I should be able to signup using signup page.


  Background:
    Given I am on the homepage




  @smoke @regression @signup @DT_52365
  Scenario: Non-registered user signup positive

    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully

 @signup
  Scenario: Non-registered user signup negative

     When I navigate to signup page and enter invalid credentials
     Then I should not be able to sign up
     Then I should not be able to sign up


  @smoke
  Scenario: Non-registered user signup no credentials

    When I navigate to signup page and enter no credentials
    Then I should not be able to sign up






