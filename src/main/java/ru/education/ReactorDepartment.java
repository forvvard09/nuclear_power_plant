package ru.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.education.exceptions.NuclearFuelIsEmptyException;
import ru.education.exceptions.ReactorWorkException;

@Component
public class ReactorDepartment {
    private int countRun;
    private boolean workingStatus;

    @Autowired
    @Lazy
    private SecurityDepartment securityDepartment;

    public ReactorDepartment() {
        this.countRun = 0;
        this.workingStatus = false;
    }

    public int run() throws NuclearFuelIsEmptyException, ReactorWorkException {
        countRun++;
        if (countRun == 100) {
            countRun = 0;
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException();
        } else if (workingStatus) {
            securityDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже работает");
        }
        System.out.println("Реактор запускается на 1 день и производит 10 миллионов киловатт/часов");
        workingStatus = true;
        return 10;
    }

    public void stop() throws ReactorWorkException {
        if (!workingStatus) {
            throw new ReactorWorkException("Реактор уже выключен");
        } else {
            workingStatus = false;
        }
    }
}
