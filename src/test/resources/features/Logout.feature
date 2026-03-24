Feature: Logout

  @logout
  Scenario: verify user can logout
    Given base url
    When user hits POST "/api/auth/logout" to logout
    Then verify status code is 200
