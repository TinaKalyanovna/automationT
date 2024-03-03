
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class AddApplicationPageLocators {
    public final SelenideElement inputAppName = $(By.xpath("//input[@id='edit-human']"));
    public final SelenideElement attributeWhereApisUsed = $(By.name("attribute_where_apis_used"));
    public final SelenideElement checkBoxPartnerApp = $(By.xpath("//input[@value='weatherapp']"));
    public final SelenideElement buttonCreateApp = $(By.xpath("//button[@value='Create App']"));

    public AddApplicationPageLocators(WebDriver driver) {
        super();
    }
}
