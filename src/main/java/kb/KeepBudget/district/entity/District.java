package kb.KeepBudget.district.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kb.KeepBudget.district.dto.DistrictResDto;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "districts")
public class District {

    @Id
    private Integer id;

    @Column(length = 7)
    private String name;

}
