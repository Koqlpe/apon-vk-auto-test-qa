package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.*;
public class SearchUsersTest extends BaseTest {
    private FeedPage feed = new FeedPage();
    private ToolBar toolBar = new ToolBar();
    private SearchPage searchPage;

    @BeforeAll
    public static void login() {
        String botUsername = "technopol33";
        String botPassword = "technopolisPassword";

        new LoginPage()
                .setUsername(botUsername)
                .setPassword(botPassword)
                .clickLoginButton();
    }

    @ParameterizedTest(name = "Тест: поиск пользователей по имени")
    @ValueSource(strings = {"Ирина", "Татьяна", "Макс"})
    public void searchPeoplesInFeedPage(String query) {
       searchPage = feed
                .searchFor(query);
       searchPage.checkPage();
       SelenideElement firstResult = searchPage.searchPeoples().first();
       assertTrue(firstResult.text().contains(query), () -> "Первый результат не совпадает с запросом: " + query);
    }

    @AfterEach
    public void clearAndReturn() {
        feed = toolBar.returnToFeedPage();
    }

    @AfterAll
    public static void logOut() {
        new ToolBar().logOut();
    }
}
