package kb.KeepBudget.property.service;

import kb.KeepBudget.property.entity.Property;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    private List<Property> properties;

    @BeforeEach
    void setUp(){
        properties = new ArrayList<>();
        properties.add(new Property(1L, 101, "Address 1", "Building A", 3, 500000.0, 80.0, PropertyType.APARTMENT, TradeType.LEASE));
        properties.add(new Property(2L, 102, "Address 2", "Building B", 5, 300000.0, 70.0, PropertyType.APARTMENT, TradeType.LEASE));
        properties.add(new Property(3L, 103, "Address 3", "Building C", 2, 700000.0, 90.0, PropertyType.APARTMENT, TradeType.LEASE));
        properties.add(new Property(4L, 104, "Address 4", "Building D", 7, 400000.0, 60.0, PropertyType.APARTMENT, TradeType.LEASE));
    }

    @Test
    void testSortPropertiesByCosineSimilarity() {
        Integer basePrice = 600000;
        Integer baseSize = 85;

        propertyService.sortPropertiesByCosineSimilarity(properties, basePrice, baseSize);

        assertEquals(3L, properties.get(0).getId());
        assertEquals(1L, properties.get(1).getId());
        assertEquals(2L, properties.get(2).getId());
        assertEquals(4L, properties.get(3).getId());
    }
}