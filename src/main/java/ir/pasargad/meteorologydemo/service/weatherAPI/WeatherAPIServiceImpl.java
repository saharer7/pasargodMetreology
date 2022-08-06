package ir.pasargad.meteorologydemo.service.weatherAPI;

import ir.pasargad.meteorologydemo.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAPIServiceImpl implements WeatherAPIService<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private Environment env;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getWeather(String location) {

        logger.info("Requesting current weather for {}/{}", location);
        String template = restTemplate.getForObject(createRequestUrl(location), String.class);
        return template;
    }

    public String createRequestUrl(String location) {
        return env.getProperty("weatherApiUrl") + env.getProperty("weatherApiKey") + "&q=" + location + "&aqi=no";
    }
}
