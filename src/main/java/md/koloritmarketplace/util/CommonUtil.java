package md.koloritmarketplace.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    public static String changeDateTimeFormatToS(LocalDate localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static LocalDate changeDateTimeFormatToL(String s) {
        if (s == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(s, formatter);

    }
}
