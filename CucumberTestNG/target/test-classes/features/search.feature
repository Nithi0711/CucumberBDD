Feature: Youtube

  Scenario Outline: Launching and searching for a value
    Given Launch Youtube <url>
    And Search for a <value>
    
    Examples:
   | url | value |
   | https://www.youtube.com | Cucumber java |