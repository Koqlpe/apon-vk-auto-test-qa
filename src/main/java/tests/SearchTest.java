package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import pages.*;
public class SearchTest extends BaseTest {
    private FeedPage feed = new FeedPage();
    private SearchPage searchPage;

    @BeforeAll
    public static void login() {
        open("/", LoginPage.class).login();
    }

    @ParameterizedTest(name = "Тест: поиск пользователей по имени")
    @ValueSource(strings = {"Ирина", "Татьяна", "Макс"})
    public void searchPeoplesInFeedPage(String query) {
       searchPage = feed
                .searchFor(query);
       searchPage.checkPage();
        SelenideElement firstResult = searchPage.searchPeoples(query).first();
        assertTrue(firstResult.text().contains(query), () -> "Первый результат не совпадает с запросом: " + query);
    }

    @AfterEach
    public void clearAndReturn() {
        System.out.println("Тест закончен!!!!");
        feed = searchPage.returnToFeedPage();
    }
}
