package ir.pasargad.meteorologydemo.service.weatherAPI;

public interface WeatherAPIService<T, E> {

    T getWeather(E location);

    String createRequestUrl(E location);
}
