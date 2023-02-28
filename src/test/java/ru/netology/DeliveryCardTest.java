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
    @Test
    public void shouldDeliveryCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Тула");
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        String verificationDate = LocalDate.now().plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(verificationDate);
        $("[data-test-id=name] input").setValue("Кузнецова Анастасия");
        $("[data-test-id=phone] input").setValue("+79109454927");
        $("[data-test-id=agreement]").click();
        $(".button").shouldHave(text("Забронировать")).click();
        $("[data-test-id=notification]")
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }

}
