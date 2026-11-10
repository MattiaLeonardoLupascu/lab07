package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);
    
        private final int days;

        Month(final int days){
            this.days = days;
        }
    }

    public Month fromString(String string) {
        string = string.toLowerCase();
        int corrispondingMonths = 0;
        Month lastFoundMonth = null;
        for (Month elem : Month.values()) {
            String stringElem = elem.toString();
            stringElem = stringElem.toLowerCase();
            if (stringElem.startsWith(string)) {
                lastFoundMonth = elem;
                corrispondingMonths++;
            }
        }
        if (lastFoundMonth == null || corrispondingMonths > 1) {
            throw new IllegalArgumentException();
        } else {
            return lastFoundMonth;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                final Month m1 = fromString(s1);
                final Month m2 = fromString(s2);
                return Integer.compare(m1.days, m2.days);
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                final Month m1 = fromString(s1);
                final Month m2 = fromString(s2);
                return m1.compareTo(m2);
            }
        };
    }
}