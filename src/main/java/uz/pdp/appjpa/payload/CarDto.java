package uz.pdp.appjpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private Integer regionId;
    private Integer userId;
    private String model;
    private String stateNumber;
    private String cartype;
    private Integer year;

}
