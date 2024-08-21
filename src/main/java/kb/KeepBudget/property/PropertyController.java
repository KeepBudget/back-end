package kb.KeepBudget.property;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kb.KeepBudget.property.dto.req.GetPropertiesReqDto;
import kb.KeepBudget.property.dto.res.GetPropertiesResDto;
import kb.KeepBudget.property.dto.res.PropertyResDto;
import kb.KeepBudget.property.service.PropertyService;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.service.UserService;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResult<GetPropertiesResDto>> getProperties(
            @RequestHeader("accessToken") String token,
            @RequestParam int page,
            @RequestParam int size,
            @Valid GetPropertiesReqDto reqDto
    ){
        log.info("GetPropertiesReqDto = {}", reqDto);
        User user = userService.getUser(token);
        GetPropertiesResDto getPropertiesResDto = propertyService.getProperties(user, reqDto);
        return ResponseEntity.ok(ApiUtils.success(getPropertiesResDto));
    }

}
