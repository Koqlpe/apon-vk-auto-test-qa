package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class UserAccountPage extends BasePage {
    private final By username = By.xpath(".//h1[@class=\"__user-profile-name-decorator\"]");
    private final By profileEntry = By.xpath(".//*[@class=\"profile-user-info_name\"]");
    private final By photos = By.xpath(".//*[@data-l=\"t,header-v2\"]");
    private final By statusActivation = By.xpath(".//*[@data-save-url=\"/dk?cmd=SaveProfileInfo\"]");

    private final By statusField = By.xpath(".//textarea[@name=\"long_bio\"]");
    private final By saveStatusButton = By.xpath(".//button[@data-l=\"t,textField-save\"]");

    public UserAccountPage() {
        checkPage();
    }

    private void checkPage() {
        $(username).shouldBe(visible);
        $(profileEntry).shouldHave(href(url()));
    }

    public UserAccountPhotosPage goToPhotos() {
        $(photos).shouldHave(href(url() + "/photos").because("С помощью кнопки \"Фотографии\" должен" +
                "осуществляться переход к фотоальбомам по ссылке")).click();
        return new UserAccountPhotosPage();
    }

    public SelenideElement getStatus() {
        return $(statusField).shouldBe(exist.because("Статус должен отображаться в профиле"));
    }

    public UserAccountPage setStatus(String status) {
        // Вопрос
        $(statusActivation).click();
        $(statusField)
                .shouldBe(exist.because("Для изменения статуса поле должно быть доступно для редактирования"))
                .val(status);
        $(saveStatusButton)
                .shouldBe(enabled.because("Кнопка Сохранение должна быть доступна"))
                .click();
        return this;
    }


}
