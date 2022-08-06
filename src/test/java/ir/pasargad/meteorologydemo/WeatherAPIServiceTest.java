package ir.pasargad.meteorologydemo;

import ir.pasargad.meteorologydemo.service.weatherAPI.WeatherAPIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RestClientTest(WeatherAPIService.class)
public class WeatherAPIServiceTest {

    @Autowired
    private WeatherAPIService weatherAPIService;

    @Test
    public void getWeather() {
        String weather  = (String) this.weatherAPIService.getWeather("Tehran");
        assertThat(weather.contains("Tehran"));

    }

}
