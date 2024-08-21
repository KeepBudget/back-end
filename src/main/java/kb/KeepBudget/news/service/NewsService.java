package kb.KeepBudget.news.service;

import kb.KeepBudget.news.dto.req.GetNewsReqDto;
import kb.KeepBudget.news.dto.res.GetNewsResDto;
import kb.KeepBudget.news.entity.News;
import kb.KeepBudget.news.entity.NewsDistrict;
import kb.KeepBudget.news.repository.NewsDistrictRepository;
import kb.KeepBudget.news.repository.NewsRepository;
import kb.KeepBudget.news.type.Category;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.utils.dto.PageNation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class NewsService {

    private final NewsDistrictRepository newsDistrictRepository;
    private final NewsRepository newsRepository;

    public GetNewsResDto getNewsList(User user, GetNewsReqDto reqDto) {

        int page = reqDto.getPage();
        int size = reqDto.getSize();

        Integer wishDistrictId = user.getWishDistrictId();
        Category category = reqDto.getCategory();
        List<NewsDistrict> newsDistricts = newsDistrictRepository.findAllByDistrictIdAndCategory(wishDistrictId, category);
        List<Long> newsIds = newsDistricts.stream().map(NewsDistrict::getNewsId).toList();
        List<News> newsList = newsRepository.findByIdInOrderByDateDesc(newsIds).stream()
                .skip((long)(page-1) * size)
                .limit(size)
                .toList();
        if(newsList.isEmpty()){
            throw new NoSuchElementException("There are no news that meet the conditions");
        }

        PageNation pageNation = PageNation.builder()
                .page(page)
                .totalCount((long) newsList.size())
                .build();

        return GetNewsResDto.builder()
                .pageNation(pageNation)
                .news(newsList)
                .build();
    }

}
