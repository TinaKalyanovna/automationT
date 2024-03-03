
import accuweather.base.BaseView;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class MainPageLocators {
    public final SelenideElement buttonToOpenTheAuthorizationWindow = $(By.xpath("//a[text()='Login']"));
    public final SelenideElement inputUsername = $(By.xpath("//input[@id='edit-name']"));
    public final SelenideElement inputPassword = $(By.xpath("//input[@id='edit-pass']"));
    public final SelenideElement buttonLogin = $(By.xpath("//button[@id='edit-submit']"));
    public final SelenideElement buttonMyAppsOnNavigationMenu = $(By.xpath("//ul[contains(@class,'hidden-xs')]//a[text()='My Apps']"));

    public final SelenideElement authorizationErrorMessage = $(By.xpath("//h4[text()='Error message']"));
    public final SelenideElement successfulAuthorizationMessage = $(By.xpath("//a[text()='Logout']"));

    public MainPageLocators(WebDriver driver) {
        super();
    }
}
