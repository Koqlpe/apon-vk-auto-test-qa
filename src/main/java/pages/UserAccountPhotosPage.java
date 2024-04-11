package pages;

import org.openqa.selenium.By;
import pages.BasePage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserAccountPhotosPage extends BasePage {
    private final By albums = By.xpath("//*[@class=\"title__x4tyv\"]");
    private final By loadPhotoButton = By.xpath("//*[@class=\"html5-upload-link __before-upload\"]");
    private final By editButton = By.xpath(".//*[@data-l=\"t,editAlbum\"]");
    private final By deletePhotoButton = By.xpath(".//*[@class=\"photo-card_control __redesign\"]");
    private final By allPhotos = By.xpath(".//*[@class=\"photo-card_cnt\"]");
    private final By firstPhotoTitle = By.xpath(".//*[@class=\"photo-album_stub-title\"]");

    public UserAccountPhotosPage openAlbum(String albumName) {
        $$(albums).findBy(attribute("title", albumName)).click();
        return this;
    }

    public UserAccountPhotosPage addPhoto(File file) {
        $(loadPhotoButton).uploadFile(file);
        return this;
    }

    public boolean checkFirstPhotoWasLoaded() {
        return $(firstPhotoTitle).shouldBe(hidden.because("После добавления первой фотографии" +
                "предложение \"Загрузить фотографии\" должно пропадать.")).isDisplayed();
    }

    public UserAccountPhotosPage deletePhoto() {
        $(editButton).shouldBe(visible).click();
        $$(deletePhotoButton).last().shouldBe(visible).click();
        return this;
    }
}
