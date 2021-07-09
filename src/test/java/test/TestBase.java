package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.BdHelper;
import data.DataHelper;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CardPage;
import page.TripPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestBase {


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

    @Test
    void shouldSuccessWithValidDebitCard() throws SQLException {
        val validCardInformation = DataHelper.getValidCardInformation();
        val paymentPage = new TripPage().selectBuyByDebitCard();
        paymentPage.fillCardInformationForSelectedWay(validCardInformation);
        paymentPage.approved();
        assertEquals("APPROVED",new BdHelper().getPaymentStatus());
        assertEquals(4500000, new BdHelper().getPaymentAmount());
        assertNull(new BdHelper().getCreditId());

    }

}
