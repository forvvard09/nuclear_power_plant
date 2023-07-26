package ru.education.economic;

import ru.education.EconomicDepartment;

import java.math.BigDecimal;

public class MaroccoEconomicDepartment implements EconomicDepartment {
    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        long input = countElectricity * 1000000;
        int whole = (int) (input / 5_000_000_000L);
        BigDecimal result = new BigDecimal(0);
        BigDecimal oneKf = new BigDecimal(5_000_000_000L);
        BigDecimal secondKf = new BigDecimal(5L);
        BigDecimal lastKf = new BigDecimal(6L);

        if (whole > 0) {
            result = result.add(oneKf.multiply(secondKf));
            result = result.add((BigDecimal.valueOf(input).subtract(oneKf)).multiply(lastKf));
        } else {
            result = result.add(BigDecimal.valueOf(input).multiply(lastKf));
        }
        return result;
    }
}
