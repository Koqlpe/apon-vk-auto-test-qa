package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    public SearchPage displayQuery(String query) {
        $(By.xpath("//*[@type=\"search\"]")).shouldHave(value(query));
        return this;
    }
}
