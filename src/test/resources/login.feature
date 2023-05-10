@LoginSuite
Feature: Login test suite

  Background: Set initial page
    Given the user is in the home page

  Scenario Outline: Verify valid user can login
    And the user provides the username "<username>"
    And the user provides the password "<password>"
    When the user clicks the login button
    Then the user is logged successfully and is into the inventory page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
      | problem_user  | secret_sauce |

  Scenario Outline: Verify invalid user can't login
    And the user provides the username "<username>"
    And the user provides the password "<password>"
    When the user clicks the login button
    Then the user is in the home page
    And a error message has displayed

    Examples:
      | username      | password     |
      | standar_user | secret_sauce |
      | locked_out_user  | secret_sauce |
      | standard_user    |              |
      | standard_user    | secretsauce |
