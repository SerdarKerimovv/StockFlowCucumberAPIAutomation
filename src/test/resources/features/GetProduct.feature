Feature: Get Product by SKU
@getProduct
  Scenario: Get product with valid data
  Given base url
    And user has valid authorization
    And product with sku "SKU00001" exists
    When user sends GET request with valid product data "SKU00001"
    Then response status should be 200

