package ru.daemon75.voting.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class VoteUtil {
    //    @Value("${key.name}")
    private final LocalTime CHECK_TIME = LocalTime.of(11, 0);

    public static Boolean isTimeForVote() {
        return LocalTime.now().isBefore(CHECK_TIME);
    }
}
