package ru.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.education.exceptions.NuclearFuelIsEmptyException;
import ru.education.exceptions.ReactorWorkException;

@Component
public class NuclearStation {
    private final ReactorDepartment reactorDepartment;
    private final SecurityDepartment securityDepartment;
    private int sumYearProduced;
    private int sumAllProduced;
    private int accidentCountAllTime;

    @Autowired
    public NuclearStation(ReactorDepartment reactorDepartment, @Lazy SecurityDepartment securityDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.securityDepartment = securityDepartment;
        this.sumYearProduced = 0;

        this.accidentCountAllTime = 0;
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
        sumAllProduced +=sumYearProduced;
        System.out.println("Атомная станция закончила работу.");
        System.out.printf("%s %s %s%n", "Выработано за год", sumYearProduced, "миллионов киловатт/часов.");
        resetSumYear();
        System.out.printf("%s %s %s%n", "Выработано за все время", sumAllProduced, "миллионов киловатт/часов.");
        System.out.println("Количество инцидентов за год: " + securityDepartment.getCountAccidents());
        securityDepartment.reset();
    }

    public void start(int year) {
        for (int i = 0; i < year; i++) {
            startYear();
            //тут по заданию непонятно нужно ли для каждого года скидывать счетчик
        }
        System.out.println("Количество инцидентов за всю работу станции: " + accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        this.accidentCountAllTime += count;
    }

    private void resetSumYear() {
        sumYearProduced = 0;
    }
}
