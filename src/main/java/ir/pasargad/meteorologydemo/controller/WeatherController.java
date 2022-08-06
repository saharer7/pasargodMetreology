package ir.pasargad.meteorologydemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import ir.pasargad.meteorologydemo.entity.Weather;
import ir.pasargad.meteorologydemo.service.LocationService;
import ir.pasargad.meteorologydemo.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/weather")
@AllArgsConstructor
@Getter
@Setter
public class WeatherController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    LocationService locationService;
    @Autowired
    private Environment env;


    @GetMapping(value = "/test")
    public String test() {
        return "index";
    }


    @GetMapping(value = "/list/{pageNo}")
    public String showList(Model model, @PathVariable(name = "pageNo") int pageNo) {
        int size = 10;
        Page<Weather> page = weatherService.findAll(pageNo, size);
        List<Weather> weatherList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("weatherList", weatherList);

        return "weather/list";
    }

    @GetMapping(value = "/show/{id}")
    public String showSingle(Model model, @PathVariable(name = "id") Long id) {
        Weather weather = weatherService.findByIdWeather(id);
        String time = weather.getRegisterDate().getHour() + ":" + weather.getRegisterDate().getMinute();
        model.addAttribute("time", time);
        model.addAttribute("weather", weather);
        return "weather/show";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        weatherService.delete(id);
        return "redirect:/weather/list/1";
    }

    @GetMapping(value = "location/{location}/{pageNo}")
    public String findByLocation(Model model, @PathVariable(name = "location") long location, @PathVariable(name = "pageNo") int pageNo) {
        int size = 10;
        Page<Weather> page = weatherService.findByLocation(location, pageNo, size);
        List<Weather> weatherList = page.getContent();
        return null;
    }


    @Scheduled(fixedDelayString = "${delayDownload}")
    public void scheduleFixedDelayTask() throws JsonProcessingException {
        weatherService.save();
    }
}
