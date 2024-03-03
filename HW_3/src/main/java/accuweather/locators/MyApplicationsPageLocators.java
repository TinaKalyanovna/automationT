
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class MyApplicationsPageLocators {
    public final SelenideElement buttonAddANewApp = $(By.xpath("//a[@class='add-app']"));
    public final SelenideElement successMessageAppCreatedIsVisible = $(By.xpath("//div[text()='App Created!']"));
    public final SelenideElement myApplication = $(By.xpath("//div[@class='truncate']/a"));
    public final SelenideElement buttonDeleteMyApplication = $(By.xpath("//li[@class='hidden-xs apigee-modal-link-delete']/a"));
    public final SelenideElement buttonConfirmDeletionApplication = $(By.xpath("//button[@value='Delete App']"));

    public final SelenideElement successDeletingApplication = $(By.xpath("//span[text()='Explore them!']"));

    public MyApplicationsPageLocators(WebDriver driver) {
        super();
    }
}
