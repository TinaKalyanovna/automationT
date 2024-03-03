Feature: Create a new application

  Background:
    Given Authorization

  Scenario:
    When Click on button 'MY APPS' on navigation menu
    And Click on button '+Add a new App'
    And Fill Application name
    And Select where the API will be used
    And Select what to build with this API
    And Click on button 'Create App'
    Then Success message 'App created!' is visible