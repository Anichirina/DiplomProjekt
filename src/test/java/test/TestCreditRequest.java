package test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.BdHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.CreditPage;
import page.TripPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCreditRequest {
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
    class shouldResponseBaseData {
        @Test
        void shouldSuccessWithValidCreditCard() {
            val validCardInformation = DataHelper.getValidCardInformation();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(validCardInformation);
            creditPage.approved();
            assertEquals("APPROVED", new BdHelper().getCreditRequestStatus());
            assertNull(new BdHelper().getCreditId());

        }

        @Test
        void shouldSuccessWithInvalidCreditCard() {
            val invalidCardInformation = DataHelper.getInvalidCardInformation();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardInformation);
            assertEquals("DECLINED", new BdHelper().getCreditRequestStatus());
            assertNull(new BdHelper().getCreditId());
            creditPage.declined();
        }
    }

    @Nested
    class shouldInvalidCardNumber {
        @Test
        void shouldGetNotificationEmptyFields() {
            val invalidCardInformation = DataHelper.getInvalidCardDataIfEmptyAllFields();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardInformation);
            creditPage.shouldEmptyFieldNotification();
            creditPage.shouldImproperFormatNotification();
            creditPage.shouldValueFieldCodCVC();
            creditPage.shouldValueFieldHolder();
            creditPage.shouldValueFieldMonth();
            creditPage.shouldValueFieldYear();
            creditPage.shouldValueFieldNumberCard();
        }

        @Test
        public void shouldInvalidCardNumberIfEmpty() {
            val invalidCardNumber = DataHelper.getInvalidCardNumberIfEmpty();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldNumberCard();
        }

        @Test
        public void shouldIncorrectFieldNumberCardMiniNumber() {
            val invalidCardNumber = DataHelper.getInvalidCardNumberInccorectNumber();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldNumberCard();
        }

        @Test
        public void shouldIncorrectFieldNumberCardOtherNumber() {
            val invalidCardNumber = DataHelper.getInvalidCardNumberIfOutOfDatabase();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.declined();
        }

    }

    @Nested
    class shouldInvalidCardFieldMonth {
        @Test
        public void shouldInvalidMonthIfEmpty() {
            val invalidCardNumber = DataHelper.getInvalidMonthIfEmpty();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldMonth();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfMore12() {
            val invalidCardNumber = DataHelper.getInvalidNumberOfMonthIfMore12();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldInvalidExpiredDateNotification();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfOneDigit() {
            val invalidCardNumber = DataHelper.getInvalidNumberOfMonthIfOneDigit();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldMonth();

        }

        @Test
        public void shouldInvalidNumberOfMonthIfZero() {
            val invalidCardNumber = DataHelper.getInvalidNumberOfMonthIfZero();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.declined();
        }
    }

    @Nested
    class shouldInvalidCardFieldYear {
        @Test
        public void shouldInvalidYearIfZero() {
            val invalidCardNumber = DataHelper.getInvalidYearIfZero();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldExpiredDatePassNotification();
        }

        @Test
        public void shouldInvalidYearIfInTheFarFuture() {
            val invalidCardNumber = DataHelper.getInvalidYearIfInTheFarFuture();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldInvalidExpiredDateNotification();

        }

        @Test
        public void shouldInvalidNumberOfYearIfOneDigit() {
            val invalidCardNumber = DataHelper.getInvalidNumberOfYearIfOneDigit();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldYear();

        }

        @Test
        public void shouldInvalidYearIfEmpty() {
            val invalidCardNumber = DataHelper.getInvalidYearIfEmpty();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldYear();
        }

        @Test
        public void shouldInvalidYearIfBeforeCurrentYear() {
            val invalidCardNumber = DataHelper.getInvalidYearIfBeforeCurrentYear();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldExpiredDatePassNotification();
        }

    }

    @Nested
    class shouldInvalidCardFieldOwner {
        @Test
        public void shouldInvalidCardOwnerNameIfEmpty() {
            val invalidCardNumber = DataHelper.getInvalidCardOwnerNameIfEmpty();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldHolder();
        }

        @Test
        public void shouldInvalidCardOwnerNameIfNumericAndSpecialCharacters() {
            val invalidCardNumber = DataHelper.getInvalidCardOwnerNameIfNumericAndSpecialCharacters();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldHolder();
        }

        @Test
        public void shouldInvalidCardOwnerNameIfRussianLetters() {
            val invalidCardNumber = DataHelper.getInvalidCardOwnerNameIfRussianLetters();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldHolder();
        }

    }

    @Nested
    class shouldInvalidCardFieldCodeCVC {
        @Test
        public void shouldInvalidCvcIfEmpty() {
            val invalidCardNumber = DataHelper.getInvalidCvcIfEmpty();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvcIfOneDigit() {
            val invalidCardNumber = DataHelper.getInvalidCvcIfOneDigit();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvcIfTwoDigits() {
            val invalidCardNumber = DataHelper.getInvalidCvcIfTwoDigits();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldCodCVC();
        }

        @Test
        public void shouldInvalidCvvIfThreeZero() {
            val invalidCardNumber = DataHelper.getInvalidCvvIfThreeZero();
            val paymentPage = new TripPage();
            paymentPage.selectBuyByCreditCard();
            val creditPage = new CreditPage();
            creditPage.creditCardFullInformation(invalidCardNumber);
            creditPage.shouldValueFieldCodCVC();
        }
    }

    //Проверяем неверные подписи строчек после правильного заполнения
    @Test
    public void shouldInvalidCardDataIfEmptyAllFieldsAndAfterFullInformationCard() {
        val paymentPage = new TripPage();
        paymentPage.selectBuyByCreditCard();
        val creditPage = new CreditPage();
        creditPage.shouldInvalidCardDataIfEmptyAllFieldsAndAfterFullInformationCard();
        creditPage.declined();
        creditPage.shouldValueFieldCodCVC();
        creditPage.shouldValueFieldHolder();
        creditPage.shouldValueFieldNumberCard();
        final SelenideElement declinedNotification = $(".notification_status_error");
        declinedNotification.click();
        creditPage.approved();
    }
}



