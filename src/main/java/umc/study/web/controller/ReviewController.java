package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    private final StoreCommandService storeCommandService;

    @PostMapping(value = "/{storeId}/reviews", consumes = "multipart/form-data")
    @Operation(summary = "특정 가게의 리뷰 추가 API",description = "특정 가게에 리뷰를 추가하는 API, query String 으로 memberId를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "memberId", description = "멤버의 아이디, query string 입니다!"),
    })
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestPart(name = "request") @Valid StoreRequestDTO.ReviewDTO request,
                                                                            @RequestPart(name = "reviewPicture") MultipartFile reviewPicture,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request, reviewPicture);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @DeleteMapping(value = "/{reviewImageId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 이미지 삭제 API",description = "특정 가게에 리뷰 이미지를 삭제하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "reviewImageId", description = "리뷰 이미지의 아이디, path variable 입니다!"),
    })
    public ApiResponse<String> deleteReviewImage(@PathVariable(name = "reviewImageId") Long reviewImageId) {
        storeCommandService.deleteReviewImage(reviewImageId);
        return ApiResponse.onSuccess("리뷰 이미지 삭제 성공");
    }
}
