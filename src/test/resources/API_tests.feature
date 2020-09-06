Feature: checking Shopping list functionality

  Background:
    Given post new Shopping list

  @API
  Scenario: check creating Shopping list functionality
    When get Shopping List by id
    Then verify that response contains necessary id
    And Verify that Shopping list is empty

  @API
  Scenario: check deletening Shopping list functionality
    When delete Shopping list by id
    And get Shopping List by id after deleting Shopping list
    Then verify that code response 400
    And verify that response message is 'shoppingList.notFound'
