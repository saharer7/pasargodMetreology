package ir.pasargad.meteorologydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_location")
@Entity
public class Location implements Serializable {

    private static final long serialVersionUID = 54872478945978345L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String country;
    @Column(length = 30)
    private String city;
    @Column(length = 30)
    private String province;

}
