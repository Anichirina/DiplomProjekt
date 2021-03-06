package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TripPage {
    private SelenideElement buttonBuyByDebit = $$(".button__text").find(exactText("Купить"));
    private SelenideElement buttonBuyCredit = $$(".button__text").find(exactText("Купить в кредит"));
    private SelenideElement paymentBySelectedWayHeader = $$(".heading").find(exactText("Путешествие дня"));

    public TripPage() {
        paymentBySelectedWayHeader.shouldBe(Condition.visible);
    }

    public CardPage selectBuyByDebitCard() {
        buttonBuyByDebit.click();
        return new CardPage();
    }

    public CardPage selectBuyByCreditCard() {
        buttonBuyCredit.click();
        return new CardPage();

    }
}
