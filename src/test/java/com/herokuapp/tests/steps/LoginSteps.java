package com.herokuapp.tests.steps;

import com.herokuapp.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage(BaseSteps.driver);
        loginPage.navigateTo();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.loginWith(username, password);
    }

    @Then("I should see the success message {string}")
    public void i_should_see_success_message(String expectedMessage) {
        String actualMessage = loginPage.getSuccessMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected success message to contain: " + expectedMessage);
    }

    @Then("I should see the error message {string}")
    public void i_should_see_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message to contain: " + expectedMessage);
    }
}