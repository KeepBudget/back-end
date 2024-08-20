package kb.KeepBudget.district.service;

import kb.KeepBudget.district.dto.DistrictResDto;
import kb.KeepBudget.district.entity.District;
import kb.KeepBudget.district.repository.DistrictRepository;
import kb.KeepBudget.utils.exceptions.DistrictNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;

    public List<District> getDistricts() {
        return districtRepository.findAllByOrderByNameAsc();
    }

    public District getDistrict(Integer districtId){
        return districtRepository.findById(districtId)
                .orElseThrow(DistrictNotExistException::new);
    }
}
