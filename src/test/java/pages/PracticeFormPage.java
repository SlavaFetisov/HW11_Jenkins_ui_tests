package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

    private final SelenideElement banner = $("#fixedban");
    private final SelenideElement practiceFormWrapper = $(".practice-form-wrapper");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderBar = $("#genterWrapper");
    private final SelenideElement numberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement subjectsDropdown = $("#subjectsDropdown");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement pictureUploader = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement stateContainer = $("#stateCity-wrapper");
    private final SelenideElement citySelect = $("#city");
    private final SelenideElement submit = $("#submit");
    private final SelenideElement errorText = $("#formError");



    @Step("Open page")
    public PracticeFormPage openPage() {
        open("/automation-practice-form.html");

        return this;
    }

    @Step("Check subtitle")
    public PracticeFormPage checkSubtitle(String subtitleText) {
        practiceFormWrapper.$(".subtitle").shouldHave(text(subtitleText));

        return this;
    }

    @Step("Type name")
    public PracticeFormPage typeFirstName(String value) {
        firstNameInput.click();
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Type last name")
    public PracticeFormPage typeLastName(String value) {
        lastNameInput.click();
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Type Email")
    public PracticeFormPage typeEmail(String value) {
        emailInput.click();
        emailInput.setValue(value);

        return this;
    }

    @Step("Select gender")
    public PracticeFormPage selectGender(String genderTextValue) {
        genderBar.$(byText(genderTextValue)).click();

        return this;
    }

    @Step("Type phone number")
    public PracticeFormPage typeNumber(String value) {
        numberInput.click();
        numberInput.setValue(value);

        return this;
    }

    @Step("Set date of birth")
    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        CalendarComponent calendar = new CalendarComponent();
        calendar.setDate(day, month, year);

        return this;
    }

    @Step("Select subject")
    public PracticeFormPage selectSubject(String value) {
        subjectsInput.click();
        subjectsDropdown.$(byText(value)).click();

        return this;
    }

    @Step("Select hobbie")
    public PracticeFormPage selectHobbie(String value) {
        hobbiesWrapper.$(byValue(value)).click();

        return this;
    }

    @Step("Upload picture")
    public PracticeFormPage uploadPicture(String value) {
        pictureUploader.uploadFromClasspath(value);

        return this;
    }

    @Step("Type current address")
    public PracticeFormPage typeCurrentAddress(String value) {
        currentAddressInput.click();
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("Select state")
    public PracticeFormPage selectState(String value) {
        stateSelect.scrollIntoView(true);
        stateSelect.click();
        stateContainer.$(byText(value)).click();

        return this;
    }

    @Step("Select city")
    public PracticeFormPage selectCity(String value) {
        citySelect.click();
        stateContainer.$(byText(value)).click();

        return this;
    }

    @Step("Submit form")
    public PracticeFormPage submitForm() {
        submit.click();

        return this;
    }

    @Step("Check form error text")
    public PracticeFormPage checkFormErrorText(String value) {
        errorText.shouldHave(text(value));

        return this;
    }

    @Step("Banner close")
    public PracticeFormPage bannerClose() {
        banner.$(byTagName("button")).click();

        return this;
    }
}