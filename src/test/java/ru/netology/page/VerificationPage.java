package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    SelenideElement codeField = $("[data-test-id=code] input");
    SelenideElement actionButton = $("[data-test-id = action-verify]");
    SelenideElement errorNotification = $("[data-test-id = error-notification]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public void verification(DataHelper.VerificationCode code) {
        codeField.setValue(code.getCode());
        actionButton.click();

    }

    public DashboardPage validVerification(DataHelper.VerificationCode code) {
        verification(code);
        return new DashboardPage();
    }

    public void errorVerificationCode(String expText) {
        errorNotification.shouldHave(Condition.text(expText));
    }
}