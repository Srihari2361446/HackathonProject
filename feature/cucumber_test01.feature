Feature: Check Validation for all inputs in wellness page

  Scenario: check validations
    Given user navigate to practo webpage
    And opens wellness page
    When all inputs filled through excel
    Then Take screenshot
