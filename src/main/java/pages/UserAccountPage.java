package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class UserAccountPage extends BasePage {
    private final static By username = By.xpath(".//h1[@class=\"__user-profile-name-decorator\"]");
    private final static By profileEntry = By.xpath(".//*[@class=\"profile-user-info_name\"]");
    private final static By photos = By.xpath(".//*[@data-l=\"t,header-v2\"]");
    private final static By statusActivation = By.xpath(".//*[@data-save-url=\"/dk?cmd=SaveProfileInfo\"]");
    private final static By statusEditField = By.xpath(".//textarea[@name=\"long_bio\"]");
    private final static By saveStatusButton = By.xpath(".//button[@data-l=\"t,textField-save\"]");
    private final static By statusTextField = By.xpath(".//div[@tsid=\"TextFieldText\"]");

    public UserAccountPage() {
        checkPage();
    }

    public void checkPage() {
        $(username).shouldBe(visible.because("В профиле пользователя должно отображаться его имя"));
        $(profileEntry).shouldHave(href(url()).because("Ссылки в адресной строке и в имени пользователя должны совпадать"));
    }

    public UserAccountPhotosPage goToPhotos() {
        $(photos).shouldHave(href(url() + "/photos").because("С помощью кнопки \"Фотографии\" должен" +
                "осуществляться переход к фотоальбомам по ссылке")).click();
        return new UserAccountPhotosPage();
    }

    public SelenideElement getStatus() {
        return $(statusEditField).shouldBe(exist.because("Статус должен отображаться в профиле"));
    }

    public UserAccountPage setStatus(String status) {
        $(statusActivation).shouldBe(visible.because("Для изменения статуса должно отображаться поле статуса")).click();

        if (status.isEmpty() || status.isBlank())
//            $(statusEditField)
//                    .shouldBe(exist.because("Для изменения статуса поле должно быть доступно для редактирования"))
//                    .clear();
            clearStatusFiled();

        else
            $(statusEditField)
                    .shouldBe(exist.because("Для изменения статуса поле должно быть доступно для редактирования"))
                    .setValue(status);

        $(saveStatusButton)
                .shouldBe(enabled.because("Кнопка Сохранение должна быть доступна"))
                .click();

        return new UserAccountPage();
    }

    private void clearStatusFiled() {
        SelenideElement status = $(statusEditField)
                .shouldBe(exist.because("Для изменения статуса поле должно быть доступно для редактирования"));
        while (!Objects.requireNonNull(status.val()).isEmpty()) {
            status.press(Keys.BACK_SPACE);
        }
    }
}
