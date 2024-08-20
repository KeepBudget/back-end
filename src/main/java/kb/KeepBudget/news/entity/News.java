package kb.KeepBudget.news.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 127)
    private String title;

    @Column(length = 511)
    private String summary;

    @Column(length = 511)
    private String imgUrl;

    @Column(length = 511)
    private String originUrl;

    @Column(length = 15)
    private String press;

    private Timestamp date;

}
