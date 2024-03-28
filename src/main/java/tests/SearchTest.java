package tests;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;
import pages.*;
public class SearchTest extends BaseTest {
    @Test
    public void searchInFeedPage() {
        String query = "Татьяна";
        LoginPage loginPage = open("/", LoginPage.class).login();
        SearchPage searchPage = new FeedPage().searchFor(query);
        searchPage.displayQuery(query);
    }
}
