
import accuweather.pages.MainPage;
import accuweather.pages.MyApplicationsPage;
import base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import main.java.accuweather.pages.MainPage;
import main.java.accuweather.pages.MyApplicationsPage;

public class UninstallingAnApplicationTest extends BaseTest {
    @Test
    @Description("Application uninstall test")
    void deletingApplication() {
        new MainPage()
                .authorization()
                .goToMyApplicationsPage();
        new MyApplicationsPage()
                .clickOnMyApplication()
                .clickOnButtonDeleteApplication()
                .confirmDeleteApplication()
                .verificationOfSuccessfulApplicationDeleting();
    }
}
