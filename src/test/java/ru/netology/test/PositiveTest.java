package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;

public class PositiveTest {
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

    @DisplayName("Successful card purchase.")
    @Test
    public void confirmPaymentAPROVEDCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var approvedCardInformation = DataHelper.getValidCard();
        payCard.enterCardData(approvedCardInformation);
        payCard.successfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful credit purchase")
    @Test
    public void confirmCreditAPROVEDCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        buyCredit.enterCreditCardData(approvedCardInformation);
        buyCredit.successfulCreditCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful card purchase with current M and Y.")
    @Test
    public void confirmPaymentCurrentMonthAndYear() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        payCard.enterCardData(validCardInformation);
        payCard.successfulCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

    @DisplayName("Successful credit purchase with current M and Y.")
    @Test
    public void confirmCreditWithCurrentMonthAndYear() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        buyCredit.enterCreditCardData(validCardInformation);
        buyCredit.successfulCreditCardPayment();

        var paymentId = SQLHelper.getPaymentId();
        var statusPayment = SQLHelper.getStatusCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusPayment);
    }

}