package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Region;
import uz.pdp.appjpa.payload.RegionDto;
import uz.pdp.appjpa.repository.RegionRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;
    public ApiResponse getAll(){
        List<Region> all = regionRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addRegion(RegionDto dto) {
        if (!regionRepository.existsByName(dto.getName())) {
            Region region = new Region();
            region.setName(dto.getName());
            Region save = regionRepository.save(region);
            return new ApiResponse("Added successfully", true, save);
        }
        return new ApiResponse("Region already exists",false);
    }
    public ApiResponse getOne(Integer id){
        if (regionRepository.existsById(id)) {
            Optional<Region> byId = regionRepository.findById(id);
            Region region = byId.get();
            return new ApiResponse("region",true,region);

        }
        return new ApiResponse("region not found",false);
    }
    public ApiResponse deleteRegion(Integer id){
        if (regionRepository.existsById(id)) {
            regionRepository.deleteById(id);
            return new ApiResponse("found and deleted",true);

        }
        return new ApiResponse("region does not exist",false);
    }
    public ApiResponse editRegion(Integer id,RegionDto dto){
        if (regionRepository.existsById(id)) {
            Optional<Region> byId = regionRepository.findById(id);
            Region region = byId.get();
            region.setName(dto.getName());
            Region save = regionRepository.save(region);
            return new ApiResponse("foun and updated",true,save);
        }
        return new ApiResponse("Region not found ",false);
    }
}
