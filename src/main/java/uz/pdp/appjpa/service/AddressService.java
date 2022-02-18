package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.District;
import uz.pdp.appjpa.payload.AddressDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.DistrictRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

import static org.apache.coyote.http11.Constants.a;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistrictRepository districtRepository;

    public ApiResponse getAll(){
        List<Address> all = addressRepository.findAll();

        return new ApiResponse("List",true,all);
    }
    public ApiResponse addAddress(AddressDto dto){
        if (districtRepository.existsById(dto.getDistrictId())) {
            Optional<District> byId = districtRepository.findById(dto.getDistrictId());
            District district = byId.get();
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setDistrict(district);
            address.setHomeNumber(dto.getHomeNumber());
            Address save = addressRepository.save(address);
            return new ApiResponse("Added successfully",true,save);
        }
        return new ApiResponse("District not found",false);
    }
    public ApiResponse getAllByDistrict(Integer id){
        List<Address> allByDistrictId = addressRepository.getAllByDistrictId(id);
        return new ApiResponse("List",true,allByDistrictId);
    }


}
