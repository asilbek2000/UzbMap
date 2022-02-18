package uz.pdp.appjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

     @AllArgsConstructor
     @NoArgsConstructor
     @Data
     @Entity(name = "users")
     public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     @OneToOne
     private Address address;
     private String name;

}
