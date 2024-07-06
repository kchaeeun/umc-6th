package umc.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.study.aws.s3.AmazonS3Manager;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Uuid;
import umc.study.repository.*;
import umc.study.web.dto.StoreRequestDTO;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request, MultipartFile reviewPicture) {

        Review review = StoreConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());


        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl,review));
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReviewImage(Long reviewImageId) {
        Optional<ReviewImage> optionalReviewImage = reviewImageRepository.findById(reviewImageId);
        if (optionalReviewImage.isPresent()) {
            ReviewImage reviewImage = optionalReviewImage.get();

            s3Manager.deleteFile(reviewImage.getImageUrl());

            reviewImageRepository.delete(reviewImage);
        } else {
            throw new IllegalArgumentException("Review Image with id " + reviewImageId + "not found");
        }
    }

}