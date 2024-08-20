package kb.KeepBudget.user.entity;

import jakarta.persistence.*;
import kb.KeepBudget.property.type.PropertyType;
import kb.KeepBudget.property.type.TradeType;
import kb.KeepBudget.user.dto.res.UserResDto;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 15)
    private String nickname;

    private Integer wishDistrictId;

    @Enumerated(EnumType.STRING)
    private PropertyType wishPropertyType;

    @Enumerated(EnumType.STRING)
    private TradeType wishTradeType;

    private Integer wishPropertyPrice;

    private Integer wishPropertySize;

    public UserResDto toUserResDto(String district){
        return UserResDto.builder()
                .nickname(nickname)
                .wishDistrict(district)
                .wishPropertyType(wishPropertyType.getName())
                .wishTradeType(wishTradeType.getName())
                .wishPropertyPrice(wishPropertyPrice)
                .wishPropertySize(wishPropertySize)
                .build();
    }

}
