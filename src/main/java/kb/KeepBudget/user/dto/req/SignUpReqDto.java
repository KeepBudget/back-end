package kb.KeepBudget.user.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import kb.KeepBudget.user.entity.User;
import lombok.Getter;

@Getter
public class SignUpReqDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotNull(message = "희망 지역을 선택해주세요.")
    private Integer wishDistrictId;

    @NotNull(message = "희망 부동산 타입을 선택해주세요.")
    private PropertyType wishPropertyType;

    @NotNull(message = "희망 부동산 거래 타입을 선택해주세요.")
    private TradeType wishTradeType;

    @Min(value = 0)
    @NotNull(message = "희망 가격을 입력해주세요")
    private Integer wishPropertyPrice;

    @Min(value = 0)
    @NotNull(message = "희망 평수를 입력해주세요")
    private Integer wishPropertySize;

    public User toUser(){
        return User.builder()
                .nickname(nickname)
                .wishDistrictId(wishDistrictId)
                .wishPropertyType(wishPropertyType)
                .wishTradeType(wishTradeType)
                .wishPropertyPrice(wishPropertyPrice)
                .wishPropertySize(wishPropertySize)
                .build();
    }

}
