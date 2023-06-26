package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {


    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void cardDeliveryArrangement() {
        open("http://localhost:9999/");

        $("[data-test-id=city] input").setValue("Самара");
        String planningDate = generateDate(5);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("01.07.2023");
        $("[data-test-id=name] input").setValue("Кузнецов Анатолий");
        $("[data-test-id=phone] input").setValue("+77468536452");
        $(".checkbox__box").click();
        $("button").click();
        $(byText("Забронировать")).click();
        $(withText(" Успешно!")).shouldBe(Condition.hidden, Duration.ofSeconds(5));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);


    }


}