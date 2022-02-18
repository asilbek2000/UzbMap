package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Integer> {
    boolean  existsByName(String name);

}
