package kb.KeepBudget.property.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyResDto {

    private Long id;

    private String address;

    private String buildingName;

    private Integer floor;

    private Double price;

    private Double size;

}
