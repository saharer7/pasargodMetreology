package ir.pasargad.meteorologydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentWeatherJson implements Serializable {

    private static final long serialVersionUID = -6085898325422557651L;

    private String last_updated;
    private float temp_c;
    private int is_day;
    private float wind_kph;
    private float wind_degree;
    private String wind_dir;
    private float pressure_mb;
    private float humidity;
    private float cloud;
    private float feelslike_c;
    private float uv;
    private float gust_kph;
    private ConditionJson condition;


}
