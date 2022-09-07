package ru.javaops.topjava.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

import static ru.javaops.topjava.util.Util.isBetweenHalfOpen;

@UtilityClass
public class VoteUtil {
    private final LocalTime CHECK_TIME = LocalTime.of(11, 0);

    public static Boolean isTimeForVote() {
        return isBetweenHalfOpen(LocalTime.now(), null, CHECK_TIME);
    }
}
