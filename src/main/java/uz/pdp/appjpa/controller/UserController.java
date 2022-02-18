package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.UserDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.CarService;
import uz.pdp.appjpa.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
    UserService userService;
  @Autowired
    CarService carService;
   @GetMapping
    public ApiResponse all(){
       ApiResponse all = userService.getAll();
       return all;
   }
   @PostMapping
    public ApiResponse add(@RequestBody UserDto dto){
       ApiResponse apiResponse = userService.addUser(dto);
       return apiResponse;
   }
   @PutMapping("/{id}")
   public ApiResponse deleteUser(@PathVariable Integer id,@RequestBody UserDto dto){
       ApiResponse apiResponse = userService.editUser(id, dto);
       return apiResponse;
   }
   @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
       ApiResponse apiResponse = userService.deleteUser(id);
       return apiResponse;
   }
   @GetMapping("/{id}") //ushbu metod orqali user id kiritiladi va shu userga tegishli cars haqida malumot olinadi
    public ApiResponse getCarsByUserId(@PathVariable Integer id){
       ApiResponse carsInfoByUserId = carService.findCarsInfoByUserId(id);
       return carsInfoByUserId;
   }

}
