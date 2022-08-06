package ir.pasargad.meteorologydemo.service;

import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.repo.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository repository;

    @Override
    public Location findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Location location) {
        repository.save(location);
    }

    @Override
    public Location findByCity(String city) {
        return repository.findByCityContainingIgnoreCase(city);
    }
}
