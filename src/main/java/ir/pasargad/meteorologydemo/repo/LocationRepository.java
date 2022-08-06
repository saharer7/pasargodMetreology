package ir.pasargad.meteorologydemo.repo;

import ir.pasargad.meteorologydemo.entity.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    List<Location> findAll();

    Location findByCityContainingIgnoreCase(String city);
}
