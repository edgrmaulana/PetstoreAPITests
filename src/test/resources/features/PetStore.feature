Feature: Test Petstore Swagger API for User Creation

  @PositiveScenario
  Scenario: Verify API User Creation
    Given Set the API Endpoint to "https://petstore.swagger.io/v2"
    Then  Set the username to "randomUsername"
    And   Set the firstName to "Edgar"
    And   Set the lastName to "Maulana"
    And   Set the email to "randomEmail"
    And   Set the password to "qwerty123"
    And   Set the phone to "11223344"
    Then  Hit the API Endpoint to create the user
    Then  Verify the create user API return 200 code

  @PositiveScenario1
  Scenario: Verify API Get User
    Given Set the API Endpoint to "https://petstore.swagger.io/v2"
    Then  Set the username to "randomUsername"
    And   Set the firstName to "Edgar"
    And   Set the lastName to "Maulana"
    And   Set the email to "randomEmail"
    And   Set the password to "qwerty123"
    And   Set the phone to "11223344"
    Then  Hit the API Endpoint to create the user
    Then  Verify the create user API return 200 code

    Then  Hit the API Endpoint to get the user
    Then  Verify the username is correct
    Then  Verify the firstName is correct
    Then  Verify the lastName is correct
    Then  Verify the email is correct
    Then  Verify the password is correct
    Then  Verify the phone is correct