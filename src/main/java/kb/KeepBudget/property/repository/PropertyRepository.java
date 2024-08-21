package kb.KeepBudget.property.repository;

import kb.KeepBudget.property.entity.Property;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByDistrictIdAndTypeAndTradeType(Integer districtId, PropertyType type, TradeType tradeType);

}
