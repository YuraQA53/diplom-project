package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker enOption = new Faker(new Locale("en"));
    static Faker faker = new Faker(new Locale("ru"));
    static DataGenerator dataGenerator = new DataGenerator();
    static CardNumber cardNumber = new CardNumber();

    public static CardInformation getValidCard() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(1),
                dataGenerator.shiftMonth(1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getCurrentMonthAndYear() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0),
                dataGenerator.shiftMonth(0),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getDeclinedCard() {
        return new CardInformation(
                cardNumber.getDeclinedCardNumber(),
                dataGenerator.shiftYear(1),
                dataGenerator.shiftMonth(1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getAllFieldsEmpty() {
        return new CardInformation(
                " ",
                " ",
                " ",
                " ",
                " ");
    }

    public static CardInformation getCardNumberEmpty() {
        return new CardInformation(
                " ",
                dataGenerator.shiftYear(1),
                dataGenerator.shiftMonth(1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getYearEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                " ",
                dataGenerator.shiftMonth(1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getMonthEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(2),
                " ",
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getHolderEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(2),
                dataGenerator.shiftMonth(2),
                " ",
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getCVVEmpty() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(3),
                dataGenerator.shiftMonth(2),
                enOption.name().fullName(),
                "");
    }

    public static CardInformation getExpiredYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(-1),
                dataGenerator.shiftMonth(0),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getExpiredMonth() {
        return new CardInformation(cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(0),
                dataGenerator.shiftMonth(-1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidNumber() {
        return new CardInformation(
                cardNumber.getInvalidCardNumber(),
                dataGenerator.shiftYear(1),
                dataGenerator.shiftMonth(2),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getWrongYear() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(-50),
                dataGenerator.shiftMonth(2),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getWrongMonth() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(50),
                dataGenerator.shiftMonth(-1),
                enOption.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getNumericName() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5),
                dataGenerator.shiftMonth(2),
                Integer.toString(enOption.number().numberBetween(1, 999)),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5),
                dataGenerator.shiftMonth(2),
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(0, 99)));
    }

    public static CardInformation getZeroCard() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.shiftYear(5),
                dataGenerator.shiftMonth(2),
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getZeroMonth() {
        return new CardInformation(
                "0000000000000000",
                dataGenerator.shiftYear(0),
                "00",
                faker.name().fullName(),
                Integer.toString(enOption.number().numberBetween(100, 999)));
    }

    public static CardInformation getZeroCVV() {
        return new CardInformation(
                cardNumber.getApprovedCardNumber(),
                dataGenerator.shiftYear(5),
                dataGenerator.shiftMonth(2),
                faker.name().fullName(),
                "000");
    }

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String holder;
        private String CVV;
    }
}