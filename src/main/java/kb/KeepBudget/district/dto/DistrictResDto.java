package kb.KeepBudget.district.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class DistrictResDto {
    private Integer id;
    private String name;
}
