package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NavigationPanel extends BasePage {
    private final By postingButton = By.xpath(".//nav//div[@id=\"hook_Block_PostingFormDropdown\"]");
    private final By accountLabel = By.xpath(".//div[@class=\"navigation\"]//a[@data-l=\"t,userPage\"]");
    private final By photoButton = By.xpath(".//div[@class=\"navigation\"]//a[@data-l=\"t,userPhotos\"]");

    public NavigationPanel() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(postingButton).shouldBe(visible
                .because("Кнопка Опубликовать должна быть доступна на панели навигации"));
    }

    public UserAccountPage goToProfile() {
        $(accountLabel).shouldBe(enabled
                .because("С помощью панели навигации можно перейти в профиль по имени пользователя"))
                .click();
        return new UserAccountPage();
    }

    public UserAccountPhotosPage goToPhoto() {
        $(photoButton).shouldBe(enabled
                        .because("С помощью панели навигации можно перейти к фотографиям пользователя"))
                .click();
        return new UserAccountPhotosPage();
    }
}
