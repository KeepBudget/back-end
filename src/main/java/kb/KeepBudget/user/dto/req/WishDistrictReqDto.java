package kb.KeepBudget.user.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class WishDistrictReqDto {

    @NotNull(message = "희망 지역을 선택해주세요.")
    private Integer wishDistrictId;

}
