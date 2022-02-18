package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Car;
import uz.pdp.appjpa.entity.Region;
import uz.pdp.appjpa.entity.User;
import uz.pdp.appjpa.payload.CarDto;
import uz.pdp.appjpa.repository.CarRepository;
import uz.pdp.appjpa.repository.RegionRepository;
import uz.pdp.appjpa.repository.UserRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    UserRepository userRepository;
    public ApiResponse getAll(){
        List<Car> all = carRepository.findAll();
        return new ApiResponse("Car List",true,all);
    }
    public ApiResponse addCar(CarDto dto){
        Car car=new Car();
        car.setModel(dto.getModel());
        car.setCartype(dto.getCartype());
        car.setMadeYear(dto.getYear());
        car.setStateNumber(dto.getStateNumber());
        Optional<User> byId = userRepository.findById(dto.getUserId());
        User user = byId.get();
        Optional<Region> byId1 = regionRepository.findById(dto.getRegionId());
        Region region = byId1.get();
        car.setUser(user);
        car.setRegion(region);
        Car save = carRepository.save(car);
        return new ApiResponse("Added successfully",true,save);
    }
    public ApiResponse editCar(Integer id,CarDto dto){
        if (carRepository.existsById(id)) {
            Optional<Car> OptionalCar = carRepository.findById(id);
            Car car = OptionalCar.get();
            car.setCartype(dto.getCartype());
            car.setModel(dto.getModel());
            car.setStateNumber(dto.getStateNumber());
            car.setMadeYear(dto.getYear());
            Optional<User> Optionaluser = userRepository.findById(dto.getUserId());
            User user = Optionaluser.get();
            Optional<Region> OptionalRegion = regionRepository.findById(dto.getRegionId());
            Region region = OptionalRegion.get();
            car.setUser(user);
            car.setRegion(region);
            Car save = carRepository.save(car);
            return new ApiResponse("Added successfully",true,save);
        }
        return new ApiResponse("Car not found",false);
    }
        public ApiResponse deleteCar(Integer id){
            if (carRepository.existsById(id)) {
                carRepository.deleteById(id);
                return new ApiResponse("Found and deleted",true);
            }
            return new ApiResponse("Car does not exist",false);
        }
        public ApiResponse findCarsInfoByRegionId(Integer id){
            List<Car> allByRegionId = carRepository.findAllByRegionId(id);
            return new ApiResponse("List of Cars by Region Id",true,allByRegionId);
        }
        public ApiResponse findCarsInfoByUserId(Integer id){
            List<Car> allByUserId = carRepository.findAllByUserId(id);
            return new ApiResponse("List of cars by user Id",true,allByUserId);
        }
}
