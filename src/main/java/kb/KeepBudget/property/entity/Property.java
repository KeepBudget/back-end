package kb.KeepBudget.property.entity;

import jakarta.persistence.*;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer districtId;

    @Column(length = 63)
    private String address;

    @Column(length = 31)
    private String buildingName;

    private Integer floor;

    private Double price;

    private Double size;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
}
