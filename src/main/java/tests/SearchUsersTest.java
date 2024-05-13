package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import static tests.TestBot.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.*;

public class SearchUsersTest extends BaseTest {
    private FeedPage feed = new FeedPage();
    private final ToolBar toolBar = new ToolBar();
    private SearchPage searchPage;

    @BeforeAll
    public static void login() {
        TestBot testBot = newBuilder().buildDefault();

        new LoginPage()
                .setUsername(testBot.getBotUsername())
                .setPassword(testBot.getBotPassword())
                .clickLoginButton();
    }

    @ParameterizedTest(name = "Тест: поиск пользователей по имени")
    @ValueSource(strings = {"Ирина", "Татьяна", "Макс"})
    public void searchPeoplesInFeedPage(String query) {
       searchPage = feed
                .searchFor(query);
       searchPage.checkPage();
       String firstResult = searchPage.getFirstResultValue().toLowerCase();
       assertTrue(firstResult.contains(query.toLowerCase()), () -> "Первый результат не совпадает с запросом: " + query);
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
