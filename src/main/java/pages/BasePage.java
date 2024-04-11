package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.$;

/**
 * Главная страница сайта Одноклассники.
 */
public abstract class BasePage {
    public static final String BASE_URL = "https://ok.ru/";
    @BeforeEach
    public void init() {
        Configuration.browser = "Chrome";
        Configuration.baseUrl = BASE_URL;
    }
}
