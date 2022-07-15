Feature: User sign up feature
  As an unregistered user, I should be able to signup using signup page.


  Scenario: Non-registered user signup
    Given I am on the homepage
    When I navigate to signup page and enter valid credentials
    Then I should be able to sign up successfully
