@Inventory
Feature: Inventory Test Suite

  Background: Logging succesfully and go to inventory page
    Given the user goes to the main page
    And the user logs succesfully in the main page
    And the user is in the inventory page

  @CheckingInventory
  Scenario: Size of inventory product list is 6
    When the user sees the inventory list
    Then the list has 6 elements

  @CheckingInventory
  Scenario Outline: Specific product is in inventory list
    When the user sees the inventory list
    Then the list contains "<item_name>"

    Examples:
      | item_name |
      | Sauce Labs Bolt T-Shirt |

  @PlayingWithCart
  Scenario Outline: Specific product is added to cart
    When the user add "<item_name>" to cart
    Then the cart has 1 element

    Examples:
    | item_name |
    | Sauce Labs Bolt T-Shirt |

  @PlayingWithCart
  Scenario Outline: Specific product is added and removed from the cart
    When the user add "<item_name>" to cart
    And the user removes "<item_name>" from cart
    Then the cart has 0 element

    Examples:
      | item_name |
      | Sauce Labs Bolt T-Shirt |

  @PlayingWithCart
  Scenario Outline: Random products are added to cart
    When the user add any <item_quantity> items to the cart
    Then the cart has <item_quantity> element

    Examples:
      | item_quantity |
      | 3 |

  @SortingCart
  Scenario Outline: Sort elements in inventory
    When the user sort the list of products "<sort_type>"
    Then the list of products shows accordingly to sort type "<sort_type>"

    Examples:
      | sort_type |
      | az |
      | za |
      | hilo |
      | lohi |

