Feature: Decode endpoint behaviour

  Scenario: Application should return decoded URL
    Given a URL has been encoded
    When I POST to the decode endpoint with the encoded URL
    Then I get a 200 response
    And the response contains the correct decoded URL

  Scenario: Application should return the correct decoded URL when multiple have been encoded
    Given 2 URLs have been encoded
    When I POST to the decode endpoint with the first encoded URL
    Then I get a 200 response
    And the response contains the first decoded URL
    When I POST to the decode endpoint with the second encoded URL
    Then I get a 200 response
    And the response contains the second decoded URL
