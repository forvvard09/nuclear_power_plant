package ru.education;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.education.config.MyConfig;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        NuclearStation nuclearStation = context.getBean("nuclearStation", NuclearStation.class);
        nuclearStation.start(3);
    }
}
