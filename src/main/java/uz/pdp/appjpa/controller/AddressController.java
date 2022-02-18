package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.AddressDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
@Autowired
    AddressService addressService;
@GetMapping
    public ApiResponse all(){
    ApiResponse apiResponse = addressService.getAll();
    return apiResponse;
}
@PostMapping
    public ApiResponse add(@RequestBody AddressDto addressDto){
    ApiResponse apiResponse = addressService.addAddress(addressDto);
    return apiResponse;
}
@GetMapping("{id}") //district id orqali addresslar haqida ma'lumot olish
    public ApiResponse getByDistrict(@PathVariable Integer id){
    ApiResponse allByDistrict = addressService.getAllByDistrict(id);
    return allByDistrict;
}
}
