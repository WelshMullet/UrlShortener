Feature: Decode endpoint behaviour

  Scenario: Application should return decoded URL
    Given a URL has been encoded
    When I POST to the decode endpoint with the encoded URL
    Then I get a 200 response
    And the response contains the correct decoded URL
