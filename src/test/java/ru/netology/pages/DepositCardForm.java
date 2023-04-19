package ru.netology.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DepositCardForm {
    private final SelenideElement mainElement = $x("//h1[text()='Пополнение карты']");
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("button[data-test-id='action-transfer'] ");

    public DepositCardForm shouldBeVisible() {
        mainElement.shouldBe(visible);

        return this;
    }

    public DepositCardForm setAmount(String amount) {
        amountInput
                .should(visible)
                .setValue(amount);
        return this;
    }

    public DepositCardForm setFrom(String cardNumber) {
        fromInput
                .should(visible)
                .setValue(cardNumber);
        return this;
    }

    public DepositCardForm clickTransferButton() {
        transferButton
                .should(visible)
                .click();
        return this;
    }


}
