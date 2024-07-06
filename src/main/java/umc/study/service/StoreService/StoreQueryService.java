package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Optional<Member> findMember(Long id);

}