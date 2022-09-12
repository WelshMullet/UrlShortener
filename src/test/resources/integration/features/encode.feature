Feature: Encode endpoint behaviour

  Scenario: Application should return encoded URL
    When I POST to the encode endpoint with the URL "https://www.google.com"
    Then I get a 200 response
    And the response contains an encoded URL
