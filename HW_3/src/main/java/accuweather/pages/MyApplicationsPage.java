import accuweather.base.BaseView;
import accuweather.locators.MyApplicationsPageLocators;
import com.codeborne.selenide.*;
import io.qameta.allure.*;

import static com.codeborne.selenide.Selenide.*;

import java.util.concurrent.locks.Condition;

import main.java.accuweather.locators.MyApplicationsPageLocators;

public class MyApplicationsPage {
    @Step("Click on button '+ Add new App'")
    public AddApplicationPage goToAddApplicationPage() {
        locators().buttonAddANewApp.click();
        return page(AddApplicationPage.class);
    }

    @Step("Verification of successful application creation")
    public MyApplicationsPage verificationOfSuccessfulApplicationCreation() {
        locators().successMessageAppCreatedIsVisible.shouldBe(Condition.visible);
        return this;
    }

    @Step("Click on the application you want to uninstall")
    public MyApplicationsPage clickOnMyApplication() {
        locators().myApplication.click();
        return this;
    }

    @Step("In the menu that appears, click the delete button")
    public MyApplicationsPage clickOnButtonDeleteApplication() {
        locators().buttonDeleteMyApplication.click();
        return this;
    }

    @Step("Confirm Application Removal")
    public MyApplicationsPage confirmDeleteApplication() {
        locators().buttonConfirmDeletionApplication.click();
        return this;
    }

    public MyApplicationsPage verificationOfSuccessfulApplicationDeleting() {
        locators().successDeletingApplication.shouldBe(Condition.visible);
        return this;
    }

    private MyApplicationsPageLocators locators() {
        return new MyApplicationsPageLocators(driver);
    }
}
