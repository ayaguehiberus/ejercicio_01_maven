@Logout
Feature: Logout Test Suite

  Background: Logging successfully and go to inventory page
    Given the user goes to the main page
    And the user logs successfully in the main page
    And the user is in the inventory page

  Scenario: Correct Logout
    When the user clicks in the burger menu
    And the user selects the logout option
    Then the user is in the home page
