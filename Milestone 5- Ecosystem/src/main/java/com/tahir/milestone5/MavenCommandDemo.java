package com.tahir.milestone5;

public class MavenCommandDemo {

    public static void main(String[] args) {
        // These are the Maven commands you use most in real projects.
        String[] commands = {
                "mvn compile",      // compile source code
                "mvn test",         // run unit tests
                "mvn package",      // create final jar
                "mvn clean install" // clean + test + package + install to local repo
        };

        for (String command : commands) {
            System.out.println(command);
        }

        // In Spring Boot projects you also run:
        // mvn spring-boot:run
    }
}
