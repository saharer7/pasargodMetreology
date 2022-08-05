package ir.pasargad.meteorologydemo.dao;

import ir.pasargad.meteorologydemo.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface WeatherDao extends CrudRepository<Weather,Long> {

    Page<Weather> findAll(Pageable pageable);

    @Query(value = "select * from tb_weather we join tb_location lc on we.fk_location=lc.id where lc.city= :location",nativeQuery = true)
    Page<Weather> findByLocation(@Param("location") String location, Pageable pageable);

}
