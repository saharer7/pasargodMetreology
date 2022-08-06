package ir.pasargad.meteorologydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationJson implements Serializable {

    private static final long serialVersionUID = -4398254011306251744L;

    private String name;
    private String region;
    private String country;
    private float lat;
    private float lon;
    private String tz_id;
    private String localtime_epoch;
    private String localtime;
}
