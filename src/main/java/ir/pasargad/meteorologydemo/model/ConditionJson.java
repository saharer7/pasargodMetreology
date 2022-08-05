package ir.pasargad.meteorologydemo.model;

import lombok.Data;

@Data
public class ConditionJson {

    private String text;
    private String icon;
    private int code;
}
