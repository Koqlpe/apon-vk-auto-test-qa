package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private final static By loginField = By.xpath(".//*[@id=\"field_email\"]");
    private final static By passwordField = By.xpath(".//*[@id=\"field_password\"]");

    private final static By loginButton = By.xpath(".//*[@class=\"button-pro __wide\"]");
    private final static By errorMessage = By.xpath(".//*[@class=\"input-e login_error\"]");

    public LoginPage() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(loginField).shouldBe(visible
                .because("На странице авторизации должно отображаться поле Логин"));
        $(passwordField).shouldBe(visible
                .because("На странице авторизации должно отображаться поле Пароль"));
        $(loginButton).shouldBe(enabled
                .because("На странице авторизации должно отображаться кнопка Входа"));
    }

    public LoginPage setUsername(String username) {
        $(loginField)
                .shouldBe(enabled.because("В поле Логин должна быть возможность вводить логин"))
                .val(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        $(passwordField)
                .shouldBe(enabled.because("В поле Пароль должна быть возможно вводить пароль"))
                .val(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        $(loginButton)
                .shouldBe(and("Кнопка входа должна быть доступна и видна", enabled, visible))
                .click();
        return this;
    }

    public String showErrorMessage() {
        return $(errorMessage)
                .shouldBe(visible.because("Пользователь должен понимать причину ошибки входа"))
                .getText();
    }
}
