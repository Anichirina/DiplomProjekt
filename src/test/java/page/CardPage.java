package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage {
    private SelenideElement heading = $$(".heading").find(exactText("Оплата по карте"));
    private final SelenideElement numberCardField = $(".input [placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthCard = $("input[placeholder='08']");
    private final SelenideElement yearCard = $("input[placeholder='22']");
    private final SelenideElement fieldCardOwner = $$(".input__top").find(text("Владелец")).parent();
    private final SelenideElement nameHolderCard = fieldCardOwner.$(".input__control");
    private final SelenideElement codeCVC = $("input[placeholder='999']");
    private final SelenideElement buttonContinue = $$("button").find(exactText("Продолжить"));
    private final SelenideElement improperFormat = $(byText("Неверный формат"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidExpiredDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expiredDatePass = $(byText("Истёк срок действия карты"));
    private final SelenideElement  approvedNotification = $(byText("Операция одобрена Банком."));
    private final SelenideElement declinedNotification = $(byText("Ошибка! Банк отказал в проведении операции."));


    public void fillCardInformationForSelectedWay(DataHelper.CardInfo cardInformation) {
        numberCardField.setValue(cardInformation.getNumber());
        monthCard.setValue(cardInformation.getMonth());
        yearCard.setValue(cardInformation.getYear());
        nameHolderCard.setValue(cardInformation.getHolder());
        codeCVC.setValue(cardInformation.getCvc());
        buttonContinue.click();
    }

    public void shouldImproperFormatNotification() {
        improperFormat.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void shouldEmptyFieldNotification() {

        emptyField.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void shouldInvalidExpiredDateNotification() {
        invalidExpiredDate.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void shouldExpiredDatePassNotification() {
        expiredDatePass.shouldBe(Condition.visible);
    }


    public void approved() {
        approvedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void declined() {
        declinedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
