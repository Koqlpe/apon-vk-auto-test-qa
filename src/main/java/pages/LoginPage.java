package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private final String botUsername = "technopol33";
    private final String botPassword = "technopolisPassword";

    private final By loginField = By.xpath(".//*[@id=\"field_email\"]");
    private final By passwordField = By.xpath("//*[@id=\"field_password\"]");

    private final By loginButton = By.xpath(".//*[@class=\"button-pro __wide\"]");
    private final By errorMessage = By.xpath(".//*[@class=\"input-e login_error\"]");

    public FeedPage login() {
        setUsername(botUsername);
        setPassword(botPassword);
        clickLoginButton();
        return new FeedPage();
    }
    public LoginPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
    }
    public LoginPage setUsername(String username) {
        $(loginField).val(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        $(passwordField).val(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        $(loginButton).shouldBe(and("Кнопка входа должна быть доступна и видна", enabled, visible)).click();
        return this;
    }

    public String showErrorMessage() {
        return $(errorMessage).shouldBe(visible.because("Пользователь должен понимать причину ошибки входа")).getText();
    }
}
