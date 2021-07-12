package test;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.BdHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.CardPage;
import page.CreditPage;
import page.TripPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestPaymentCard {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    class shouldResponseBasaData {
        @Test
        void shouldSuccessWithValidDebitCard() {
            val validCardInformation = DataHelper.getValidCardInformation();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(validCardInformation);
            paymentPage.approved();
            assertEquals("APPROVED", new BdHelper().getPaymentStatus());
            assertEquals(4500000, new BdHelper().getPaymentAmount());
            assertNull(new BdHelper().getCreditId());

        }

        @Test
        void shouldSuccesWithInvalidDebitCard() {
            val invalidCardInformation = DataHelper.getInvalidCardInformation();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(invalidCardInformation);
            paymentPage.approved();
            assertEquals("DECLINED", new BdHelper().getPaymentStatus());
            assertNull(new BdHelper().getCreditId());
        }
    }

    @Nested
    class shouldInvalidCardNumber {
        @Test
        void shouldGetNotificationEmptyFields() {
            val incorrectCardInfo = DataHelper.getInvalidCardDataIfEmptyAllFields();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldEmptyFieldNotification();
            paymentPage.shouldImproperFormatNotification();
            paymentPage.shouldValueFieldCodCVC();
            paymentPage.shouldValueFieldYear();
            paymentPage.shouldValueFieldMonth();
            paymentPage.shouldValueFieldNumberCard();
            paymentPage.shouldValueFieldHolder();
        }

        @Test
        public void shouldFailurePaymentIfEmptyNumberCard() {
            val incorrectCardInfo = DataHelper.getInvalidCardNumberIfEmpty();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldNumberCard();
        }

        @Test
        public void shouldIncorrectFieldNumberCardMiniNumber() {
            val incorrectCardInfo = DataHelper.getInvalidCardNumberInccorectNumber();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldNumberCard();
        }

        @Test
        public void shouldIncorrectFieldNumberCardOtherNumber() {
            val incorrectCardInfo = DataHelper.getInvalidCardNumberIfOutOfDatabase();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.declined();
        }
    }

    @Nested
    class shouldInvalidCardFieldMonth {
        @Test
        public void shouldInvalidMonthIfEmpty() {
            val incorrectCardInfo = DataHelper.getInvalidMonthIfEmpty();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldMonth();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfMore12() {
            val incorrectCardInfo = DataHelper.getInvalidNumberOfMonthIfMore12();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldInvalidExpiredDateNotification();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfOneDigit() {
            val incorrectCardInfo = DataHelper.getInvalidNumberOfMonthIfOneDigit();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldMonth();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfZero() {
            val incorrectCardInfo = DataHelper.getInvalidNumberOfMonthIfZero();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.approved();
        }
    }

    @Nested
    class shouldInvalidCardFieldYear {
        @Test
        public void shouldInvalidYearIfZero() {
            val incorrectCardInfo = DataHelper.getInvalidYearIfZero();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldExpiredDatePassNotification();
        }

        @Test
        public void shouldInvalidYearIfInTheFarFuture() {
            val incorrectCardInfo = DataHelper.getInvalidYearIfInTheFarFuture();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldInvalidExpiredDateNotification();

        }

        @Test
        public void shouldInvalidNumberOfYearIfOneDigit() {
            val incorrectCardInfo = DataHelper.getInvalidNumberOfYearIfOneDigit();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldYear();

        }

        @Test
        public void shouldInvalidYearIfEmpty() {
            val incorrectCardInfo = DataHelper.getInvalidYearIfEmpty();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldYear();
        }

        @Test
        public void shouldInvalidYearIfBeforeCurrentYear() {
            val incorrectCardInfo = DataHelper.getInvalidYearIfBeforeCurrentYear();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldExpiredDatePassNotification();

        }
    }

    @Nested
    class shouldInvalidCardFieldOwner {
        @Test
        public void shouldInvalidCardOwnerNameIfEmpty() {
            val incorrectCardInfo = DataHelper.getInvalidCardOwnerNameIfEmpty();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldHolder();
        }

        @Test
        public void shouldInvalidCardOwnerNameIfNumericAndSpecialCharacters() {
            val incorrectCardInfo = DataHelper.getInvalidCardOwnerNameIfNumericAndSpecialCharacters();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.approved();
        }

        @Test
        public void shouldInvalidCardOwnerNameIfRussianLetters() {
            val incorrectCardInfo = DataHelper.getInvalidCardOwnerNameIfRussianLetters();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.approved();
        }
    }

    @Nested
    class shouldInvalidCardFieldCodeCVC {
        @Test
        public void shouldInvalidCvcIfEmpty() {
            val incorrectCardInfo = DataHelper.getInvalidCvcIfEmpty();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvcIfOneDigit() {
            val incorrectCardInfo = DataHelper.getInvalidCvcIfOneDigit();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvcIfTwoDigits() {
            val incorrectCardInfo = DataHelper.getInvalidCvcIfTwoDigits();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvvIfThreeZero() {
            val incorrectCardInfo = DataHelper.getInvalidCvvIfThreeZero();
            val paymentPage = new TripPage().selectBuyByDebitCard();
            paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
            paymentPage.declined();
            final SelenideElement declinedNotification = $(".notification_status_error");
            declinedNotification.click();
            paymentPage.approved();
        }
    }

    //Проверяем неверные подписи строчек после правильного заполнения
    @Test
    public void shouldInvalidCardDataIfEmptyAllFieldsAndAfterFullInformationCard() {
        val incorrectCardInfo = DataHelper.getInvalidCardDataIfEmptyAllFields();
        val paymentPage = new TripPage().selectBuyByDebitCard();
        paymentPage.fillCardInformationForSelectedWay(incorrectCardInfo);
        paymentPage.shouldEmptyFieldNotification();
        paymentPage.shouldImproperFormatNotification();
        paymentPage.shouldValueFieldCodCVC();
        paymentPage.shouldValueFieldYear();
        paymentPage.shouldValueFieldMonth();
        paymentPage.shouldValueFieldNumberCard();
        paymentPage.shouldValueFieldHolder();
        val invalidCardInfo = DataHelper.getInvalidCardNumberIfOutOfDatabase();
        paymentPage.fillCardInformationForSelectedWay(invalidCardInfo);
        paymentPage.declined();
        paymentPage.shouldValueFieldCodCVC();
        paymentPage.shouldValueFieldHolder();
        paymentPage.shouldValueFieldNumberCard();
        final SelenideElement declinedNotification = $(".notification_status_error");
        declinedNotification.click();
        paymentPage.approved();
    }
}