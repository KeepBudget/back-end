package kb.KeepBudget.user.dto.res;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDto {

    private Long id;

    private String nickname;

    private String wishDistrict;

    private String wishPropertyType;

    private String wishTradeType;

    private Integer wishPropertyPrice;

    private Integer wishPropertySize;

}
