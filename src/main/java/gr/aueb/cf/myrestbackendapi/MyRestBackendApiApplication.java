package gr.aueb.cf.myrestbackendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyRestBackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestBackendApiApplication.class, args);
    }

}
