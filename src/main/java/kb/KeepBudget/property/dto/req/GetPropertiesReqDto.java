package kb.KeepBudget.property.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPropertiesReqDto {

    @NotNull
    @Min(value = 1, message = "size은 1 이상이어야 합니다.")
    private int size;

    @NotNull
    @Min(value = 1, message = "page는 1 이상이어야 합니다.")
    private int page;

}
