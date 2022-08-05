package ir.pasargad.meteorologydemo.model;

import lombok.Data;

@Data
public class CurrentWeatherJson {

    private String last_updated;
    private double temp_c;
    private int is_day;
    private double wind_kph;
    private double wind_degree;
    private String wind_dir;
    private double pressure_mb;
    private double humidity;
    private double cloud;
    private double feelslike_c;
    private double uv;
    private double gust_kph;
    private ConditionJson condition;


}
