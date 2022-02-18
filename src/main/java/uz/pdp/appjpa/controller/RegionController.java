package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.RegionDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.CarService;
import uz.pdp.appjpa.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionService regionService;
    @Autowired
    CarService carService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = regionService.getAll();
        return apiResponse;

    }
    @PostMapping
    public ApiResponse add(@RequestBody RegionDto dto){
        ApiResponse apiResponse = regionService.addRegion(dto);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = regionService.deleteRegion(id);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody RegionDto dto){
        ApiResponse apiResponse = regionService.editRegion(id, dto);
        return apiResponse;
    }
   @GetMapping("/{id}")//ushbu metod orqali  region id kiritb shu regionga tegishli mashinalar haqida ma'lumot olinadi
    public ApiResponse getCarsByRegion(@PathVariable Integer id){
       ApiResponse carsInfoByRegionId = carService.findCarsInfoByRegionId(id);
       return carsInfoByRegionId;
   }
}
