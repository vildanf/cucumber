@Login
Feature: Login
  As user, I want to be able to login into vytrack under different roles with
  username, password
  #any steps that was implemented before we can reuse
  #if test step has yellow background that means it doesn't have implementation yet
  #click ctr alt L to rganize code
  #i had somewhere from 2 to 25 scenirios in every feature file
  #by passing parameters/strings in "some word" we can reuse test steps
  @store_manager
  Scenario: Login as store manager
    Given  user is on the login page
    Then user logs in as store manager
    And user verifies that "Dashboard" page subtitle is displayed

  @driver
  Scenario: Login as driver
    Given user is on the login page
    Then user logs in as driver
    And user verifies that "Dashboard" page subtitle is displayed

  @sales_manager
  Scenario: Login as sales manager
    Given user is on the login page
    Then user logs in as sales manager
    And user verifies that "Dashboard" page subtitle is displayed

  @negative_test
  Scenario: Verify that warning message is displayed when password is not correct
    Given user is on the login page
    Then user enters "storemanager85" and "wrong" password
    And user verifies that "Invalid user name or password." message is displayed

  @negative_test @driver
  Scenario: Verify that warning message is displayed when password is not correct
    Given user is on the login page
    Then user enters "wrong_username" and "UserUser123" password
    And user verifies that "Invalid user name or password." message is displayed

  @driver_with_data_table
  Scenario: Login as driver(data table example)
    Given user is on the login page
    Then user logs in as driver with following credential
      | username | user160     |
      | password | UserUser123 |
    And user verifies that "Quick Launchpad" page subtitle is displayed

  @login_with_role
  Scenario: Login as driver
    Given user is on the login page
    Then user logs in as "driver"

  @login_with_role
  Scenario: Login as sales manager
    Given user is on the login page
    Then user logs in as "sales manager"

  @login_with_role
  Scenario: Login as store manager
    Given user is on the login page
    Then user logs in as "store manager"

  @login_with_role_ddt
  Scenario Outline: DDT example,login as <role>
    Given user is on the login page
    Then user logs in as "<role>"

    Examples: roles
      | role          |
      | driver        |
      | sales manager |
      | store manager |

  @login_with_credentials_ddt
  Scenario Outline: DDT example with credentials, Login as <username>
    Given user is on the login page
    Then user enters "<username>" username and "<password>" password

    Examples:
      | username        | password    |
      | storemanager85  | UserUser123 |
      | user160         | UserUser123 |
      | salesmanager110 | UserUser123 |

  @login_with_roles_ddt_2
  Scenario Outline: Login as <role> and verify <title> page title is correct
    Given user is on the login page
    And user logs in as "<role>"
    When user navigates to "<module>" then to "<sub module>"
    Then the page title should be "<title>"

    Examples: drivers

      | role   | module     | sub module      | title                                                        |
      | driver | Fleet      | Vehicles        | All - Car - Entities - System - Car - Entities- System       |
      | driver | Fleet      | Vehicles Model  | Vehicles Model - Entities - System - Car - Entities - System |
      | driver | Customers  | Accounts        | Accounts - Customers                                         |
      | driver | Customers  | Contacts        | Contacts - Customers                                         |
      | driver | Activities | Calendar Events | Calendar Events - Activities                                 |
      | driver | System     | Jobs            | Jobs - System                                                |

    Examples: sales managers

      | role          | module     | sub module      | title                                                              |
      | sales manager | Fleet      | Vehicles        | All - Car - Entities - System - Car - Entities- System             |
      | sales manager | Fleet      | Vehicles Model  | All - Vehicles Model - Entities - System - Car - Entities - System |
      | sales manager | Customers  | Accounts        | All - Accounts - Customers                                         |
      | sales manager | Customers  | Contacts        | All - Contacts - Customers                                         |
      | sales manager | Activities | Calendar Events | All -  Calendar Events - Activities                                |
      | sales manager | System     | Jobs            | All - Jobs - System                                                |