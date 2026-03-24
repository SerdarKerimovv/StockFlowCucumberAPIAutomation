Feature: Login

  @login
  Scenario: verify user can login
    Given base url
    When user hits POST "/api/auth/login"
    Then verify status code is 200
