@Logout
Feature: Logout Test Suite

  Background: Logging succesfully and go to inventory page
    Given the user is in the home page
    And the user logs succesfully in the main page
    And the user is in the inventory page

  Scenario: Correct Logout
    When the user clicks in the burger menu
    And the user selects the logout option
    Then the user is in the home page
