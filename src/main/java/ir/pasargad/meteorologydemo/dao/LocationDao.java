package ir.pasargad.meteorologydemo.dao;

import ir.pasargad.meteorologydemo.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface LocationDao extends CrudRepository<Location,Long> {

    List<Location> findAll();


//    @Query(value = "select lc from Location lc where lc.city like :city")
    Location findByCityContainingIgnoreCase(String city);
}
