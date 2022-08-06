package ir.pasargad.meteorologydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_weather")
@Entity
public class Weather implements Serializable {


    private static final long serialVersionUID = -5920738997832462504L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_updated")
    private LocalDateTime last_updated;

    @Column(name = "temperature")
    private float temp_c;

    @Column(name = "is_day")
    private int is_day;

    @Column(name = "wind_kph")
    private float wind_kph;

    @Column(name = "wind_degree")
    private float wind_degree;

    @Column(name = "wind_dir")
    private String wind_dir;

    @Column(name = "pressure")
    private float pressure_mb;

    @Column(name = "humidity")
    private float humidity;

    @Column(name = "cloud")
    private float cloud;

    @Column(name = "feelslike")
    private float feelslike_c;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "uv")
    private float uv;

    @Column(name = "gust")
    private float gust_kph;

    @Column(name = "condition_text")
    private String text;

    @Column(name = "condition_icon")
    private String icon;

    @Column(name = "condition_code")
    private int code;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_location", referencedColumnName = "ID")
    private Location location;

}
