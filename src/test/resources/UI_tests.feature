Feature: checking Shopping list functionality

  Background:
    Given user navigate to my Whisk
    When fill in 'username'
    And click 'Continue'
    And fill in 'password'
    And click 'Log in'
    And click "get cooking!" button
    And click 'Shopping' tab

  @UI
  Scenario Outline: adding itmes to Shopping List
    And add 5 products to the list
    #    Then check the result
    Then check added products "<name>"
    Examples:
      | name     |
      | Bread    |
      | Eggs     |
      | Milk     |
      | Onions   |
      | Potatoes |



  @UI
  Scenario: deleting already existed Shopping list
    And open Shopping list menu
    And delete Shopping list
    Then check that user doesn't have Shopping lists