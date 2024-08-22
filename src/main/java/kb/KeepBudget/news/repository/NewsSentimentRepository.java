package kb.KeepBudget.news.repository;

import kb.KeepBudget.news.entity.NewsSentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsSentimentRepository extends JpaRepository<NewsSentiment, Long> {

    List<NewsSentiment> findAllByNewsIdIn(List<Long> newsIds);

}
