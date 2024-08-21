package kb.KeepBudget.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class PageNation {

    private Integer page;
    private Long totalCount;

}
