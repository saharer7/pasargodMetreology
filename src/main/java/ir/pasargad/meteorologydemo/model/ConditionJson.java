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
public class ConditionJson implements Serializable {

    private static final long serialVersionUID = -3172499186667797788L;

    private String text;
    private String icon;
    private int code;
}
