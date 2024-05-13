package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserAccountPhotosPage extends BasePage {
    private final static By albums = By.xpath(".//*[@data-l=\"t,title\"]");
    private final static By loadPhotoButton = By.xpath(".//div[@data-l=\"t,upload-file\"]//input[@type=\"file\"]");
    private final static By photos = By.xpath(".//ul[@class=\"ugrid_cnt\"]//li[@class=\"ugrid_i\"]");

    @Override
    public void checkPage() {
        $(loadPhotoButton).shouldBe(clickable
                .because("На странице Фото должна отображаться кнопка Загрузить фото"));
    }

    public PhotoAlbum openAlbum(String albumName) {
        $$(albums)
                .shouldBe(sizeGreaterThan(0).because("На странице Фото всегда есть альбом по умолчанию"))
                .findBy(attribute("title", albumName))
                .click();
        $$(photos).shouldBe(sizeGreaterThanOrEqual(0).because("Фотографии должны отображаться на странице"));
        return new PhotoAlbum();
    }
}
