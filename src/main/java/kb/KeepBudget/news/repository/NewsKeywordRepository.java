package kb.KeepBudget.news.repository;

import kb.KeepBudget.news.entity.NewsKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsKeywordRepository extends JpaRepository<NewsKeyword, Long> {

    List<NewsKeyword> findAllByNewsIdIn(List<Long> newsIds);

}
