package ru.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityDepartment {
    private final NuclearStation nuclearStation;
    private int accidentCountPeriod;

    @Autowired
    public SecurityDepartment(NuclearStation nuclearStation) {
        this.nuclearStation = nuclearStation;
        this.accidentCountPeriod = 0;
    }

    public void addAccident() {
        this.accidentCountPeriod++;
    }

    public int getCountAccidents() {
        return accidentCountPeriod;
    }

    public void reset() {
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }
}
