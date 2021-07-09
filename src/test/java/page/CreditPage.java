package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement heading = $$(".heading").find(exactText("Кредит по данным карты"));
    private final SelenideElement cardNumber = $(".input [placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $(".input [placeholder='08']");
    private final SelenideElement year = $(".input [placeholder='22']");
    private final SelenideElement fieldCardOwner = $$(".input__top").find(text("Владелец")).parent();
    private final SelenideElement cardOwner = fieldCardOwner.$(".input__control");
    private final SelenideElement cvc = $(".input [placeholder='999']");
    private final SelenideElement proceedButton = $(".form-field button");
    private final SelenideElement approvedNotification = $(".notification_status_ok");
    private final SelenideElement declinedNotification = $(".notification_status_error");

    public CreditPage() {
        heading.shouldBe(Condition.visible);
    }

    public void credit(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        cardOwner.setValue(info.getHolder());
        cvc.setValue(info.getCvc());
        proceedButton.click();
    }

    public void approved() {
        approvedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void declined() {
        declinedNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}
