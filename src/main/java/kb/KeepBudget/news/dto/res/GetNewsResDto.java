package kb.KeepBudget.news.dto.res;

import kb.KeepBudget.news.entity.News;
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
public class GetNewsResDto {

    private PageNation pageNation;
    private List<News> news;

}
