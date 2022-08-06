package ir.pasargad.meteorologydemo;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
public class MeteorologyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MeteorologyApplication.class, args);

    }

}
