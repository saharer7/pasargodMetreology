package ir.pasargad.meteorologydemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.pasargad.meteorologydemo.dao.WeatherDao;
import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.entity.Weather;
import ir.pasargad.meteorologydemo.model.CurrentWeatherJson;
import ir.pasargad.meteorologydemo.model.LocationJson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherDao dao;
    @Autowired
    LocationService locationService;
    @Autowired
    private Environment env;

    @Override
    public void save() throws JsonProcessingException {

        List<Location> locationList = locationService.findAll();
        if (!locationList.isEmpty()) {
            for (Location location: locationList) {
                String object = downloadWeatherInfo(location);
                Weather weather = fillProperties(object);
                dao.save(weather);
            }
        }
    }

    private Weather fillProperties(String object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(object);
        LocationJson location = mapper.convertValue(node.get("location"), LocationJson.class);
        CurrentWeatherJson currentWeather = mapper.convertValue(node.get("current"), CurrentWeatherJson.class);
        ModelMapper modelMapper = new ModelMapper();
        Weather weather = modelMapper.map(currentWeather, Weather.class);
        weather.setIcon(currentWeather.getCondition().getIcon());
        weather.setText(currentWeather.getCondition().getText());
        weather.setCode(currentWeather.getCondition().getCode());
        weather.setRegisterDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        weather.setIcon("https://" + weather.getIcon());

        if (currentWeather.getLast_updated() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(currentWeather.getLast_updated(), formatter);
            weather.setLast_updated(dateTime);
        }
        weather.setLocation(locationService.findByCity(location.getName()));
        return weather;
    }

    private String downloadWeatherInfo(Location location) {
        RestTemplate restTemplate = new RestTemplate();
        String city = "&q=" + location.getCity() + "&aqi=no";
        String key = env.getProperty("weatherApiKey");
        String UrlSite = env.getProperty("weatherApiUrl");
        String url = UrlSite + key + city;
        String object = restTemplate.getForObject(url, String.class);
        return object;
    }


    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Page<Weather> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Weather> weathers = dao.findAll(pageable);
        return weathers;
    }

    @Override
    public Page<Weather> findByLocation(String location, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Weather> weathers = dao.findByLocation(location, pageable);
        return weathers;
    }

    @Override
    public Weather findByIdWeather(Long id) {
        return dao.findById(id).orElse(null);
    }

}
