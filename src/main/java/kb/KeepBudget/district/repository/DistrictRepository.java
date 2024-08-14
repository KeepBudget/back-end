package kb.KeepBudget.district.repository;

import kb.KeepBudget.district.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findAllByOrderByNameAsc();

}
