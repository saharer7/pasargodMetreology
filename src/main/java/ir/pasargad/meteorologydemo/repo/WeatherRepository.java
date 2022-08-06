package ir.pasargad.meteorologydemo.repo;

import ir.pasargad.meteorologydemo.entity.Location;
import ir.pasargad.meteorologydemo.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends PagingAndSortingRepository<Weather, Long> {

    Page<Weather> findAll(Pageable pageable);

    Page<Weather> findAllByLocation(Location location, Pageable pageable);

}
