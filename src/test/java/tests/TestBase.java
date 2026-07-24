package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;
import pages.TextBoxPage;
import pages.components.ResultModalComponent;
import helpers.Attach;
import tests.secrets.Secrets;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    ResultModalComponent resultModalComponent = new ResultModalComponent();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void setupSelenideEnv(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";
        Configuration.browserVersion = "149.0";
        Configuration.browser = "chrome";
        Configuration.timeout = 20000; // default 4000
        Configuration.remote = Secrets.remoteBrowserUrl;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = Secrets.remoteBrowserUrl;
    }

    @BeforeEach
    void beforeEachTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotsAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}