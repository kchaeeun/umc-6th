package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MemberRepository;
import umc.study.repository.StoreRepository;
import umc.study.validation.annotation.ExistStore;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistStore, List<Long>> {
    final private MemberRepository  memberRepository;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // 여기만 바꿔주면 됨
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(memberRepository::existsById);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
