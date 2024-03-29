package pl.adamorlowski.tablefootballratingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TableFootballRatingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableFootballRatingApiApplication.class, args);
    }

}
