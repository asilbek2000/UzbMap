package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjpa.entity.Car;

import java.util.List;

public interface CarRepository extends JpaRepository <Car,Integer> {
    @Query("select c from Car c where c.user.id = ?1")
    List<Car> findAllByUserId(Integer id);
    @Query("select c from Car c where c.region.id = ?1")
    List<Car>findAllByRegionId(Integer id);
}
