package kb.KeepBudget.news.repository;

import kb.KeepBudget.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByIdInOrderByDateDesc(List<Long> newsIds);

}
