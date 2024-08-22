package kb.KeepBudget.news.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import kb.KeepBudget.news.type.Category;
import kb.KeepBudget.news.type.SentimentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetNewsReqDto {

    @NotNull
    @Min(value = 1, message = "size은 1 이상이어야 합니다.")
    private int size;

    @NotNull
    @Min(value = 1, message = "page는 1 이상이어야 합니다.")
    private int page;

    @NotNull
    private Category category;

    private SentimentStatus status;

}
