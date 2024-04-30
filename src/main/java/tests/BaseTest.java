package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

/**
 * Базовый тестовый класс для всех тестов.
 */
public abstract class BaseTest {
    public static final String BASE_URL = "https://ok.ru/";
    @BeforeAll
    public static void init() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = BASE_URL;
        open("/");
    }
}
