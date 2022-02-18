package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> getAllByDistrictId(Integer id);
}
