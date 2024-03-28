package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private final String botUsername = "technopol33";
    private final String botPassword = "technopolisPassword";

    public LoginPage login() {
        setUsername(botUsername);
        setPassword(botPassword);
        clickLoginButton();
        return this;
    }
    public LoginPage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return this;
    }
    public LoginPage setUsername(String username) {
        $(By.xpath("//*[@id=\"field_email\"]")).val(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        $(By.xpath("//*[@id=\"field_password\"]")).val(password);
        return this;
    }

    public void clickLoginButton() {
        $(By.xpath("//*[@class=\"button-pro __wide\"]")).click();
    }

    public void showErrorMessage() {
        $(By.xpath("//*[@class=\"input-e login_error\"]")).shouldBe(visible);
    }
}
