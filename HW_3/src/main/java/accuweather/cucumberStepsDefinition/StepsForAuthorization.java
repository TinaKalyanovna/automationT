
import accuweather.data.LoginData;
import accuweather.pages.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

public class StepsForAuthorization {
    @Given("Open site")
    @Step("Open site developer.accuweather.com")
    public void openSite() {
        new MainPage().openSite();
    }

    @When("Click on the login button to open the authorization window")
    public void openingTheAuthorizationWindow() {
        new MainPage().clickOnTheButtonToOpenTheAuthorizationWindow();
    }

    @And("Fill input Login")
    public void fillInputLogin() {
        new MainPage().fillInputLogin(LoginData.LOGIN);
    }

    @And("Fill input Login with incorrect data")
    public void fillInputLoginWithIncorrectData() {
        new MainPage().fillInputLogin(LoginData.INCORRECT_LOGIN);
    }

    @And("Click on Password field")
    public void clickOnPasswordField() {
        new MainPage().clickOnPasswordField();
    }

    @And("Fill input Password with incorrect data")
    public void fillInputPasswordWithIncorrectData() {
        new MainPage().fillInputPassword(LoginData.INCORRECT_PASSWORD);
    }

    @And("Fill input Password")
    public void fillInputPassword() {
        new MainPage().fillInputPassword(LoginData.PASSWORD);
    }

    @And("Click on button \"Login\"")
    public void clickOnButtonLogin() {
        new MainPage().clickOnTheButtonLogin();
    }

    @Then("Check success authorization")
    public void checkSuccessAuthorization() {
        new MainPage().checkingMessagesAboutSuccessfulAuthorization();
    }

    @Then("Authorization error check")
    public void checkErrorAuthorization() {
        new MainPage().checkingTheAuthorizationErrorMessage();
    }
}
