package kb.KeepBudget.property.service;

import kb.KeepBudget.property.dto.req.GetPropertiesReqDto;
import kb.KeepBudget.property.dto.res.GetPropertiesResDto;
import kb.KeepBudget.property.dto.res.PropertyResDto;
import kb.KeepBudget.property.entity.Property;
import kb.KeepBudget.property.repository.PropertyRepository;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import kb.KeepBudget.user.entity.User;
import kb.KeepBudget.utils.dto.PageNation;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public GetPropertiesResDto getProperties(User user, GetPropertiesReqDto reqDto) {

        int page = reqDto.getPage();
        int size = reqDto.getSize();

        Integer wishPrice = user.getWishPropertyPrice();
        Integer wishSize = user.getWishPropertySize();

        Integer baseDistrictId = user.getWishDistrictId();
        PropertyType basePropertyType = user.getWishPropertyType();
        TradeType baseTradeType = user.getWishTradeType();
        List<Property> properties = propertyRepository.findAllByDistrictIdAndTypeAndTradeType(baseDistrictId, basePropertyType, baseTradeType);

        sortPropertiesByCosineSimilarity(properties, wishPrice, wishSize);

        List<PropertyResDto> propertyResDtos = properties.stream()
                .skip((long) (page - 1) * size)
                .limit(size)
                .map(Property::toPropertyResDto)
                .toList();

        PageNation pageNation = PageNation.builder()
                .page(page)
                .totalCount((long) properties.size())
                .build();

        return GetPropertiesResDto.builder()
                .pageNation(pageNation)
                .properties(propertyResDtos)
                .build();
    }

    public void sortPropertiesByCosineSimilarity(List<Property> properties, Integer wishPrice, Integer wishSize){
        Double minPrice = properties.stream().mapToDouble(Property::getPrice).min().orElse(1.0);
        Double maxPrice = properties.stream().mapToDouble(Property::getPrice).max().orElse(2.0);
        Double minSize = properties.stream().mapToDouble(Property::getSize).min().orElse(1.0);
        Double maxSize = properties.stream().mapToDouble(Property::getSize).max().orElse(2.0);

        Double scaledWishPrice = (wishPrice - minPrice) / (maxPrice - minPrice);
        Double scaledWishSize = (wishSize - minSize) / (maxSize - minSize);

        properties.sort(Comparator.comparingDouble(property ->
            -cosineSimilarity(
                scaledWishPrice,
                scaledWishSize,
                (property.getPrice() - minPrice) / (maxPrice - minPrice),
                (property.getSize() - minSize) / (maxSize - minSize)
            )
        ));
    }

    private Double cosineSimilarity(Double x1, Double y1, Double x2, Double y2){
        Double dotProduct = x1 * x2 + y1 * y2;
        Double norm1 = Math.sqrt(x1 * x1 + y1 * y1);
        Double norm2 = Math.sqrt(x2 * x2 + y2 * y2);
        return dotProduct / (norm1 * norm2);
    }

}
