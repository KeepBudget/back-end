package kb.KeepBudget.news.entity;

import jakarta.persistence.*;
import kb.KeepBudget.news.type.Category;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "news_districts")
public class NewsDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer districtId;

    private Long newsId;

    @Enumerated(EnumType.STRING)
    private Category category;
}
