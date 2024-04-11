package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FeedPage extends BasePage {
    private final static String URL = "/feed";
    private final By tapeButton = By.xpath(".//*[@class=\"link__91azp tab__9agyv __is-active__9agyv __primary__91azp\"]"); // Рекомендации
    private final By searchField = By.xpath("//input[@name=\"st.query\"]");
    private final By accountButton = By.xpath(".//*[@data-l=\"t,userPage\"]");

    public FeedPage() {
        checkPage();
    }

    public void checkPage() {
        $(tapeButton).shouldBe(visible);
    }

    public String getUsernameInNavigation() {
        return $(By.xpath("//*[@class=\"tico ellip\"]"))
                .shouldBe(visible.because("Имя и Фамилия пользователя должны отображаться на панели навигации"))
                .getText();
    }

    public SearchPage searchFor(String query) {
        $(searchField).shouldBe(visible.because("Пользователь должен видеть поле ввода.")).val(query).pressEnter();
        return new SearchPage();
    }

    public UserAccountPage openAccountPage() {
        $(accountButton)
                .shouldBe(exist.because("Для перехода в профиль в панели навигации должна" +
                "существовать и отображаться кнопка перехода в профиль."))
                .click();
        return new UserAccountPage();
    }
}
