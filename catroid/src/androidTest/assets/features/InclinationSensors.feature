Feature: Inclination Sensors values usable in Formula Editor

Scenario: Reading values for x-axis inclination sensors when in stage activity
    Given I have an activity with variables holding the values of inclination sensors
    When I change the inclination of the phone
    Then the test variable value should change accordingly