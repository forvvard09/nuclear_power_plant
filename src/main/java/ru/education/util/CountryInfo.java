package ru.education.util;

import org.springframework.beans.factory.annotation.Value;

public class CountryInfo {
    @Value("${name.country}")
    private String nameCountry;
    @Value("${name.currency}")
    private String nameCurrency;

    public CountryInfo() {
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public String getNameCurrency() {
        return nameCurrency;
    }
}
