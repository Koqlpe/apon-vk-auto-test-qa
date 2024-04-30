package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import pages.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AccountTest extends BaseTest {
    private UserAccountPage myAccount;
    private ToolBar toolBar = new ToolBar();

    @BeforeAll
    public static void openSiteAndLogin() {
        String botUsername = "technopol33";
        String botPassword = "technopolisPassword";

        new LoginPage()
                .setUsername(botUsername)
                .setPassword(botPassword)
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
        private UserAccountPhotosPage myAccountPhotos;
        private NavigationPanel navigation = new NavigationPanel();

        @BeforeEach
        public void setup() {
            myAccount = navigation.goToProfile();
        }

        @ParameterizedTest(name = "Тест: пользователь добавляет фото")
        @ValueSource(strings = {"Личные фотографии", "Разное"})
        public void userAddPhotoTest(String album) {
            myAccountPhotos = myAccount
                    .goToPhotos()
                    .openAlbum(album);

            int expectedPhotosAmount = myAccountPhotos.countPhotos() + 1;
            File img = new File("src/main/resources/photos/vkeducation.jpg");
            myAccountPhotos.addPhoto(img);

            assertEquals(expectedPhotosAmount, myAccountPhotos.countPhotos(), () -> album + ": Количество фотографий не совпадает");
        }

        @Disabled("Был переработан на тест выше, планируется этот откорректировать, а здесь продемонстрировать ещё одну аннотацию JUnit :)")
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

    @AfterAll
    public static void cleanUp() {
        ToolBar toolBar = new ToolBar();
        toolBar.logOut();
    }
}
