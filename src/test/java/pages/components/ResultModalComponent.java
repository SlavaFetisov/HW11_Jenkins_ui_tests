package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class ResultModalComponent {

    private final SelenideElement resultModal = $("#resultModal");
    private final SelenideElement resultTable = $("#resultBody");

    @Step("Check result modal appearance")
    public ResultModalComponent checkResultModalAppearance() {
        resultModal.shouldHave(visible);

        return this;
    }

    @Step("Check result modal title")
    public ResultModalComponent checkResultModalTitle(String value) {
        resultModal.find(byId("example-modal-sizes-title-lg")).shouldHave(text(value));

        return this;
    }

    @Step("Check result field {fieldName}")
    public ResultModalComponent checkResultField(String fieldName, String fieldValue) {
        resultTable.$$("tr").findBy(text(fieldName)).shouldHave(text(fieldValue));

        return this;
    }


}