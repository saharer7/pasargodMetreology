package ir.pasargad.meteorologydemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.entity.Weather;
import ir.pasargad.meteorologydemo.model.CurrentWeatherJson;
import ir.pasargad.meteorologydemo.model.LocationJson;
import ir.pasargad.meteorologydemo.repo.WeatherRepository;
import ir.pasargad.meteorologydemo.service.weatherAPI.WeatherAPIService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository repository;

    @Autowired
    LocationService locationService;

    @Autowired
    private WeatherAPIService weatherAPIService;

    @Override
    public void save() throws JsonProcessingException {

        List<Location> locationList = locationService.findAll();
        if (!locationList.isEmpty()) {
            for (Location location : locationList) {
                String object = (String) weatherAPIService.getWeather(location.getCity());
                Weather weather = fillProperties(object);
                repository.save(weather);
            }
        }
    }

    @Override
    public Page<Weather> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Weather> weathers = repository.findAll(pageable);
        return weathers;
    }

    @Override
    public Page<Weather> findByLocation(long location, int pageNo, int pageSize) {
        Location loc = locationService.findOne(location);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Weather> weathers = repository.findAllByLocation(loc, pageable);
        return weathers;
    }

    @Override
    public Weather findByIdWeather(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


    private Weather fillProperties(String object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ModelMapper modelMapper = new ModelMapper();
        JsonNode node = mapper.readTree(object);
        LocationJson location = mapper.convertValue(node.get("location"), LocationJson.class);
        CurrentWeatherJson currentWeather = mapper.convertValue(node.get("current"), CurrentWeatherJson.class);
        Weather weather = modelMapper.map(currentWeather, Weather.class);
        fillWeatherCondition(currentWeather, weather);
        fillWeatherDates(currentWeather, weather);
        weather.setIcon("https://" + weather.getIcon());
        weather.setLocation(locationService.findByCity(location.getName()));
        return weather;
    }

    private void fillWeatherCondition(CurrentWeatherJson currentWeather, Weather weather) {
        weather.setIcon(currentWeather.getCondition().getIcon());
        weather.setText(currentWeather.getCondition().getText());
        weather.setCode(currentWeather.getCondition().getCode());
    }

    private void fillWeatherDates(CurrentWeatherJson currentWeather, Weather weather) {

        weather.setRegisterDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        if (currentWeather.getLast_updated() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(currentWeather.getLast_updated(), formatter);
            weather.setLast_updated(dateTime);
        }
    }


}
