package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EksiSozlukCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksiSozlukCloneApplication.class, args);
    }

}
