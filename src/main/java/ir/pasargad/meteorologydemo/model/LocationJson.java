package ir.pasargad.meteorologydemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LocationJson {

    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private String localtime_epoch;
    private String localtime;
}
