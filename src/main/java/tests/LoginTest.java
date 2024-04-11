package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import pages.*;

public class LoginTest extends BaseTest {
    private String noLogin = "Введите логин";
    private String noPassword = "Введите пароль";
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        loginPage = open("/", LoginPage.class);
    }

    @Test
    @DisplayName("Тест: вход с действительными логином и паролем")
    public void userCanLogin() {
        String currentUsername = loginPage.login().getUsernameInNavigation();
        assertEquals("technopol33 technopol33", currentUsername);
    }

    @ParameterizedTest(name = "Тест: вход без логина")
    @ValueSource(strings = {"abracadabra", "technopolisPassword", ""})
    public void loginWithoutUsername(String password) {
        String errorMessage = loginPage
            .login("", password)
            .showErrorMessage();
        assertEquals(noLogin, errorMessage);
    }

    @ParameterizedTest(name = "Тест: вход без пароля")
    @ValueSource(strings = {"abracadabra", "technopol33"})
    public void loginWithoutPassword(String login) {
        String errorMessage = loginPage
                .login(login, "")
                .showErrorMessage();
        assertEquals(noPassword, errorMessage);
    }
}
