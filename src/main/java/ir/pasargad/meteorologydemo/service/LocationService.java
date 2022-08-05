package ir.pasargad.meteorologydemo.service;

import ir.pasargad.meteorologydemo.entity.Location;

import java.util.List;

public interface LocationService {

    Location findOne(Long id);

    List<Location> findAll();

    void save(Location location);

    Location findByCity(String city);
}
