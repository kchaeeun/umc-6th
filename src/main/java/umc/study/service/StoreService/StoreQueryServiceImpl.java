package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }
    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Review> getMyReviewList(Long StoreId, Long MemberId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        Member member = memberRepository.findById(MemberId).get();
        Page<Review> StoreMyPage = reviewRepository.findMyAllByStore(store, member, PageRequest.of(page,10));

        return StoreMyPage;
    }
}
