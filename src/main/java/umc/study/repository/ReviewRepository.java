package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    // parameter가 다중처리 될 수 있도록 pageRequest에서 Pageable로 변경
    Page<Review> findMyAllByStore(Store store, Member member, Pageable pageable);

    Page<Review> findByMemberId(Long member_id, Pageable pageable);
}
