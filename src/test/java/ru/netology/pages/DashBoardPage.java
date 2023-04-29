package ru.netology.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.models.Card;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement mainElement = $("[data-test-id='dashboard']");
    private final ElementsCollection cardsList = $$("ul[class*='CardList_cardBlock'] li div");


    public DashBoardPage shouldBeVisible() {
        mainElement.shouldBe(visible);
        return this;
    }

    public DashBoardPage clickDepositButton(int cartNumber) {
        cardsList
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .get(cartNumber - 1)
                .$("[data-test-id='action-deposit']")
                .click();

        return this;
    }

    public String getCardBalance(int cardNumber) {
        String cardInfo = cardsList
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .get(cardNumber - 1)
                .getText();

        int start = cardInfo.indexOf(balanceStart);
        int finish = cardInfo.indexOf(balanceFinish);
        return cardInfo.substring(start + balanceStart.length(), finish);
    }

}
