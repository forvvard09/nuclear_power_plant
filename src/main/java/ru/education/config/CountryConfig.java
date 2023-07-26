package ru.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import ru.education.EconomicDepartment;
import ru.education.economic.FranceEconomicDepartment;
import ru.education.economic.MaroccoEconomicDepartment;
import ru.education.util.CountryInfo;

@Configuration
public class CountryConfig {

    @Bean
    @Profile("france")
    public CountryInfo countryInfoFrance() {
        return new CountryInfo();
    }

    @Bean
    @Profile("france")
    public EconomicDepartment franceEconomicDepartmentBean() {
        return new FranceEconomicDepartment();
    }

    @Bean
    @Profile("marocco")
    public CountryInfo countryInfoMarocco() {
        return new CountryInfo();
    }

    @Bean
    @Profile("marocco")
    public EconomicDepartment maroccoEconomicDepartmentBean() {
        return new MaroccoEconomicDepartment();
    }

    @Configuration
    @Profile("france")
    @PropertySource("classpath:application-france.properties")
    static class France {
    }

    @Configuration
    @Profile("marocco")
    @PropertySource("classpath:application-marocco.properties")
    static class Marocco {
    }
}
