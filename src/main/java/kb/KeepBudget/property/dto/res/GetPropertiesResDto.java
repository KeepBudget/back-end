package kb.KeepBudget.property.dto.res;

import kb.KeepBudget.utils.dto.PageNation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
@Builder
public class GetPropertiesResDto {

    private PageNation pageNation;
    private List<PropertyResDto> properties;

}
