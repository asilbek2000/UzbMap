package uz.pdp.appjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Car {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 @Column(nullable = false)
 private String model;
 @Column(nullable = false)
 private String cartype;
 @Column(nullable = false)
 private String stateNumber;
 @Column(nullable = false)
 private Integer madeYear;
 @ManyToOne
 private User user;
 @ManyToOne
 private Region region;
}
