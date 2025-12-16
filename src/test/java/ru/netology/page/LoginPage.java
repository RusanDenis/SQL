package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement heading = $("h2");
    SelenideElement loginField = $("[data-test-id=login] input");
    SelenideElement passwordField = $("[data-test-id=password] input");
    SelenideElement actionButton = $("[data-test-id=action-login].button");
    SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public LoginPage() {
        verifyPage();
    }

    public VerificationPage Validlogin(DataHelper.AuthInfo authInfo) {
        login(authInfo);
        return new VerificationPage();
    }


    public void login(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        actionButton.click();

    }

    public void errorMessage(String expText) {
        errorMessage.shouldHave(Condition.text(expText));
    }

    public void verifyPage() {
        heading.shouldBe(Condition.visible);
    }
}