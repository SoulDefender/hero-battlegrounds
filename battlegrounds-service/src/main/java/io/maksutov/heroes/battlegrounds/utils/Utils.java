package io.maksutov.heroes.battlegrounds.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException();
    }

    public static BigDecimal scale2(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP);
    }
}
