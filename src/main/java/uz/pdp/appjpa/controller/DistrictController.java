package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.DistrictDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.DistrictService;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @GetMapping
    public ApiResponse all(){
        ApiResponse apiResponse = districtService.getAll();
        return apiResponse;
    }
    @PostMapping
    public ApiResponse add(@RequestBody DistrictDto dto){
        ApiResponse apiResponse = districtService.addDistirict(dto);
        return apiResponse;
    }
    @GetMapping("/{id}") // Region Id orqali district(tumanlar) haqida ma'lumot olish
    public ApiResponse getByRegionId(@PathVariable Integer id){
        ApiResponse districtById = districtService.getDistrictById(id);
        return districtById;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@RequestBody DistrictDto dto,@PathVariable Integer id){
        ApiResponse apiResponse = districtService.editDistrict(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = districtService.deleteDistrict(id);
        return apiResponse;
    }
}
