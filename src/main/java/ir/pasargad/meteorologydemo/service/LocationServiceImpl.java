package ir.pasargad.meteorologydemo.service;

import ir.pasargad.meteorologydemo.dao.LocationDao;
import ir.pasargad.meteorologydemo.entity.Location;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationDao dao;

    @Override
    public Location findOne(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Location location) {
        dao.save(location);
    }

    @Override
    public Location findByCity(String city) {
        return dao.findByCityContainingIgnoreCase(city);
    }
}
