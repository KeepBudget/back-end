package kb.KeepBudget.news.repository;

import kb.KeepBudget.news.entity.NewsDistrict;
import kb.KeepBudget.news.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsDistrictRepository extends JpaRepository<NewsDistrict, Long> {

    List<NewsDistrict> findAllByDistrictIdAndCategory(Integer districtId, Category category);

}
