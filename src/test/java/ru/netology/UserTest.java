package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UserTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyPathBlockerUser() {

        UserData userBlocker = UserGenerator.generateUser("blocker");
        UserRegistration.registration(userBlocker);

        $("[data-test-id='login'] input").val(userBlocker.getLogin());
        $("[data-test-id='password'] input").val(userBlocker.getPassword());
        $x(".//button").click();
        $x("//h2").should(text("Личный кабинет"));
    }

    @Test
    public void happyPathActiveUser() {

        UserData userActive = UserGenerator.generateUser("active");
        UserRegistration.registration(userActive);

        $("[data-test-id='login'] input").val(userActive.getLogin());
        $("[data-test-id='password'] input").val(userActive.getPassword());
        $x(".//button").click();
        $x("//h2").should(text("Личный кабинет"));
        $("[data-test-id='error-notification'] div[class='notification__content']").
                shouldBe(visible).should(text("Пользователь заблокирован"));
        $x(".//button").click();
    }
}
