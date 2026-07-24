package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tests.testdata.TestDataTextBox;

import static io.qameta.allure.Allure.step;


public class TextBoxTests extends TestBase {
    TestDataTextBox testData = new TestDataTextBox();

    @Test
    @DisplayName("Successful filling and submit form")
    void successfulFormCompletionTest(){
        step("Open registration page", () ->
                textBoxPage.openPage()
        );
        step("Filling all form fields and submit", () -> {
            textBoxPage.typeUserName(testData.userName)
                    .typeUserEmail(testData.userEmail)
                    .typeCurrentAddress(testData.address)
                    .typePermanentAddress(testData.address)
                    .submitForm();
        });
        step("Check output values", () -> {
            textBoxPage.checkField("name", testData.expectedName)
                    .checkField("email", testData.expectedEmail)
                    .checkField("currentAddress", testData.expectedCurrentAddress)
                    .checkField("permanentAddress", testData.expectedPermanentAddress);
        });
    }

    @Test
    @DisplayName("Submit form with incorrect Email")
    void incorrectEmail() {
        step("Open registration page", () ->
                textBoxPage.openPage()
        );
        step("Type incorrect Email and submit", () -> {
            textBoxPage.typeUserEmail(testData.incorrectUserEmail)
                    .submitForm();
        });
        step("Check validation of Email field", () -> {
            textBoxPage.noOutput("email");
        });
    }
}