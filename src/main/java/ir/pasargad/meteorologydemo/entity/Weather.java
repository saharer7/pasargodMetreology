package ir.pasargad.meteorologydemo.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_weather")
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_updated")
    private LocalDateTime last_updated;

    @Column(name = "temperature")
    private Double temp_c;

    @Column(name = "is_day")
    private int is_day;

    @Column(name = "wind_kph")
    private Double wind_kph;

    @Column(name = "wind_degree")
    private Double wind_degree;

    @Column(name = "wind_dir")
    private String wind_dir ;

    @Column(name = "pressure")
    private Double pressure_mb;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "cloud")
    private Double cloud;

    @Column(name = "feelslike")
    private Double feelslike_c;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "uv")
    private Double uv;

    @Column(name = "gust")
    private Double gust_kph;

    @Column(name = "condition_text")
    private String text ;

    @Column(name = "condition_icon")
    private String icon ;

    @Column(name = "condition_code")
    private int code ;

    @JoinColumn(name = "fk_location", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Location location;

}
