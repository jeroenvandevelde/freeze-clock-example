package com.example.javafreezeclockexample;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.javafreezeclockexample.DateTimeProvider.DEFAULT_ZONE_ID;

/**
 * Class that provides a way to freeze the {@link Clock} instance that is made in the {@link DateTimeProvider}
 */
public class FixedTimeExtension implements AfterEachCallback {
    private Field clockField = ReflectionUtils.findField(DateTimeProvider.class, "clock");
    private Clock fixedClock;

    private FixedTimeExtension(Clock fixedClock) {
        this.fixedClock = fixedClock;
        ReflectionUtils.makeAccessible(clockField);
        freezeClock(this.fixedClock);
    }

    public static FixedTimeExtension freezeOnLocalDate(LocalDate date) {
        return new FixedTimeExtension(getFixedClock(date));
    }

    public static FixedTimeExtension freezeOnLocalDateTime(LocalDateTime dateTime) {
        return new FixedTimeExtension(getFixedClock(dateTime));
    }

    public static FixedTimeExtension freezeNow() {
        return new FixedTimeExtension(getFixedClockNow());
    }

    private static Clock getFixedClockNow() {
        return Clock.fixed(Instant.now(), DEFAULT_ZONE_ID);
    }

    private static Clock getFixedClock(LocalDate date) {
        return Clock.fixed(date.atStartOfDay(DEFAULT_ZONE_ID).toInstant(), DEFAULT_ZONE_ID);
    }

    private static Clock getFixedClock(LocalDateTime dateTime) {
        return Clock.fixed(dateTime.atZone(DEFAULT_ZONE_ID).toInstant(), DEFAULT_ZONE_ID);
    }

    private void freezeClock(Clock fixedClock) {
        ReflectionUtils.setField(clockField, null, fixedClock);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        ReflectionUtils.setField(clockField, null, Clock.system(DEFAULT_ZONE_ID));
    }
}

