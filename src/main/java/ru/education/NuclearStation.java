package ru.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.education.exceptions.NuclearFuelIsEmptyException;
import ru.education.exceptions.ReactorWorkException;

@Component
public class NuclearStation {
    private final ReactorDepartment reactorDepartment;
    private int sumYearProduced;

    @Autowired
    public NuclearStation(ReactorDepartment reactorDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.sumYearProduced = 0;
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
        System.out.println("Атомная станция закончила работу.");
        System.out.printf("%s %s %s%n", "За год Выработано", sumYearProduced, "миллионов киловат/часов.");
    }

    public void start(int year) {
        for (int i = 0; i < year; i++) {
            startYear();
            //тут по заданию непонятно нужно ли для каждого года скидывать счетчик
            //sumYearProduced = 0;
        }
    }
}
