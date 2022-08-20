Feature: Testing GitHub API


  Scenario: Verify User info update with Patch request

    Given The Base URI is set
    When  I send a PATCH request to "/user" endpoint with the following values
      | location | San Fransisco, CA |
      | company  | Duotify           |
      | hireable | false             |

    Then The response should contain the following

      | status code  | 200                             |
      | Content-Type | application/json; charset=utf-8 |
      | Server       | GitHub.com                      |
      | location     | San Fransisco, CA               |
      | company      | Duotify                         |
      | hireable     | null                            |



  Scenario: Verify Users endpoint results count
    Given The Base URI is set
    And The following headers are added
        |Accept| application/vnd.github+json|

    When I send a GET request to users endpoint
    Then The response should have 30 users


  Scenario: Verify Users endpoint results count

    Given The following headers are added
      |Accept| application/vnd.github+json|
    And the query parameter "per_page" is set to 60
    When I send a GET request to users endpoint
    Then The response should have 60 users



  Scenario: Verify Users endpoint per_page query param

    Given The following headers are added
      |Accept| application/vnd.github+json|
    And the query parameter "per_page" is set to 60
    When I send a GET request to users endpoint
    Then The response should have 60 users

  @api
  Scenario: Verify Users endpoint since query param

    Given The following headers are added
      |Accept| application/vnd.github+json|
    And the query parameter "since" is set to 106641159
    When I send a GET request to users endpoint
    Then The response payload should contain ids greater than 106641159
