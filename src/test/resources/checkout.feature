@Checkout
Feature: Checkout Test Suite

  Background: Logging succesfully and go to inventory page
    Given the user is in the home page
    And the user logs succesfully in the main page
    And the user is in the inventory page

  Scenario: Check final price without taxes of some products
    When the user add any "3" items to the cart
    And the user goes to cart page
    And the user goes to first checkout page
    And the user fills the checkout form
    Then the subtotal price in the second checkoutpage match the price of the elements in the inventory page

  Scenario: Make an order
    When the user add any "1" items to the cart
    And the user goes to cart page
    And the user goes to first checkout page
    And the user fills the checkout form
    And the user completes checkout
    Then the user is in the finalized checkout page
    And there is a message of successful order
