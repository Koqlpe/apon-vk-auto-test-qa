package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.BasePage;

public abstract class BaseTest {
    @BeforeAll
    public static void init() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = BasePage.BASE_URL;
    }
}
