package com.example.javafreezeclockexample;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static java.time.Clock.system;

/**
 * Class that uses a specific instance of {@link Clock}, so that {@link FixedTimeExtension}
 * can access it through reflection and freeze it by calling for example {@link Clock#fixed(Instant, ZoneId)}
 */
public class DateTimeProvider {
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("UTC");

    private static Clock clock = system(DEFAULT_ZONE_ID);

    private DateTimeProvider() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(clock).truncatedTo(ChronoUnit.MICROS);
    }

    public static Instant timestamp() {
        return Instant.now(clock).truncatedTo(ChronoUnit.MICROS);
    }
}
