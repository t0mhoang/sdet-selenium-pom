package com.herokuapp.tests;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.herokuapp.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
//******** Running test w/ Allure Reporting:
//*******To run test, open gitbash by right-clicking project then open location, open project folder. right click anywhere in folder then open gitbash
// when gitbash is open... run "mvn clean test" in CommandLine... then run "mvn allure:serve" in CL to generate the allure report after test.

@Epic("Authentication")           // top-level grouping in report
@Feature("Login functionality")   // feature being tested
public class LoginTest extends BaseTest {
	
    private LoginPage loginPage;

    @BeforeEach
    void initPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateTo();
    }

    @Test
    @Story("Valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify successful login with correct credentials")
    @DisplayName("Valid credentials: successful login shows confirmation message")
    void validCredentials_successfulLogin() {
        loginPage.loginWith("tomsmith", "SuperSecretPassword!");
        assertTrue(loginPage.isSuccessMessageDisplayed());
        assertTrue(loginPage.getSuccessMessage().contains("You logged into a secure area!"));
    }

    @Test
    @Story("Invalid login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify error shown when username is wrong")
    @DisplayName("Invalid username: error message displayed")
    void invalidUsername_showsErrorMessage() {
        loginPage.loginWith("wronguser", "SuperSecretPassword!");
        assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"));
    }

    @Test
    @Story("Invalid login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify error shown when password is wrong")
    @DisplayName("Invalid password: error message displayed")
    void invalidPassword_showsErrorMessage() {
        loginPage.loginWith("tomsmith", "wrongpassword");
        assertTrue(loginPage.getErrorMessage().contains("Your password is invalid!"));
    }

    @Test
    @Story("Invalid login")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify error shown when both fields are empty")
    @DisplayName("Empty credentials: error message displayed")
    void emptyCredentials_showsErrorMessage() {
        loginPage.loginWith("", "");
        assertTrue(loginPage.getErrorMessage().contains("Your username is invalid!"));
    }
}