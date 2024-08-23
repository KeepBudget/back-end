package kb.KeepBudget.news;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import kb.KeepBudget.news.dto.req.GetNewsReqDto;
import kb.KeepBudget.news.dto.res.GetNewsResDto;
import kb.KeepBudget.news.dto.res.GetNewsSentimentResDto;
import kb.KeepBudget.news.service.NewsService;
import kb.KeepBudget.news.type.Category;
import kb.KeepBudget.news.type.SentimentStatus;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.user.service.UserService;
import kb.KeepBudget.utils.ApiUtils;
import kb.KeepBudget.utils.ApiUtils.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final UserService userService;
    private final NewsService newsService;

    @GetMapping("")
    public ResponseEntity<ApiResult<GetNewsResDto>> getNews(
            @RequestHeader("accessToken") String token,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Category category,
            @RequestParam(required = false) SentimentStatus status,
            @Valid GetNewsReqDto reqDto
    ){
        log.info("GetNewsReqDto = {}", reqDto);
        User user = userService.getUser(token);
        GetNewsResDto getNewsResDto = newsService.getNewsList(user, reqDto);
        return ResponseEntity.ok(ApiUtils.success(getNewsResDto));
    }

    // request : 사용자 정보 받음 -> 희망 지역 추출
    // response : 그 지역의 부동산 뉴스들의 감정분석 결과를 부정, 중립, 긍정 점수 return
    @GetMapping("/sentiment")
    public ResponseEntity<ApiResult<GetNewsSentimentResDto>> getNewsSentiment(@RequestHeader("accessToken") String token){
        User user = userService.getUser(token);
        GetNewsSentimentResDto newsSentimentResDto = newsService.getNewsSentiment(user.getWishDistrictId());
        return ResponseEntity.ok(ApiUtils.success(newsSentimentResDto));
    }

}
