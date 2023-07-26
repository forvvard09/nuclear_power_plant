package ru.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.education.exceptions.NuclearFuelIsEmptyException;
import ru.education.exceptions.ReactorWorkException;
import ru.education.util.CountryInfo;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_DOWN;

@Component
public class NuclearStation {
    private final ReactorDepartment reactorDepartment;
    private final SecurityDepartment securityDepartment;

    @Autowired
    private CountryInfo countryInfo;
    @Autowired
    private EconomicDepartment economicDepartment;

    private int sumYearProduced;
    private int sumAllProduced;
    private int accidentCountAllTime;
    private BigDecimal moneyAllPeriod;

    @Autowired
    public NuclearStation(ReactorDepartment reactorDepartment, @Lazy SecurityDepartment securityDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.securityDepartment = securityDepartment;
        this.sumYearProduced = 0;
        this.accidentCountAllTime = 0;
        this.moneyAllPeriod = BigDecimal.valueOf(0);
    }

    private void startYear() {
        System.out.println("Атомная станция начала работу");
        for (int i = 0; i < 365; i++) {
            try {
                sumYearProduced = sumYearProduced + reactorDepartment.run();
                reactorDepartment.stop();
            } catch (ReactorWorkException | NuclearFuelIsEmptyException e) {
                System.out.println("Внимание! Происходят работы на атомной станции! Электричества нет!");
            }
        }
        sumAllProduced += sumYearProduced;
        System.out.println("Атомная станция закончила работу.");
        System.out.printf("%s %s %s%n", "Выработано за год", sumYearProduced, "миллионов киловатт/часов.");
        System.out.println("Количество инцидентов за год: " + securityDepartment.getCountAccidents());
        System.out.println("Доход за год составил: " + economicDepartment.computeYearIncomes(sumYearProduced).setScale(6, ROUND_DOWN) + " " + countryInfo.getNameCurrency());
        resetSumYear();
        securityDepartment.reset();
    }

    public void start(int year) {
        System.out.println("Действие происходит в стране: " + countryInfo.getNameCountry());
        for (int i = 0; i < year; i++) {
            startYear();
        }
        System.out.println();
        System.out.println("======");
        System.out.printf("%s %s %s%n", "Выработано за все время", sumAllProduced, "миллионов киловатт/часов.");
        System.out.println("Количество инцидентов за всю работу станции: " + accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        this.accidentCountAllTime += count;
    }

    private void resetSumYear() {
        sumYearProduced = 0;
    }
}
