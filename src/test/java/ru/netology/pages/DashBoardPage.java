package ru.netology.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.models.Card;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashBoardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement mainElement = $("[data-test-id='dashboard']");
    private final ElementsCollection cardsList = $$("ul[class*='CardList_cardBlock'] li");
    private final SelenideElement reloadButton = $("button[data-test-id='action-reload']");


    public DashBoardPage shouldBeVisible() {
        mainElement.shouldBe(visible);
        return this;
    }

    public DashBoardPage clickDepositButton(Card cardToDeposit) {
        cardsList
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asFixedIterable().stream().filter(c->c.lastChild().has(attribute("data-test-id", cardToDeposit.getUid()))).findFirst().get()
                .$("[data-test-id='action-deposit']")
                .click();

        return this;
    }

    private String getCardBalance(Card card) {
        String cardInfo = cardsList
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asFixedIterable().stream().filter(c->c.lastChild().has(attribute("data-test-id", card.getUid()))).findFirst().get()
                .getText();

        int start = cardInfo.indexOf(balanceStart);
        int finish = cardInfo.indexOf(balanceFinish);
        return cardInfo.substring(start + balanceStart.length(), finish);
    }

    public DashBoardPage cardShouldHaveBalance(Card card, String expectedBalance) {
        String currentCardBalance = getCardBalance(card);
        assertEquals(expectedBalance, currentCardBalance,
                format("Текущий баланс карты '%s' не совпадает с ожидаемым '%s'", currentCardBalance, expectedBalance));

        return this;
    }

    public DashBoardPage clickReloadButton() {
        reloadButton
                .shouldBe(visible)
                .click();

        return this;
    }


}
