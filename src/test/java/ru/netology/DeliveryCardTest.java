package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    private String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Тула");
        String currentDate = generateDate(3);
        $("[data-test-id=data] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(currentDate);
        $("[data-test-id=name] input").setValue("Кузнецова Анастасия");
        $("[data-test-id=phone] input").setValue("+79109454927");
        $("[data-test-id=agreement]").click();
        $("button").shouldHave(text("Забронировать")).click();
        $("[data-test-id=notification]")
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + currentDate),
                        Duration.ofSeconds(15));
    }

}
