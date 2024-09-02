import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "safari";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1400x800";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeEach
    void beforeEach() {
        open("/automation-practice-form");
    }

    @Test
    void correctFillingTest() {
        $("#firstName").setValue("Alexey");
        $("#lastName").setValue("Rotar");
        $("#userEmail").setValue("email@mail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("7123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("2024");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").sendKeys("Computer Science");
        $("#subjectsInput").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile (new File("src/test/resources/avatar.png"));
        $("#currentAddress").setValue("Perm, Russia");
        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaipur")).click();
        $("#submit").click();

        $(".modal-body").shouldHave(
                text("Alexey Rotar"),
                text("email@mail.com"),
                text("Male"),
                text("7123456789"),
                text("01 September,2024"),
                text("Computer Science"),
                text("Sports"),
                text("avatar.png"),
                text("Perm, Russia"),
                text("Rajasthan Jaipur"));
    }

    @Test
    void firstNameIsEmptyNegativeTest() {
        $("#submit").click();

        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#firstName").shouldBe(empty);
    }

    @Test
    void lastNameIsEmptyNegativeTest() {
        $("#submit").click();

        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldBe(empty);
    }

    @Test
    void genderRadioButtonsNotSelectedNegativeTest() {
        $("#submit").click();

        $("#genterWrapper").$(byText("Male")).shouldHave(cssValue("color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Female")).shouldHave(cssValue("color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Other")).shouldHave(cssValue("color", "rgb(220, 53, 69)"));
        $("#genterWrapper").$(byText("Male")).shouldNotBe(selected);
        $("#genterWrapper").$(byText("Female")).shouldNotBe(selected);
        $("#genterWrapper").$(byText("Other")).shouldNotBe(selected);
    }

    @Test
    void mobilePhoneIsEmptyNegativeTest() {
        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#userNumber").shouldBe(empty);
    }

    @Test
    void mobilePhoneNotEnoughDigitsNegativeTest() {
        $("#userNumber").setValue("123456789");

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void mobilePhoneIsNotDigitsNegativeTest1() {
        $("#userNumber").setValue("mobile num");

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void mobilePhoneIsNotDigitsNegativeTest2() {
        $("#userNumber").setValue("1234mobile");

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void mobilePhoneIsNotDigitsNegativeTest3() {
        $("#userNumber").setValue(" ");

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void mobilePhoneIsNotDigitsNegativeTest4() {
        $("#userNumber").setValue("#$%^&*!@()");

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest1() {
        $("#userEmail").setValue("email.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest2() {
        $("#userEmail").setValue("email@mail");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest3() {
        $("#userEmail").setValue("email@mail.c");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest4() {
        $("#userEmail").setValue("email@.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest5() {
        $("#userEmail").setValue("@mail.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest6() {
        $("#userEmail").setValue("#email@mail.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest7() {
        $("#userEmail").setValue("email@#mail.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest8() {
        $("#userEmail").setValue("email@mail.#com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest9() {
        $("#userEmail").setValue(" @ .com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emailNegativeTest10() {
        $("#userEmail").setValue("@.com");

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

}
