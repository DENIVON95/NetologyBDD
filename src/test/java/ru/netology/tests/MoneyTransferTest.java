package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.models.Card;
import ru.netology.models.User;
import ru.netology.pages.AuthPage;
import ru.netology.pages.DashBoardPage;
import ru.netology.pages.DepositCardForm;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.utils.DataHelper.getUserWithCards;

public class MoneyTransferTest extends BaseTest {
    private static User user = getUserWithCards();
    DashBoardPage dashBoardPage;

    @BeforeEach
    public void login() {
        AuthPage authPage = new AuthPage();

        authPage
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .clickLoginButton()
                .setCode(user.getVerificationCode())
                .clickVerifyButton();

        dashBoardPage = new DashBoardPage().shouldBeVisible();

    }

    @ParameterizedTest
    @MethodSource("provideCardsInfo")
    public void shouldTransferMoneyFromOneCardToAnother(Card cardToTransferTo,
                                                        Card cardToWithdrawFrom, String transferCardExpectedBalance, String withdrawCardExpectedBalance) {
        DepositCardForm depositCardForm = new DepositCardForm();

        dashBoardPage
                .clickDepositButton(cardToTransferTo.getOrder());
        depositCardForm
                .setAmount("2000")
                .setFrom(cardToWithdrawFrom.getNumber())
                .clickTransferButton();

        String transferCardCurrentBalance = dashBoardPage.getCardBalance(cardToTransferTo.getOrder());
        String withdrawCardCurrentBalance = dashBoardPage.getCardBalance(cardToWithdrawFrom.getOrder());
        assertEquals(transferCardExpectedBalance, transferCardCurrentBalance,
                format("Текущий баланс карты '%s' не совпадает с ожидаемым '%s'", transferCardCurrentBalance, transferCardExpectedBalance));
        assertEquals(withdrawCardExpectedBalance, withdrawCardCurrentBalance,
                format("Текущий баланс карты '%s' не совпадает с ожидаемым '%s'", withdrawCardCurrentBalance, withdrawCardExpectedBalance));


    }

    private static Stream<Arguments> provideCardsInfo() {
        Card firstCard = user.getCards().get(0);
        Card secondCard = user.getCards().get(1);

        return Stream.of(
                Arguments.of(firstCard, secondCard, "12000", "8000"),
                Arguments.of(secondCard, firstCard, "10000", "10000")
        );
    }

}
