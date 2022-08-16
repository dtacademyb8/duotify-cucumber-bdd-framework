Feature: Testing GitHub API

  @api
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