
Feature: User login feature


  Background:
    Given I am on the homepage





  Scenario: Registered user login positive
    When I enter lara.alissa as a username and lara12345 as password
    Then I should be able to login successfully


  @login1
  Scenario: Registered user login positive parametrized
    When I enter "lara.alissa" as a username and "lara12345" as password
    Then I should be able to login successfully


  @login
  Scenario: Registered user login positive parametrized
    When I enter "majd.aslan" as a username and "majd12345" as password
    Then I should be able to login successfully

  @login
  Scenario: Registered user login positive parametrized
    When I enter "halil.dikmen" as a username and "halil12345" as password
    Then I should be able to login successfully



    @parametrized
    Scenario:  scenario for passinf different parameter type
      Given I have 5 cucumbers
      When I add 20 more and change the weight to 1.8 pounds
      Then I should have 25 cucumbers

  @parametrized
  Scenario: Example scenario for passing different parameter type
    Given I have 105 cucumbers
    When I add 50 more and change the weight to 1.8 pounds
    Then I should have 155 cucumbers
    Given I have 5 pounds of cucumbers of type "Cuke" that cost me 5.99 dollars





    @so
  Scenario Outline: Registered user login positive scenario outline
    When I enter "<username>" as a username and "<password>" as password
    Then I should be able to login successfully and the name should be "<name>"

    Examples: valid user credentials
      | username    | password  | name            |
      | lara.alissa | lara12345 | Lara Alissa     |
      | duotechb8   | duotechb8 | Duotech Academy |
      | janedoe     | janedoe   | Jane Doe        |
      | johndoe     | johndoe   | John Doe        |
      | magdalena   | magdalena | Magdalena Nitek |
      | lara.alissa | lara12345 | Lara Alissa     |
      | duotechb8   | duotechb8 | Duotech Academy |
      | janedoe     | janedoe   | Jane Doe        |
      | johndoe     | johndoe   | John Doe        |
      | magdalena   | magdalena | Magdalena Nitek |




      @docstring
      Scenario: Verify database table
        When I send the following query
#           """
#            select first_name,last_name,email,address,district,city,country from customer
#            join address
#            on customer.address_id = address.address_id
#            join city
#            on address.city_id  = city.city_id
#            join country
#            on city.country_id = country.country_id;
#            """












