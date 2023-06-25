package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
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
        ChromeDriver driver = new ChromeDriver(options);
        open("http://localhost:9999/");

    }
    @Test
    void cardDeliveryArrangement(){
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $(byName("city")).shouldHave(Condition.value("Санкт-Петербург"));
        LocalDate date = LocalDate.of(2023, 10, 25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("25.10.2023");
        String formattedDate = date.format(formatter);
        $(byName("date")).setValue(formattedDate);
        $(byName("date")).shouldHave(Condition.value(formattedDate));
        $("[data-test-id=name] input").setValue("Кузнецов Анатолий");
        $(byName("name")).shouldHave(Condition.value("Кузнецов Анатолий"));
        $("[data-test-id=phone] input").setValue("+77468536452");
        $(byName("phone")).shouldHave(Condition.value("+77468536452"));
        $(byName("Забронировать")).click();
        $(byName("Забронировать")).shouldBe(Condition.checked);
        $(byText("Забронировать")).click();

    }



}
