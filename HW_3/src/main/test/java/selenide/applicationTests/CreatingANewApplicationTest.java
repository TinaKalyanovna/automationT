import accuweather.data.ApplicationData;
import accuweather.pages.AddApplicationPage;
import accuweather.pages.MainPage;
import base.BaseTest;
import accuweather.pages.MyApplicationsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import main.java.accuweather.data.ApplicationData;
import main.java.accuweather.pages.AddApplicationPage;
import main.java.accuweather.pages.MainPage;
import main.java.accuweather.pages.MyApplicationsPage;
import main.test.java.base.BaseTest;

public class CreatingANewApplicationTest  extends BaseTest {
    @Test
    @Description("Test of creating a new application")
    void creatingNewApplication() {
        new MainPage()
                .authorization()
                .goToMyApplicationsPage();
        new MyApplicationsPage()
                .goToAddApplicationPage();
        new AddApplicationPage()
                .fillNameApplication(ApplicationData.APPLICATION_NAME)
                .selectAttributeWhereApisUsed(ApplicationData.ATTRIBUTE)
                .selectCheckBoxPartnerApp()
                .clickOnCreateApp();
        new MyApplicationsPage()
                .verificationOfSuccessfulApplicationCreation();
    }
}
