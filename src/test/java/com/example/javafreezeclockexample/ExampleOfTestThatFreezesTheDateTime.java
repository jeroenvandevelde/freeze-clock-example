package com.example.javafreezeclockexample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleOfTestThatFreezesTheDateTime {
    LocalDateTime localDateTime = LocalDateTime.of(
            LocalDate.of(2024, 2, 3),
            LocalTime.of(4, 3, 2));

    @RegisterExtension
    FixedTimeExtension fixedTimeExtension = FixedTimeExtension.freezeOnLocalDateTime(localDateTime);

    @Test
    void verifyIfDateTimeOfTheSystemEqualsPredefinedTestDateTime() {
        assertThat(new ClassThatUsesDateTime().getCurrentLocalDateTime())
                .isEqualTo(LocalDateTime.of(LocalDate.of(2024, 2, 3),
                        LocalTime.of(4, 3,2)));
    }

}
