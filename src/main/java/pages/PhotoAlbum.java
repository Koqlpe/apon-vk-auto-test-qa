package pages;

import org.openqa.selenium.By;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PhotoAlbum extends BasePage {
    private final static By albumName = By.xpath(".//*[@class=\"photo-album_info\"]");
    private final static By loadPhotoButton = By.xpath(".//div[@data-l=\"t,upload-file\"]//input[@type=\"file\"]");
    private final static By editButton = By.xpath(".//*[@data-l=\"t,editAlbum\"]");
    private final static By deletePhotoButton = By.xpath(".//*[@data-l=\"t,remove-photo\"]");
    private final static By backToAlbumButton = By.xpath(".//*[@data-l=\"t,back\"]");
    private final static By photos = By.xpath(".//ul[@class=\"ugrid_cnt\" and contains(@id, 'photo')]//li[@class=\"ugrid_i\"]");
    private final static By photoImg = By.xpath(".//img[contains(@class, va_target)]");
    private final static By emptyAlbumLabel = By.xpath(".//*[@class=\"photo-album_stub\"]");
    private final static By rootButton = By.xpath(".//a[@data-l=\"t,root\"]");

    @Override
    public void checkPage() {
        $(albumName).shouldBe(visible
                .because("Название альбома должно отображаться на странице"));
        $(editButton).shouldBe(visible
                .because("В Альбоме должна отображаться кнопка Редактировать"));
        $(loadPhotoButton).shouldBe(visible
                .because("В Альбоме должна отображаться кнопка Загрузить фотографии"));
    }

    public PhotoAlbum addPhoto(File file) {
        int photoAmount = countPhotos();

        $(loadPhotoButton)
                .shouldBe(clickable.because("На странице Фото в альбоме должна быть доступна кнопка Загрузить фото"))
                .uploadFile(file);

        $$(photos).shouldBe(sizeGreaterThan(photoAmount).because("Ожидание загрузки фотографии"), Duration.ofMillis(20000));
        $$(photos).last().find(photoImg).shouldBe(image.because("Фотография должна быть загружена"));

        return new PhotoAlbum();
    }

    public int countPhotos() {
        return $$(photos)
                .shouldHave(sizeGreaterThanOrEqual(0).because("Фотографии должны отображаться на странице"))
                .size();
    }

    public boolean checkFirstPhotoWasLoaded() {
        return $$(photos)
                .shouldBe(sizeGreaterThanOrEqual(0)
                        .because("В альбоме должны быть фотографии, иначе ни одно фото не было загружено"))
                .isEmpty();
    }

    public boolean checkFirstPhotoNotLoaded() {
        return $(emptyAlbumLabel).isDisplayed();
    }

    public PhotoAlbum deletePhoto() {
        $(editButton)
                .shouldBe(visible.because("Кнопка Редактировать должна отображаться"))
                .click();
        $$(deletePhotoButton)
                .shouldBe(sizeGreaterThan(0).because("Для удаления фотографии альбом должен быть не пустым"))
                .last().shouldBe(visible.because("При редактировании альбома у фотографии должна отображаться кнопка Удалить"))
                .click();
        $(backToAlbumButton)
                .shouldBe(visible.because("На названием альбом должна быть возможность нажать для возвращения к альбому"))
                .click();
        return new PhotoAlbum();
    }

    public UserAccountPhotosPage goBackToAlbums() {
        $(rootButton)
                .shouldBe(visible.because("На кнопку Фото у названия альбома должна быть возможность нажать для возвращения ко всем альбомам"))
                .click();
        return new UserAccountPhotosPage();
    }
}
