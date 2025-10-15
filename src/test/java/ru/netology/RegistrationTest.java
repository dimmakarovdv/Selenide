package ru.netology;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.swing.plaf.basic.BasicTextAreaUI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class RegistrationTest {

    @Test
    void shouldMustFillOutFormToHaveTheCardDelivered() {
        Selenide.open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $$(".menu-item").findBy(text("Москва")).click();

        $("[data-test-id=date] input").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        String meetingDate = LocalDate.now().plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(meetingDate);

        $("[data-test-id=name] input").setValue("Иванов Иван Иваныч");
        $("[data-test-id=phone] input").setValue("+79191468952");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
    }
}
