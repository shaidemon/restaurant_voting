package ru.daemon75.voting.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;

import static ru.daemon75.voting.util.Util.isBetweenHalfOpen;

@UtilityClass
public class VoteUtil {
//    @Value("${key.name}")
    private final LocalTime CHECK_TIME = LocalTime.of(11, 0);

    public static Boolean isTimeForVote() {
        return isBetweenHalfOpen(LocalTime.now(), null, CHECK_TIME);
    }
}
