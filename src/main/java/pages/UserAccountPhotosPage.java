package pages;

import org.openqa.selenium.By;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserAccountPhotosPage extends BasePage {
    private final By albums = By.xpath(".//*[@data-l=\"t,title\"]");
    private final By loadPhotoButton = By.xpath(".//div[@data-l=\"t,upload-file\"]//input[@type=\"file\"]");
    private final By editButton = By.xpath(".//*[@data-l=\"t,editAlbum\"]");
    private final By deletePhotoButton = By.xpath(".//*[@data-l=\"t,remove-photo\"]");
    private final By backToAlbumButton = By.xpath(".//*[@data-l=\"t,back\"]");

    private final By photos = By.xpath(".//div[@class=\"photo-sc\"]//li[@class=\"ugrid_i\"]");
    private final By photoImg = By.xpath(".//img[contains(@class, va_target)]");

    @Override
    public void checkPage() {
        $(loadPhotoButton).shouldBe(clickable
                .because("На странице Фото должна отображаться кнопка Загрузить фото"));
    }

    public UserAccountPhotosPage openAlbum(String albumName) {
        $$(albums)
                .shouldBe(sizeGreaterThan(0).because("На странице Фото всегда есть альбом по умолчанию"))
                .findBy(attribute("title", albumName))
                .click();
        $$(photos).shouldBe(sizeGreaterThanOrEqual(0).because("Фотографии должны отображаться на странице"));
        return this;
    }

    public UserAccountPhotosPage addPhoto(File file) {
        int photoAmount = countPhotos();

        $(loadPhotoButton)
                .shouldBe(clickable.because("На странице Фото в альбоме должна быть доступна кнопка Загрузить фото"))
                .uploadFile(file);

        $$(photos).shouldBe(sizeGreaterThan(photoAmount).because("Ожидание загрузки фотографии"));
        $$(photos).last().find(photoImg).shouldBe(image.because("Фотография должна быть загружена"));

        return this;
    }

    public int countPhotos() {
        int photosAmount = 0;

        if (!checkFirstPhotoWasLoaded())
            photosAmount = $$(photos)
                    .shouldHave(sizeGreaterThan(0).because("Фотографии должны отображаться на странице"))
                    .size();

        return photosAmount;
    }

    public boolean checkFirstPhotoWasLoaded() {
        return $$(photos)
                .shouldBe(sizeGreaterThanOrEqual(0).because("Фотографии должны отображаться на странице"))
                .isEmpty();
    }

    public UserAccountPhotosPage deletePhoto() {
        $(editButton).shouldBe(visible.because("Кнопка Редактировать должна отображаться")).click();
        $$(deletePhotoButton)
                .shouldBe(sizeGreaterThan(0).because("Для удаления фотографии альбом должен быть не пустым"))
                .last().shouldBe(visible.because("При редактировании альбома у фотографии должна отображаться кнопка Удалить"))
                .click();
        $(backToAlbumButton)
                .shouldBe(visible.because("На названием альбом должна быть возможность нажать для возвращения к альбому"))
                .click();
        return this;
    }
}
