import accuweather.data.LoginData;
import accuweather.pages.MainPage;
import base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import main.java.accuweather.data.LoginData;
import main.java.accuweather.pages.MainPage;

public class NegativeAuthorizationTest  extends BaseTest {
    @Test
    @Description("Authorization without login")
    void emptyFieldLogin() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .clickOnPasswordField()
                .fillInputPassword(LoginData.PASSWORD)
                .clickOnTheButtonLogin()
                .checkingTheAuthorizationErrorMessage();
    }

    @Test
    @Description("Authorization without password")
    void emptyFieldPassword() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .fillInputLogin(LoginData.LOGIN)
                .clickOnTheButtonLogin()
                .checkingTheAuthorizationErrorMessage();
    }

    @Test
    @Description("Authorization with empty fields login and password")
    void emptyFieldsLoginAndPassword() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .clickOnTheButtonLogin()
                .checkingTheAuthorizationErrorMessage();
    }

    @Test
    @Description("Authorization with wrong login")
    void incorrectLogin() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .fillInputLogin(LoginData.INCORRECT_LOGIN)
                .clickOnPasswordField()
                .fillInputPassword(LoginData.PASSWORD)
                .clickOnTheButtonLogin()
                .checkingTheAuthorizationErrorMessage();
    }

    @Test
    @Description("Authorization with wrong password")
    void incorrectPassword() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .fillInputLogin(LoginData.LOGIN)
                .clickOnPasswordField()
                .fillInputPassword(LoginData.INCORRECT_PASSWORD)
                .clickOnTheButtonLogin()
                .checkingTheAuthorizationErrorMessage();
    }
}
