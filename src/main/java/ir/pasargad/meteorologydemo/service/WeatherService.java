package ir.pasargad.meteorologydemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.pasargad.meteorologydemo.entity.Weather;
import org.springframework.data.domain.Page;


public interface WeatherService {

    void save() throws JsonProcessingException;

    void delete(Long id);

    Page<Weather> findAll(int pageNo, int pageSize);

    Page<Weather> findByLocation(String location,int pageNo, int pageSize);

    Weather findByIdWeather(Long id);
}
