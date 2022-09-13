Feature: Encode endpoint behaviour

  Scenario: Application should return encoded URL
    When I POST to the encode endpoint with the URL "https://www.google.com"
    Then I get a 200 response
    And the response contains an encoded URL

  Scenario: Application should gracefully handle a request to encode an invalid URL
    When I POST to the encode endpoint with the URL "not-a-valid-url"
    Then I get a 400 response
    And the response contains an error message
