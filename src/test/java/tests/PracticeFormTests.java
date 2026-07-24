package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.testdata.TestDataPracticeForm;

import static io.qameta.allure.Allure.step;

@Story("Registration form")
public class PracticeFormTests extends TestBase {
    TestDataPracticeForm testData = new TestDataPracticeForm();

    @Test
    @DisplayName("Successfull form submit")
    void successfulFormCompletionTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Filling all form fields", () ->{
            practiceFormPage.bannerClose()
                    .checkSubtitle(testData.subtitleText)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .selectGender(testData.genderTextValue)
                    .typeNumber(testData.userNumber)
                    .setDateOfBirth(testData.dateDay, testData.dateMonth, testData.dateYear)
                    .selectSubject(testData.subject)
                    .selectHobbie(testData.hobbie)
                    .uploadPicture(testData.uploadPicture)
                    .typeCurrentAddress(testData.currentAddress)
                    .selectState(testData.state)
                    .selectCity(testData.city)
                    .submitForm();
        });
        step("Check results", () -> {
            resultModalComponent.checkResultModalAppearance()
                    .checkResultModalTitle(testData.successSubmitText)
                    .checkResultField("Student Name", testData.firstName + " " + testData.secondName)
                    .checkResultField("Student Email", testData.userEmail)
                    .checkResultField("Gender", testData.genderTextValue)
//                .checkResultField("Date of Birth", testData.dateOfBirth)
                    .checkResultField("Subjects", testData.subject)
                    .checkResultField("Hobbies", testData.hobbie)
                    .checkResultField("Picture", testData.uploadPicture)
                    .checkResultField("Address", testData.currentAddress)
                    .checkResultField("State and City", testData.state + " " + testData.city);
        });
    }

    @Test
    @DisplayName("Filling required form fields")
    void requiredFieldsOnlyTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Filling required form fields", () -> {
            practiceFormPage.bannerClose()
                    .checkSubtitle(testData.subtitleText)
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .selectGender(testData.genderTextValue)
                    .typeNumber(testData.userNumber)
                    .setDateOfBirth(testData.dateDay, testData.dateMonth, testData.dateYear)
                    .submitForm();
        });
        step("Check results", () -> {
            resultModalComponent.checkResultModalAppearance()
                    .checkResultModalTitle(testData.successSubmitText);
        });
    }

    @Test
    @DisplayName("Empty form submit")
    void unsuccessfulSubmitTest(){
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Submit empty form", () -> {
            practiceFormPage.bannerClose()
                    .submitForm()
                    .checkFormErrorText(testData.formError);
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }

    @Test
    @DisplayName("Incorrect phone number submit")
    void incorrectNumberTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Incorrect phone number submit", () -> {
            practiceFormPage.bannerClose()
                    .typeFirstName(testData.firstName)
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .typeNumber(testData.incorrectUserNumber)
                    .submitForm();
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }

    @Test
    @DisplayName("Submit form with empty first name")
    void submitWithoutNameTest() {
        step("Open registration page", () ->
                practiceFormPage.openPage()
        );
        step("Submit form with empty first name", () -> {
            practiceFormPage.bannerClose()
                    .typeLastName(testData.secondName)
                    .typeEmail(testData.userEmail)
                    .typeNumber(testData.userNumber)
                    .submitForm();
        });
        step("Check error text", () -> {
            practiceFormPage.checkFormErrorText(testData.formError);
        });
    }
}