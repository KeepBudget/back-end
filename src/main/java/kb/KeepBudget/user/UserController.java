package kb.KeepBudget.user;

import jakarta.validation.Valid;
import kb.KeepBudget.district.entity.District;
import kb.KeepBudget.district.service.DistrictService;
import kb.KeepBudget.user.dto.req.NicknameReqDto;
import kb.KeepBudget.user.dto.req.SignUpReqDto;
import kb.KeepBudget.user.dto.res.UserResDto;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.service.UserService;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final DistrictService districtService;

    @PostMapping("/sign-up")
    public ApiResult<UserResDto> signUp(@Valid @RequestBody SignUpReqDto reqDto){
        User savedUser = userService.signUp(reqDto);
        String district = districtService.getDistrict(savedUser.getWishDistrictId()).getName();
        return ApiUtils.success(savedUser.toUserResDto(district));
    }

    @PostMapping("/login")
    public ApiResult<UserResDto> login(@Valid @RequestBody NicknameReqDto reqDto){
        User foundUser = userService.login(reqDto);
        String district = districtService.getDistrict(foundUser.getWishDistrictId()).getName();
        return ApiUtils.success(foundUser.toUserResDto(district));
    }


}
