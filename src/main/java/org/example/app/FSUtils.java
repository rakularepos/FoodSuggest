package org.example.app;

import java.time.LocalDate;

public class FSUtils {
    public static LocalDate getLocalDateFromDateString(String date) {
        if (date == null || date.length() != 8)
            return null;
        LocalDate localDate = LocalDate.of( Integer.valueOf(date.substring(0, 4)),
                Integer.valueOf(date.substring(4, 6)),
                Integer.valueOf(date.substring(6, 8)));
        return localDate;
    }
}
