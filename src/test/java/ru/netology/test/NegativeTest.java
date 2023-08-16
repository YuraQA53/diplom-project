package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;

public class NegativeTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
    }

    @DisplayName("Card - DECLINED card")
    @Test
    public void notPayDECLINEDCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCard = DataHelper.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.notSuccessfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Credit - DECLINED card")
    @Test
    public void shouldNotCreditDECLINEDCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var declinedCard = DataHelper.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCard);
        buyCredit.notSuccessfulCreditCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Card - All blank data")
    @Test
    public void notPayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - All blank data")
    @Test
    public void notCreditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank card number")
    @Test
    public void notPayEmptyCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank card number")
    @Test
    public void notCreditEmptyCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Year")
    @Test
    public void notPayEmptyYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Year")
    @Test
    public void notCreditEmptyYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Month")
    @Test
    public void notPayEmptyMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Month")
    @Test
    public void notCreditEmptyMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Holder")
    @Test
    public void notPayEmptyHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.requiredCardToFillIn();
    }

    @DisplayName("Credit - Blank Holder")
    @Test
    public void notCreditEmptyHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.requiredCreditToFillIn();
    }

    @DisplayName("Card - Blank CVV")
    @Test
    public void notPayEmptyCvv() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank CVV")
    @Test
    public void notCreditEmptyCvv() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Expired year")
    @Test
    public void notPayExpiredYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("Credit - Expired year")
    @Test
    public void notCreditExpiredYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("Card - Expired month")
    @Test
    public void notPayExpiredMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Expired month")
    @Test
    public void notCreditExpiredMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Invalid card number")
    @Test
    public void notPayInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid card number")
    @Test
    public void notCreditInvalidNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidNumber();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Wrong year.")
    @Test
    public void notPayWrongYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Wrong year")
    @Test
    public void notCreditWrongYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Wrong month")
    @Test
    public void notPayWrongMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getWrongMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Wrong month")
    @Test
    public void notCreditWrongMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getWrongMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Сard - Numeric holder's name")
    @Test
    public void notPayNumericHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Сredit - Numeric holder's name")
    @Test
    public void notCreditNumericHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getNumericName();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Invalid CVV")
    @Test
    public void notPayInvalidCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid CVV")
    @Test
    public void notCreditInvalidCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Zero card number")
    @Test
    public void notPayZeroNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.notSuccessfulCardPayment();
    }

    @DisplayName("Credit - Zero card number")
    @Test
    public void notCreditZeroNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCard();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.notSuccessfulCreditCardPayment();
    }

    @DisplayName("Card - Zero month")
    @Test
    public void notPayZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit- Zero month")
    @Test
    public void notCreditZeroMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Zero CVV")
    @Test
    public void notPayZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Zero CVV")
    @Test
    public void notCreditZeroCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

}