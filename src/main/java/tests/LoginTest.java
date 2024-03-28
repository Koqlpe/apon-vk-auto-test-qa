package tests;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;
import pages.*;

public class LoginTest extends BaseTest {
    @Test
    public void userCanLogin() {
        open("/", LoginPage.class).login();
        new FeedPage().checkUsernameInNavigation();
        sleep(5000);
    }

    @Test
    public void loginWithoutData() {
        LoginPage loginPage = open("/", LoginPage.class);
        loginPage.clickLoginButton();
        loginPage.showErrorMessage();
    }
}
