import accuweather.base.BaseView;
import accuweather.data.BaseData;
import accuweather.data.LoginData;
import accuweather.locators.MainPageLocators;
import com.codeborne.selenide.*;
import io.qameta.allure.*;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.*;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;

import java.util.concurrent.locks.Condition;

import main.java.accuweather.data.BaseData;
import main.java.accuweather.data.LoginData;
import main.java.accuweather.locators.MainPageLocators;

public class MainPage {
    @Step("Open AccuWeather site")
    public MainPage openSite() {
        open(BaseData.BASE_URL);
        return this;
    }

    @Step("Login to the site")
    public MainPage authorization() {
        locators().buttonToOpenTheAuthorizationWindow.click();
        locators().inputUsername.sendKeys(LoginData.LOGIN);
        locators().inputPassword.sendKeys(LoginData.PASSWORD);
        locators().buttonLogin.click();
        return this;
    }

    @Step("Click on the login button to open a window for authorization")
    public MainPage clickOnTheButtonToOpenTheAuthorizationWindow() {
        locators().buttonToOpenTheAuthorizationWindow.click();
        return this;
    }

    @Step("Filling in the login field")
    public MainPage fillInputLogin(String login) {
        locators().inputUsername.sendKeys(login);
        return this;
    }

    @Step("Click on the password field")
    public MainPage clickOnPasswordField() {
        locators().inputPassword.click();
        return this;
    }

    @Step("Filling in the password field")
    public MainPage fillInputPassword(String password) {
        locators().inputPassword.sendKeys(password);
        return this;
    }

    @Step("Click on the button 'Login'")
    public MainPage clickOnTheButtonLogin() {
        locators().buttonLogin.click();
        return this;
    }

    @Step("Go to my applications page")
    public MyApplicationsPage goToMyApplicationsPage() {
        locators().buttonMyAppsOnNavigationMenu.click();
        return Selenide.page(MyApplicationsPage.class);
    }

    @Step("Verification of error message")
    public MainPage checkingTheAuthorizationErrorMessage() {
        assertThat(locators().authorizationErrorMessage, hasText("Error message"));
        return this;
    }

    @Step("Checking messages about successful authorization")
    public MainPage checkingMessagesAboutSuccessfulAuthorization() {
        locators().successfulAuthorizationMessage.shouldBe(Condition.visible);
        return this;
    }

    private MainPageLocators locators() {
        return new MainPageLocators(driver);
    }
}
