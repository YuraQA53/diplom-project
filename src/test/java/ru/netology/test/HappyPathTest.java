package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelperCard;
import ru.netology.data.SQLunits;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;

public class HappyPathTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }

    @DisplayName("Card - Declined card.")
    @Test
    public void shouldNotPayDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCard = DataHelperCard.getDeclinedCard();
        payCard.enterCardData(declinedCard);
        payCard.notSuccessfulCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusPayment = SQLunits.getStatusPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Credit - Declined card.")
    @Test
    public void shouldNotCreditDeclinedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var declinedCard = DataHelperCard.getDeclinedCard();
        buyCredit.enterCreditCardData(declinedCard);
        buyCredit.notSuccessfulCreditCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusPayment = SQLunits.getStatusCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Card - All blank data.")
    @Test
    public void shouldNotPayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelperCard.getAllFieldsEmpty();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - All blank data.")
    @Test
    public void shouldNotCreditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelperCard.getAllFieldsEmpty();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank card number.")
    @Test
    public void shouldNotPayEmptyCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCardEmpty = DataHelperCard.getCardNumberEmpty();
        payCard.enterCardData(fieldCardEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank card number.")
    @Test
    public void shouldNotCreditEmptyCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCardEmpty = DataHelperCard.getCardNumberEmpty();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Year.")
    @Test
    public void shouldNotPayEmptyYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldYearEmpty = DataHelperCard.getYearEmpty();
        payCard.enterCardData(fieldYearEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Year.")
    @Test
    public void shouldNotCreditEmptyYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldYearEmpty = DataHelperCard.getYearEmpty();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Month.")
    @Test
    public void shouldNotPayEmptyMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldMonthEmpty = DataHelperCard.getMonthEmpty();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Month.")
    @Test
    public void shouldNotCreditEmptyMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldMonthEmpty = DataHelperCard.getMonthEmpty();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Holder")
    @Test
    public void shouldNotPayEmptyHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldHolderEmpty = DataHelperCard.getHolderEmpty();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.requiredCardToFillIn();
    }

    @DisplayName("Credit - Blank Holder")
    @Test
    public void shouldNotCreditEmptyHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldHolderEmpty = DataHelperCard.getHolderEmpty();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.requiredCreditToFillIn();
    }

    @DisplayName("Card - Blank CVV.")
    @Test
    public void shouldNotPayEmptyCvv() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var fieldCvvEmpty = DataHelperCard.getCVVEmpty();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank CVV.")
    @Test
    public void shouldNotCreditEmptyCvv() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var fieldCvvEmpty = DataHelperCard.getCVVEmpty();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Expired year.")
    @Test
    public void shouldNotPayExpiredYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getExpiredYear();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("Credit - Expired year.")
    @Test
    public void shouldNotCreditExpiredYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getExpiredYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("Card - Expired month.")
    @Test
    public void shouldNotPayExpiredMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getExpiredMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Expired month.")
    @Test
    public void shouldNotCreditExpiredMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getExpiredMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Invalid card number.")
    @Test
    public void shouldNotPayInvalidNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getInvalidNumber();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid card number.")
    @Test
    public void shouldNotCreditInvalidNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getInvalidNumber();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Wrong year.")
    @Test
    public void shouldNotPayWrongYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getWrongYear();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Wrong year.")
    @Test
    public void shouldNotCreditWrongYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getWrongYear();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Wrong month.")
    @Test
    public void shouldNotPayWrongMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getWrongMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit - Wrong month.")
    @Test
    public void shouldNotCreditWrongMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getWrongMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Сard - Numeric holder's name.")
    @Test
    public void shouldNotPayNumericHolder() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getNumericName();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Сredit - Numeric holder's name.")
    @Test
    public void shouldNotCreditNumericHolder() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getNumericName();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Invalid CVV.")
    @Test
    public void shouldNotPayInvalidCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getInvalidCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid CVV.")
    @Test
    public void shouldNotCreditInvalidCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getInvalidCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Zero card number.")
    @Test
    public void shouldNotPayZeroNumber() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getZeroCard();
        payCard.enterCardData(invalidCard);
        payCard.notSuccessfulCardPayment();
    }

    @DisplayName("Credit - Zero card number.")
    @Test
    public void shouldNotCreditZeroNumber() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getZeroCard();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.notSuccessfulCreditCardPayment();
    }

    @DisplayName("Card - Zero month.")
    @Test
    public void shouldNotPayZeroMonth() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getZeroMonth();
        payCard.enterCardData(invalidCard);
        payCard.invalidDate();
    }

    @DisplayName("Credit- Zero month.")
    @Test
    public void shouldNotCreditZeroMonth() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getZeroMonth();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidDate();
    }

    @DisplayName("Card - Zero CVV")
    @Test
    public void shouldNotPayZeroCVV() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var invalidCard = DataHelperCard.getZeroCVV();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Zero CVV")
    @Test
    public void shouldNotCreditZeroCVV() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var invalidCard = DataHelperCard.getZeroCVV();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

}