package com.example.javafreezeclockexample;

import java.time.LocalDateTime;

public class ClassThatUsesDateTime {
    public LocalDateTime getCurrentLocalDateTime() {
        return DateTimeProvider.now();
    }
}
