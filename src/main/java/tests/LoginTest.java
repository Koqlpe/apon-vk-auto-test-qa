package tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.*;

public class LoginTest extends BaseTest {
    public final static String botUsername = "technopol33";
    public final static String botPassword = "technopolisPassword";
    private final static String noLogin = "Введите логин";
    private final static String noPassword = "Введите пароль";

    @Nested
    class LoginWithExistUser {
        ToolBar toolBar = new ToolBar();
        @Test
        @DisplayName("Тест: вход с действительными логином и паролем")
        public void userCanLogin() {
            LoginPage loginPage = new LoginPage();
            loginPage
                    .setUsername(botUsername)
                    .setPassword(botPassword)
                    .clickLoginButton();
            String currentUsername = new FeedPage().getUsernameInNavigation();
            assertEquals("technopol33 technopol33", currentUsername);
        }

        @AfterEach
        public void cleanUp() {
            toolBar.logOut();
        }
    }

    @ParameterizedTest(name = "Тест: вход без логина")
    @ValueSource(strings = {"abracadabra", "technopolisPassword", ""})
    public void loginWithoutUsername(String password) {
        LoginPage loginPage = new LoginPage();
        String errorMessage = loginPage
                .setPassword(password)
                .clickLoginButton()
                .showErrorMessage();
        assertEquals(noLogin, errorMessage);
    }

    @ParameterizedTest(name = "Тест: вход без пароля")
    @ValueSource(strings = {"abracadabra", "technopol33"})
    public void loginWithoutPassword(String login) {
        LoginPage loginPage = new LoginPage();
        String errorMessage = loginPage
                .setUsername(login)
                .clickLoginButton()
                .showErrorMessage();
        assertEquals(noPassword, errorMessage);
    }
}
