package ir.pasargad.meteorologydemo;

import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableScheduling
@AllArgsConstructor
public class MeteorologyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MeteorologyApplication.class, args);

    }

}
