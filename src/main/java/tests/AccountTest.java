package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import pages.FeedPage;
import pages.UserAccountPage;
import pages.LoginPage;
import pages.UserAccountPhotosPage;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AccountTest extends BaseTest {
    private UserAccountPage myAccount;

    @BeforeAll
    public static void openSiteAndLogin() {
        open("/", LoginPage.class).login();
    }

    @BeforeEach
    public void setup() {
        myAccount = new FeedPage().openAccountPage();
    }

    @Nested
    class TextFieldTest {
        @Test
        public void userCanSetStatusTest() {
            String status = "Статусный статус";
            myAccount.setStatus(status);
            assertEquals(status, myAccount.getStatus().val());
        }

        @AfterEach
        public void clear() {
            myAccount.setStatus("");
        }
    }

    @Nested
    class PhotosTest {
        private UserAccountPhotosPage myAccountPhotos;

//        @BeforeEach
//        public void setup() {
//            myAccount = new FeedPage().openAccountPage();
//        }

        @ParameterizedTest(name = "Тест: пользователь добавляет первое фото")
        @ValueSource(strings = {"Личные фотографии", "Разное"})
        public void userAddFirstPhotoTest(String album) {
            myAccountPhotos = myAccount
                    .goToPhotos()
                    .openAlbum(album)
                    .addPhoto(new File("src/main/resources/photos/vkeducation.jpg"));
            assertFalse(myAccountPhotos.checkFirstPhotoWasLoaded(), () -> "Первое фото успешно загружено.");
        }

        @AfterEach
        public void clear() {
            myAccountPhotos.deletePhoto();
        }
    }

}
