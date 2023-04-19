package ru.netology.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.models.Card;
import ru.netology.models.User;
import ru.netology.pages.AuthPage;
import ru.netology.pages.DashBoardPage;
import ru.netology.pages.DepositCardForm;
import ru.netology.pages.VerificationPage;
import ru.netology.utils.JsonParser;

import java.util.stream.Stream;

public class MoneyTransferTest extends BaseTest {
    private static User user = JsonParser.getObjectFromJsonFile(User.class, "src/test/resources/testdata/users.json");
    private DepositCardForm depositCardForm;
    private DashBoardPage dashboard;

    @BeforeEach
    public void login() {
        AuthPage authPage = new AuthPage();
        VerificationPage verificationPage = new VerificationPage();
        depositCardForm = new DepositCardForm();
        dashboard = new DashBoardPage();

        authPage
                .open()
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .clickLoginButton();
        verificationPage
                .setCode(user.getVerificationCode())
                .clickVerifyButton();

        dashboard.shouldBeVisible();

    }

    @ParameterizedTest
    @MethodSource("provideCardsInfo")
    public void shouldTransferMoneyFromOneCardToAnother(Card cardToTransferTo,
                                                        Card cardToWithdrawFrom, String transferCardExpectedBalance, String withdrawCardExpectedBalance) {
        dashboard
                .clickDepositButton(cardToTransferTo);
        depositCardForm
                .setAmount("2000")
                .setFrom(cardToWithdrawFrom.getNumber())
                .clickTransferButton();
        dashboard
                .cardShouldHaveBalance(cardToTransferTo, transferCardExpectedBalance)
                .cardShouldHaveBalance(cardToWithdrawFrom, withdrawCardExpectedBalance);

    }

    private static Stream<Arguments> provideCardsInfo() {
        Card firstCard = user.getCards().get(0);
        Card secondCard = user.getCards().get(1);

        return Stream.of(
                Arguments.of(firstCard, secondCard, "12000", "8000"),
                Arguments.of(secondCard, firstCard, "10000", "10000")
        );
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }

}
