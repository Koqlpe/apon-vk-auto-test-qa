package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import pages.*;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.TestBot.newBuilder;

public class AccountTest extends BaseTest {
    private UserAccountPage myAccount;
    private ToolBar toolBar = new ToolBar();
    TestBot testBot = newBuilder().buildDefault();

    @BeforeAll
    public static void openSiteAndLogin() {
        TestBot testBot = newBuilder().buildDefault();

        new LoginPage()
                .setUsername(testBot.getBotUsername())
                .setPassword(testBot.getBotPassword())
                .clickLoginButton();
    }

    @Nested
    class StatusFieldTest {
        @BeforeEach
        public void setup() {
            myAccount = new NavigationPanel().goToProfile();
        }

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
        UserAccountPhotosPage photosPage;
        private PhotoAlbum album;
        private NavigationPanel navigation = new NavigationPanel();
        File img = new File("src/main/resources/photos/vkeducation.jpg");

        @BeforeEach
        public void setup() {
            //photosPage = navigation.goToPhoto();
            open("/profile/" + testBot.getId() + "/photos");
        }

        @ParameterizedTest(name = "Тест: пользователь добавляет фото")
        @ValueSource(strings = {"Личные фотографии", "Разное"})
        public void userAddPhotoTest(String albumName) {
            photosPage = new UserAccountPhotosPage();
            album = photosPage
                    .openAlbum(albumName);

            int expectedPhotosAmount = album.countPhotos() + 1;
            album.addPhoto(img);

            assertEquals(expectedPhotosAmount, album.countPhotos(), () -> albumName + ": Количество фотографий не совпадает");
        }

        @Disabled("Был переработан на тест выше, планируется этот откорректировать, а здесь продемонстрировать ещё одну аннотацию JUnit :)")
        @ParameterizedTest(name = "Тест: пользователь добавляет первое фото")
        @ValueSource(strings = {"Личные фотографии", "Разное"})
        public void userAddFirstPhotoTest(String album) {
            this.album = myAccount
                    .goToPhotos()
                    .openAlbum(album)
                    .addPhoto(img);

            assertFalse(this.album.checkFirstPhotoWasLoaded(), "Первое фото успешно загружено.");
        }

        @AfterEach
        public void clear() {
            album.deletePhoto().goBackToAlbums();
        }
    }

    @AfterAll
    public static void cleanUp() {
        ToolBar toolBar = new ToolBar();
        toolBar.logOut();
    }
}
