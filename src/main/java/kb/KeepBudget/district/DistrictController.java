package kb.KeepBudget.district;

import kb.KeepBudget.district.dto.DistrictResDto;
import kb.KeepBudget.district.entity.District;
import kb.KeepBudget.district.service.DistrictService;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping("")
    public ApiResult<List<District>> getDistricts(){
        List<District> districts = districtService.getDistricts();
        return ApiUtils.success(districts);
    }

}
