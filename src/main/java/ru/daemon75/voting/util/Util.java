package ru.daemon75.voting.util;

import java.time.LocalDate;

public class Util {

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);
}
