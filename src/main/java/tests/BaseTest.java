package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    @BeforeEach
    public void init() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://ok.ru/";
    }
}
