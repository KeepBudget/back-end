package kb.KeepBudget.user;

import jakarta.validation.Valid;
import kb.KeepBudget.district.service.DistrictService;
import kb.KeepBudget.user.dto.req.NicknameReqDto;
import kb.KeepBudget.user.dto.req.UserReqDto;
import kb.KeepBudget.user.dto.req.WishDistrictReqDto;
import kb.KeepBudget.user.dto.res.UserResDto;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.service.UserService;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final DistrictService districtService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResult<String>> signUp(@Valid @RequestBody UserReqDto reqDto){
        String userToken = userService.signUp(reqDto);
        return ResponseEntity.created(null)
                .header("accessToken", userToken)
                .body(ApiUtils.success("회원가입 및 로그인 성공"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<String>> login(@Valid @RequestBody NicknameReqDto reqDto){
        String userToken = userService.login(reqDto);
        return ResponseEntity.ok()
                .header("accessToken", userToken)
                .body(ApiUtils.success("로그인 성공"));
    }

    @GetMapping("")
    public ResponseEntity<ApiResult<UserResDto>> getUser(@RequestHeader("accessToken") String token){
        User user = userService.getUser(token);
        String district = districtService.getDistrict(user.getWishDistrictId()).getName();
        return ResponseEntity.ok(ApiUtils.success(user.toUserResDto(district)));
    }

    @GetMapping("/raw")
    public ResponseEntity<ApiResult<User>> getUserRaw(@RequestHeader("accessToken") String token){
        User user = userService.getUser(token);
        return ResponseEntity.ok(ApiUtils.success(user));
    }

    @PutMapping("/wish-district")
    public ResponseEntity<ApiResult<UserResDto>> updateWishDistrict(
            @RequestHeader("accessToken") String token,
            @Valid @RequestBody WishDistrictReqDto reqDto
    ){
        User updatedUser = userService.updateWishDistrict(token, reqDto);
        String district = districtService.getDistrict(updatedUser.getWishDistrictId()).getName();
        return ResponseEntity.ok(ApiUtils.success(updatedUser.toUserResDto(district)));
    }

    @PostMapping("/check/nickname")
    public ResponseEntity<ApiResult<String>> checkNickname(@Valid @RequestBody NicknameReqDto reqDto){
        boolean existsNickname = userService.existsByNickname(reqDto.getNickname());
        if(existsNickname){
            return ResponseEntity.ok(ApiUtils.error("Nickname is already taken", HttpStatus.CONFLICT));
        }
        return ResponseEntity.ok(ApiUtils.success("Nickname is available"));
    }

    @PutMapping("")
    public ResponseEntity<ApiResult<UserResDto>> updateUser(
            @RequestHeader("accessToken") String token,
            @Valid @RequestBody UserReqDto reqDto
    ){
        User updatedUser = userService.updateUser(token, reqDto);
        String district = districtService.getDistrict(updatedUser.getWishDistrictId()).getName();
        return ResponseEntity.ok(ApiUtils.success(updatedUser.toUserResDto(district)));
    }
}
