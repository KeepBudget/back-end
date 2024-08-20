package kb.KeepBudget.user;

import jakarta.validation.Valid;
import kb.KeepBudget.district.service.DistrictService;
import kb.KeepBudget.user.dto.req.SignUpReqDto;
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

    @PostMapping("/sign-up")
    public ApiResult signUp(@Valid @RequestBody SignUpReqDto reqDto){
        User savedUser = userService.signUp(reqDto);
        return ApiUtils.success(savedUser);
    }

}
