@CartSuite
Feature: Cart Test Suite

  Background: Logging succesfully and go to inventory page
    Given the user is in the home page
    And the user logs succesfully in the main page
    And the user is in the inventory page

  @PlayingWithCart
  Scenario: Remove a product from the cart
    And the user add any "2" items to the cart
    And the user goes to cart page
    When the user removes a product from the cart
    Then the cart has "1" element