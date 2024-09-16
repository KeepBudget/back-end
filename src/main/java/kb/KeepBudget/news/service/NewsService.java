package kb.KeepBudget.news.service;

import kb.KeepBudget.news.dto.req.GetNewsReqDto;
import kb.KeepBudget.news.dto.res.GetNewsResDto;
import kb.KeepBudget.news.dto.res.GetNewsSentimentResDto;
import kb.KeepBudget.news.dto.res.NewsKeywordResDto;
import kb.KeepBudget.news.entity.News;
import kb.KeepBudget.news.entity.NewsDistrict;
import kb.KeepBudget.news.entity.NewsKeyword;
import kb.KeepBudget.news.entity.NewsSentiment;
import kb.KeepBudget.news.repository.NewsDistrictRepository;
import kb.KeepBudget.news.repository.NewsKeywordRepository;
import kb.KeepBudget.news.repository.NewsRepository;
import kb.KeepBudget.news.repository.NewsSentimentRepository;
import kb.KeepBudget.news.type.Category;
import kb.KeepBudget.news.type.SentimentStatus;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.utils.dto.PageNation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsService {

    private final NewsDistrictRepository newsDistrictRepository;
    private final NewsRepository newsRepository;
    private final NewsSentimentRepository newsSentimentRepository;
    private final NewsKeywordRepository newsKeywordRepository;

    public GetNewsResDto getNewsList(User user, GetNewsReqDto reqDto) {

        int page = reqDto.getPage();
        int size = reqDto.getSize();

        Integer wishDistrictId = user.getWishDistrictId();
        Category category = reqDto.getCategory();
        List<NewsDistrict> newsDistricts = newsDistrictRepository.findAllByDistrictIdAndCategory(wishDistrictId, category);
        List<Long> newsIds = getNewsIdsByCategory(newsDistricts, reqDto.getStatus());
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

    private List<Long> getNewsIdsByCategory(List<NewsDistrict> newsDistricts, SentimentStatus status){
        List<Long> newsIds = newsDistricts.stream().map(NewsDistrict::getNewsId).toList();
        if(status == null){
            return newsIds;
        }
        List<NewsSentiment> newsSentiments = newsSentimentRepository.findAllByNewsIdIn(newsIds);
        return newsSentiments.stream().filter(newsSentiment -> matchesSentiment(newsSentiment, status))
                .map(NewsSentiment::getNewsId).toList();
    }

    private boolean matchesSentiment(NewsSentiment newsSentiment, SentimentStatus status){
        return switch (status) {
            case NEGATIVE -> newsSentiment.getNegative() > 0;
            case NEUTRAL -> newsSentiment.getNeutral() > 0;
            case POSITIVE -> newsSentiment.getPositive() > 0;
        };
    }

    public GetNewsSentimentResDto getNewsSentiment(Integer districtId) {
        List<NewsDistrict> newsDistricts = newsDistrictRepository.findAllByDistrictIdAndCategory(districtId, Category.PROPERTY);
        List<Long> newsIds = newsDistricts.stream().map(NewsDistrict::getNewsId).toList();
        List<NewsSentiment> newsSentiments = newsSentimentRepository.findAllByNewsIdIn(newsIds);
        Double negative = newsSentiments.stream().mapToDouble(NewsSentiment::getNegative).average().orElse(0.0);
        Double neutral = newsSentiments.stream().mapToDouble(NewsSentiment::getNeutral).average().orElse(0.0);
        Double positive = newsSentiments.stream().mapToDouble(NewsSentiment::getPositive).average().orElse(0.0);
        return GetNewsSentimentResDto.builder()
                .negative(negative)
                .neutral(neutral)
                .positive(positive)
                .build();
    }

    public List<NewsKeywordResDto> getNewsKeywords(Integer districtId) {
        List<NewsDistrict> newsDistricts = newsDistrictRepository.findAllByDistrictIdAndCategory(districtId, Category.ACCIDENT);
        List<Long> newsIds = newsDistricts.stream().map(NewsDistrict::getNewsId).toList();
        List<NewsKeyword> newsKeywords = newsKeywordRepository.findAllByNewsIdIn(newsIds);
        Map<String, Integer> keywordCountMap =  newsKeywords.stream().collect(Collectors.groupingBy(
                        NewsKeyword::getKeyword,
                        Collectors.summingInt(NewsKeyword::getCount)
                ));
        return keywordCountMap.entrySet().stream()
                .map(entry -> new NewsKeywordResDto(entry.getKey(), entry.getValue()))
                .sorted((dto1, dto2) -> dto2.getValue().compareTo(dto1.getValue()))
                .limit(40)
                .toList();
    }
}
