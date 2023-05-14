@Cart
Feature: Cart Test Suite

  Background: Logging successfully and go to inventory page
    Given the user goes to the main page
    And the user logs successfully in the main page
    And the user is in the inventory page

  @PlayingWithCart
  Scenario: Remove a product from the cart
    And the user add any 2 items to the cart
    And the user goes to cart page
    When the user removes a product from the cart
    Then the cart has 1 element