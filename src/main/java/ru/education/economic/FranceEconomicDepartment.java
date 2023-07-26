package ru.education.economic;

import org.springframework.context.annotation.Profile;
import ru.education.EconomicDepartment;

import java.math.BigDecimal;

@Profile("france")
public class FranceEconomicDepartment implements EconomicDepartment {
    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        long input = countElectricity * 1000000;
        int whole = (int) (input / 1_000_000_000L);
        BigDecimal lastPart = BigDecimal.valueOf(input - whole * 1_000_000_000L);
        BigDecimal result = new BigDecimal(0L);
        BigDecimal oneKoef = new BigDecimal(1_000_000_000L);
        BigDecimal secondKoef = new BigDecimal(0.99);
        BigDecimal lastKoef = new BigDecimal(0.5);
        for (int i = 0; i < whole; i++) {
            BigDecimal temp = oneKoef.multiply(lastKoef.multiply(secondKoef.pow(i)));
            result = result.add(temp);
        }
        BigDecimal temp = lastPart.multiply(lastKoef.multiply(secondKoef.pow(whole - 1)));
        result = result.add(temp);
        return result;
    }
}
