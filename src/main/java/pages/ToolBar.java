package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ToolBar extends BasePage {
    private final static By logoButton = By.xpath(".//*[@class=\"toolbar_logo\"]");
    private final static By profileToolBar = By.xpath(".//button[@aria-controls=\"user-dropdown-menu\"]");
    private final static By exitButton = By.xpath(".//div[@class=\"toolbar_dropdown\"]//a[@data-l=\"t,logout\"]");
    public final static By confirmExitButton = By.xpath(".//input[@data-l=\"t,logout\"]");

    public ToolBar() {
        checkPage();
    }

    @Override
    public void checkPage() {
        $(profileToolBar).shouldBe(visible
                .because("На панели инструментов должна отображаться кнопка настроек пользователя"));
    }

    public FeedPage returnToFeedPage() {
        $(logoButton).shouldBe(visible
                .because("Логотип ОК должен быть в верхнем левом углу экрана."))
                .click();
        return new FeedPage();
    }

    public LoginPage logOut() {
        $(profileToolBar)
                .shouldBe(visible.because("Чтобы выйти, должна отображаться кнопка настроек пользователя"))
                .click();
        $(exitButton)
                .shouldBe(visible.because("Чтобы выйти, должна быть доступна кнопка Выйти"))
                .click();
        $(confirmExitButton)
                .shouldBe(visible.because("Чтобы выйти, должно всплывать окно с кнопкой для подтверждения выхода"))
                .click();
        return new LoginPage();
    }
}
