package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FeedPage extends BasePage {
    private final static String URL = "/feed";
    private final static By tapeButton = By.xpath(".//*[@data-l=\"feedTargetFilterId,43\"]"); // Рекомендации
    private final static By searchField = By.xpath(".//input[@name=\"st.query\"]");
    private final static By accountButton = By.xpath(".//*[@data-l=\"t,userPage\"]");
    private final static By accountLabel = By.xpath(".//*[@class=\"tico ellip\"]");


    public FeedPage() {
        checkPage();
    }

    public void checkPage() {
        $(tapeButton).shouldBe(visible
                .because("Кнопка рекомендации должна отображаться на странице Ленты новостей"));
    }

    public String getUsernameInNavigation() {
        return $(accountLabel)
                .shouldBe(visible.because("Имя и Фамилия пользователя должны отображаться на панели навигации"))
                .getText();
    }

    public SearchPage searchFor(String query) {
        $(searchField).shouldBe(visible.because("Пользователь должен видеть поле ввода"))
                .val(query)
                .pressEnter();
        return new SearchPage();
    }

    public UserAccountPage openAccountPage() {
        $(accountButton)
                .shouldBe(exist.because("Для перехода в профиль в панели навигации должна" +
                "существовать и отображаться кнопка перехода в профиль"))
                .click();
        return new UserAccountPage();
    }
}
