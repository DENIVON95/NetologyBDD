package ru.netology.pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private final SelenideElement mainElement = $("div[class*='Login_loginForm']");
    private final SelenideElement loginInput = $("[data-test-id='login'] input");
    private final SelenideElement passwordInput = $("[data-test-id='password'] input");
    private final SelenideElement loginButton = $("button[data-test-id='action-login']");

    public AuthPage open() {
        Selenide.open("/");
        mainElement.shouldBe(visible, Duration.ofSeconds(5));

        return this;
    }

    public AuthPage setLogin(String login) {
        loginInput
                .shouldBe(visible)
                .setValue(login);

        return this;
    }

    public AuthPage setPassword(String password) {
        passwordInput
                .shouldBe(visible)
                .setValue(password);

        return this;
    }

    public AuthPage clickLoginButton() {
        loginButton
                .shouldBe(visible)
                .click();

        return this;
    }

}