Feature: Login functionality
  As a user of the-internet.herokuapp.com
  I want to be able to log in with valid credentials
  So that I can access the secure area

  Background:
    Given I am on the login page

  Scenario: Successful login with valid credentials
    When I enter username "tomsmith" and password "SuperSecretPassword!"
    Then I should see the success message "You logged into a secure area!"

  Scenario: Failed login with invalid username
    When I enter username "wronguser" and password "SuperSecretPassword!"
    Then I should see the error message "Your username is invalid!"

  Scenario: Failed login with invalid password
    When I enter username "tomsmith" and password "wrongpassword"
    Then I should see the error message "Your password is invalid!"

  Scenario Outline: Login with multiple invalid credentials
    When I enter username "<username>" and password "<password>"
    Then I should see the error message "<message>"

    Examples:
      | username   | password             | message                   |
      | wronguser  | SuperSecretPassword! | Your username is invalid! |
      | tomsmith   | wrongpassword        | Your password is invalid! |