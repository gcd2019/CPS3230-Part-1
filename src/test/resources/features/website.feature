Feature: WebScraper tests

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again

  Scenario: Alert layout
    Given I am an administrator of the website and I upload 3 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website

  Scenario: Alert limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts I should see 5 alerts

  Scenario Outline: Icon Check
    Given I am an administrator of the website and I upload an alert of type <alert-type>
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be <icon-file-name>

    Examples:
    |alert-type | icon-file-name                                                |
    |1          | "https://www.marketalertum.com/images/icon-car.png"           |
    |2          | "https://www.marketalertum.com/images/icon-boat.png"          |
    |3          | "https://www.marketalertum.com/images/icon-property-rent.jpg" |
    |4          | "https://www.marketalertum.com/images/icon-property-sale.jpg" |
    |5          | "https://www.marketalertum.com/images/icon-toys.png"          |
    |6          | "https://www.marketalertum.com/images/icon-electronics.png"   |

