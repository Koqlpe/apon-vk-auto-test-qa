package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class FeedPage extends BasePage {
    private final static String URL = "https://ok.ru/feed";

    public void checkUsernameInNavigation() {
        $(By.xpath("//*[@class=\"tico ellip\"]")).shouldHave(text("technopol33 technopol33"));
    }

    public SearchPage searchFor(String query) {
        $(By.xpath("//*[@name=\"st.query\"]")).val("Татьяна").pressEnter();
        return new SearchPage();
    }
}
