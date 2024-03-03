import accuweather.data.LoginData;
import base.BaseTest;
import accuweather.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import main.java.accuweather.data.LoginData;
import main.java.accuweather.pages.MainPage;

public class PositiveAuthorizationTest extends BaseTest {
    @Test
    @Description("Success login in AccuWeather")
    void successLogin() {
        new MainPage()
                .clickOnTheButtonToOpenTheAuthorizationWindow()
                .fillInputLogin(LoginData.LOGIN)
                .clickOnPasswordField()
                .fillInputPassword(LoginData.PASSWORD)
                .clickOnTheButtonLogin()
                .checkingMessagesAboutSuccessfulAuthorization();
    }
}
