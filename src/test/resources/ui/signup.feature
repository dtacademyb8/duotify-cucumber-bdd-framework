@ui
Feature: User sign up feature
  As an unregistered user, I should be able to signup using signup page.


  Background:
    Given I am on the homepage




  @smoke @regression @signup @DT_52365
  Scenario: Non-registered user signup positive

    When I navigate to signup page and enter valid credential
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


  Scenario: Non-registered user signup positive datatable list of lists

    When I navigate to signup page
    And  I enter the following credentials
      | username  | firstname | lastname | email               | password      |
      | spiderman | Spider    | Man      | spiderman@gmail.com | spiderman2022 |
    Then I should be able to sign up successfully


  Scenario: Non-registered user signup positive datatable list of maps

    When I navigate to signup page
    And  I enter the following credentials as list of maps
      | username  | firstname | lastname | email               | password      |
      | spiderman1 | Spider    | Man      | spiderman1@gmail.com | spiderman2022 |
    Then I should be able to sign up successfully




    Scenario:  Example scenario to demonstrate datatable

      Given I am on the homepage
      When I pass this information as list of maps

        | firstName   | lastName | birthDate  |
        | Annie M. G. | Schmidt  | 1911-03-20 |
        | Roald       | Dahl     | 1916-09-13 |
        | Astrid      | Lindgren | 1907-11-14 |


      Then I should not be able to sign up




  Scenario:  Example scenario to demonstrate datatable

    Given I am on the homepage
    When I pass this information as maps

      | KMSY | Louis Armstrong New Orleans International Airport    |
      | KSFO | San Francisco International Airport               	|
      | KSEA | Seattle-Tacoma International Airport              	|
      | KJFK | John F. Kennedy International Airport             	|


    Then I should not be able to sign up



  Scenario:  Example scenario to demonstrate datatable

    Given I am on the homepage
    When I pass this information as map of <String, List<String>>

      | KMSY | 29.993333 |  -90.258056 |
      | KSFO | 37.618889 | -122.375000 |
      | KSEA | 47.448889 | -122.309444 |
      | KJFK | 40.639722 |  -73.778889 |



    Then I should not be able to sign up




  Scenario:  Example scenario to demonstrate datatable

    Given I am on the homepage
    When I pass this information as map of <String, Map<String,String>>

      |      | lat       | lon         |
      | KMSY | 29.993333 | -90.258056  |
      | KSFO | 37.618889 | -122.375000 |
      | KSEA | 47.448889 | -122.309444 |
      | KJFK | 40.639722 | -73.778889  |



    Then I should not be able to sign up


  @datatable
  Scenario:  Example scenario to demonstrate datatable

    Given I am on the homepage
    When I pass this information as list


      | KMSY |
      | KSFO |
      | KSEA |
      | KJFK |
      | HSSZ |
      | CSCS |
      | CSSC |
      | CSSC |



    Then I should not be able to sign up




   @scenario_outline_with_datatable
  Scenario Outline: Non-registered user signup positive datatable list of maps scenario outline

    When I navigate to signup page
    And  I enter the following credentials as list of maps
      | username   | firstname | lastname | email   | password |
      | <username> | <FIRST>   | <LAST>   | <EMAIL> | <PASS>   |
    Then I should be able to sign up successfully


    Examples:

      | username   | FIRST  | LAST | EMAIL                | PASS          |
      | spiderman3 | Spider | Man  | spiderman3@gmail.com | spiderman2022 |
      | batman3     | Bat    | Man  | batman3@gmail.com     | spiderman2022 |
      | robin3     | Robin  | Hood | robinhood3@gmail.com  | spiderman2022 |
      | superman3   | Super  | Man  | superman3@gmail.com   | spiderman2022 |
      | venom3      | Venom  | Man  | venom3@gmail.com      | spiderman2022 |
      | peppapig3   | Peppa  | Pig  | peppapig3@gmail.com   | spiderman2022 |
      | Joker3      | Joker  | Man  | joker3@gmail.com      | spiderman2022 |


   @data_driven_csv @smoke
  Scenario: Non-registered user signup data-driven testing using csv file

    When I navigate to signup page
    Then  I read the signup information from csv file and enter the details and should be able to sign up successfully

















