Feature: Uninstalling an application

  Background:
    Given Authorization

  Scenario:
    When Click on button 'MY APPS' on navigation menu
    And Select Application
    And Click on the 'Delete' button in the menu that open
    And Confirm the deletion by clicking on the delete button
    Then Make sure the app is uninstalled