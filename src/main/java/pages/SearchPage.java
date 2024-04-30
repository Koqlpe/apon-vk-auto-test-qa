package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {
    private final By searchField = By.xpath(".//input[@type=\"search\"]");
    private final By searchHeader = By.xpath(".//*[@id=\"hook_Block_SearchMRB\"]//div[@role=\"heading\"]");
    private final By profiles = By.xpath(".//div[@id=\"tabpanel-users\"]//div[contains(@class, 'ellipted')]");

    public SearchPage() {
        checkPage();
    }

    public void checkPage() {
        $(searchField).shouldBe(enabled
                .because("На странице поиска пользователю должно быть доступно поле для поиска"));
        $(searchHeader).shouldHave(text("Поиск")
                .because("На странице осуществляется поиск"));
    }

    public ElementsCollection searchPeoples() {
        return $$(profiles).shouldHave(sizeGreaterThan(0)
                .because("Поиск должен найти карточки пользователей"));
    }
}
