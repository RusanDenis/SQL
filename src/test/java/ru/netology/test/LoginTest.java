package ru.netology.test;

import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;

import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;


public class LoginTest {
    LoginPage loginPage;
    VerificationPage verificationPage;
    DashboardPage dashboardPage;

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clean() {
        SQLHelper.cleanDB();
    }

    @DisplayName("should Login With Valid User")
    @Test
    void shouldLoginWithValidUser() {
        loginPage = new LoginPage();
        verificationPage = loginPage.Validlogin(DataHelper.getAuthInfoWithValidUser());
        dashboardPage = verificationPage.validVerification(DataHelper.getVerificationCode());
    }

    @DisplayName("should Not Login With Invalid Login")
    @Test
    void shouldNotLoginWithInvalidLogin() {
        loginPage = new LoginPage();
        loginPage.login(DataHelper.getAuthInfoWithInvalidLogin());
        loginPage.errorMessage("Неверно указан логин или пароль");
    }

    @DisplayName("should Not Login With Invalid Pass")
    @Test
    void shouldNotLoginWithInvalidPass() {
        loginPage = new LoginPage();
        loginPage.login(DataHelper.getAuthInfoWithInvalidPass());
        loginPage.errorMessage("Неверно указан логин или пароль");
    }

    @DisplayName("should Get Error With Multiple Invalid Verification Code")
    @Test
    void shouldGetErrorWithMultipleInvalidVerificationCode() {
        loginPage = new LoginPage();
        verificationPage = loginPage.Validlogin(DataHelper.getAuthInfoWithValidUser());
        for (int i = 0; i <= 4; i++) {
            verificationPage.verification(DataHelper.generateRandomVerificationCode());
        }
        loginPage.errorMessage("Ошибка! Превышено количество попыток ввода кода!");
        loginPage.verifyPage();
    }


}