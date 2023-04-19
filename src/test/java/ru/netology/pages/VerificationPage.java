package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement mainElement = $("div[class*='Verification_verificationForm']");
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("button[data-test-id='action-verify'] ");

    public VerificationPage shouldBeVisible() {
        mainElement.shouldBe(visible);
        return this;
    }

    public VerificationPage setCode(String verificationCode) {
        codeInput
                .shouldBe(visible)
                .setValue(verificationCode);
        return this;
    }

    public VerificationPage clickVerifyButton() {
        verifyButton
                .shouldBe(visible)
                .click();
        return this;
    }


}
