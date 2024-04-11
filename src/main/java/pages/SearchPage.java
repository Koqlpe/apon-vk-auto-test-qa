package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchPage {
    private final By searchField = By.xpath("//input[@type=\"search\"]");
    private final By searchHeader = By.xpath("//*[@class=\"wrap__j90gp\"]");
    private final By profiles = By.xpath("//*[@class=\"sm__79ad9 __ellipted__79ad9\"]");
    private final By logoButton = By.xpath("//*[@class=\"toolbar_logo\"]");

    public SearchPage() {
        checkPage();
    }

    public void checkPage() {
        $(searchField).shouldBe(enabled
                .because("На странице поиска пользователю должно быть доступно поле для поиска"));
        $(searchHeader).shouldBe(visible);
    }

    public ElementsCollection searchPeoples(String query) {
        return $$(profiles).shouldHave(sizeGreaterThan(0)
                .because("Поиск должен найти карточки пользователей"));
    }

    public FeedPage returnToFeedPage() {
        $(logoButton).shouldBe(visible
                .because("Логотип ОК должен быть в верхнем левом углу экрана.")).click();
        return new FeedPage();
    }
}
