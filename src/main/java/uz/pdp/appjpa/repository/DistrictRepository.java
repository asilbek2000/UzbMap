package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.District;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Integer> {
    boolean  existsByName(String name);
 List<District> getAllByRegionId(Integer id);
}
