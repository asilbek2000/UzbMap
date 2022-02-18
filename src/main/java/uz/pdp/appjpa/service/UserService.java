package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.District;
import uz.pdp.appjpa.entity.User;
import uz.pdp.appjpa.payload.UserDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.DistrictRepository;
import uz.pdp.appjpa.repository.UserRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistrictRepository districtRepository;
    public ApiResponse getAll(){
        List<User> all = userRepository.findAll();
        return new ApiResponse("List of users",true,all);
    }
    public ApiResponse addUser(UserDto dto){
        Address address=new Address();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        Optional<District> byId = districtRepository.findById(dto.getDistrictId());
        District district = byId.get();
        address.setDistrict(district);
        Address savedAddress = addressRepository.save(address);
        User user=new User();
        user.setAddress(savedAddress);
        user.setName(dto.getName());
        User save = userRepository.save(user);
        return new ApiResponse("User added successfully",true,save);
    }
    public ApiResponse editUser(Integer id,UserDto dto){
        if (userRepository.existsById(id)) {
            Optional<User> byId = userRepository.findById(id);
            User user = byId.get();
            Optional<District> byId1 = districtRepository.findById(dto.getDistrictId());
            District district = byId1.get();


            Address address = user.getAddress();
            address.setDistrict(district);
            address.setStreet(dto.getStreet());
            address.setHomeNumber(dto.getHomeNumber());
            Address savedAddress = addressRepository.save(address);
            user.setName(dto.getName());
            user.setAddress(savedAddress);
            User save = userRepository.save(user);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("User not found",false);
    }
    public ApiResponse deleteUser(Integer id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("User does not exist",false);
    }
}
