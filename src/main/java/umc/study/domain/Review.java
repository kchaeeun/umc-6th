package umc.study.domain;

import lombok.*;
import umc.study.domain.common.BaseEntity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    private String body;

    // setMember 메서드 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // setStore 메서드 추가
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewMember> reviewMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewStore> reviewStoreList = new ArrayList<>();

}