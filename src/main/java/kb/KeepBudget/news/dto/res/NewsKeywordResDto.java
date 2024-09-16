package kb.KeepBudget.news.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Builder
public class NewsKeywordResDto {

    private String name;
    private Integer value;

}
