package ir.pasargad.meteorologydemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.entity.Weather;
import org.springframework.data.domain.Page;


public interface WeatherService {

    void save() throws JsonProcessingException;

    void delete(long id);

    Page<Weather> findAll(int pageNo, int pageSize);

    Page<Weather> findByLocation(long location, int pageNo, int pageSize);

    Weather findByIdWeather(long id);

}
