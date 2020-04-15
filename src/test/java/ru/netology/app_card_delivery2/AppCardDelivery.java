package ru.netology.app_card_delivery2;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.model.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class AppCardDelivery {

    private String date1 = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    private String date2 = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    void shouldRegisterCardDelivery() {
        open("http://localhost:9999");
        val user = DataGenerator.Registration.generateByFaker("ru");
        val name = user.getName();
        val phone = user.getPhoneNumber();
        val city = user.getCity();
        System.out.println(city);

        $("span[data-test-id='city'] input").setValue(city.substring(0,2));
        $$("div.menu div.menu-item").find(exactText(city)).click();

        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(date1);

        $("span[data-test-id='name'] input").setValue(name);
        $("span[data-test-id='phone'] input").setValue(phone);

        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='success-notification'] button").waitUntil(visible, 15000).click();

        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(date2);

        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='replan-notification'] button").waitUntil(visible, 15000).click();
        $("div.notification__content").waitUntil(text("Встреча успешно запланирована на " + date2),
                15000);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
}
