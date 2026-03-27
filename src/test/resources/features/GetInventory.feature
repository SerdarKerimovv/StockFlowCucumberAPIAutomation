Feature: Get inventory API validation
  @getInventory
  Scenario:  Get inventory with valid query params
    Given base url
    And user has valid authorization
    When user hits GET "/api/inventory" with query params
      | page        | 1         |
      | pageSize    | 15        |
      | warehouseId | WH001     |
      | sku         | SKU00001  |
      | search      | School    |
    Then response status should be 200
    And the response "page" should be 1
    And the response "pageSize" should be 15
    And the response "total" should be 0
    And the response "totalPages" should be 0
    And the response data array should be empty


  Scenario: Get inventory with only pagination params
    Given base url
    And user has valid authorization
    When user hits GET "/api/inventory" with query params
      | page     | 1  |
      | pageSize | 15 |
    Then response status should be 200
    And the response "page" should be 1
    And the response "pageSize" should be 15

