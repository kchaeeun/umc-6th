package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import umc.study.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Uuid extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;
}
