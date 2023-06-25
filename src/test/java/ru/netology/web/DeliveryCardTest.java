package ru.netology.web;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        open("http://localhost:9999/");

    }

    @Test
    void cardDeliveryArrangement() {

        $("[data-test-id=city] input").setValue("Самара");
        LocalDate date = LocalDate.of(2023, 10, 25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("25.10.2023");
        String formattedDate = date.format(formatter);
        $("[data-test-id=name] input").setValue("Кузнецов Анатолий");
        $("[data-test-id=phone] input").setValue("+77468536452");
        $(".checkbox__box").click();
        $("button").click();
        $(byText("Забронировать")).click();
        $(withText(" Успешно!")).shouldBe(Condition.hidden, Duration.ofSeconds(5));


    }


}