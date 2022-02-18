package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.District;
import uz.pdp.appjpa.entity.Region;
import uz.pdp.appjpa.payload.DistrictDto;
import uz.pdp.appjpa.repository.DistrictRepository;
import uz.pdp.appjpa.repository.RegionRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RegionRepository regionRepository;
    public ApiResponse getAll(){
        List<District> all = districtRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addDistirict(DistrictDto dto){
        if (regionRepository.existsById(dto.getRegionId())) {
            if (!districtRepository.existsByName(dto.getName())) {
                Optional<Region> byId = regionRepository.findById(dto.getRegionId());

                Region region = byId.get();
                District district=new District();
                district.setRegion(region);
                district.setName(dto.getName());
                District save = districtRepository.save(district);
                return new ApiResponse("Added successfully",true,save);
            }

            return new ApiResponse("district with this name already exist",false);
        }
        return new ApiResponse("Region not found",false);
    }
    public ApiResponse getDistrictById(Integer id){
        List<District> allByRegionId = districtRepository.getAllByRegionId(id);
        List<String > districtNames=new ArrayList<>();
        for (District district : allByRegionId) {
            districtNames.add(district.getName());
        }
        return new ApiResponse("List",true,districtNames);
    }
    public ApiResponse editDistrict(Integer id,DistrictDto dto){
        if (districtRepository.existsById(id)) {
            Optional<Region> byId = regionRepository.findById(dto.getRegionId());
            Region region = byId.get();
            Optional<District> byId1 = districtRepository.findById(id);
            District district = byId1.get();
            district.setName(dto.getName());
            district.setRegion(region);
            District save = districtRepository.save(district);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("District does not exist",false);
    }
    public ApiResponse deleteDistrict(Integer id){
        if (districtRepository.existsById(id)) {
            districtRepository.deleteById(id);
            return new ApiResponse("foudn and deleted",true);
        }
        return new ApiResponse("District does not exist",false);
    }
}
