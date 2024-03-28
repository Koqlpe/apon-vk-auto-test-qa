package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

/**
 * Главная страница сайта Одноклассники.
 */
public abstract class BasePage {
    public BasePage(){
        Configuration.browser = "Chrome";
        Configuration.baseUrl = "https://ok.ru/";
    }
}
