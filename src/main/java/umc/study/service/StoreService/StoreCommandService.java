package umc.study.service.StoreService;

import org.springframework.web.multipart.MultipartFile;
import umc.study.domain.Review;
import umc.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
}
