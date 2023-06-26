package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.PromoCodeDto;
import md.koloritmarketplace.model.dto.input.PromoCodeInput;
import md.koloritmarketplace.model.entity.PromoCodeEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.PromoCodeRepository;
import md.koloritmarketplace.service.PromoCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.PromoCodeSpec.*;
import static md.koloritmarketplace.util.CommonUtil.changeDateTimeFormatToL;

@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService {

    private final AppMapper appMapper;
    private final PromoCodeRepository promoCodeRepository;

    @Override
    public PromoCodeDto getaddPromoCodeDto(Long cardId) {
        return appMapper.map(promoCodeRepository.findById(cardId).get());
    }

    @Override
    public List<PromoCodeDto> addPromoCodeDto(List<PromoCodeInput> promoCodeInputs) {
        return null;
    }

    @Override
    public PromoCodeDto updatePromoCodeDto(PromoCodeInput promoCodeInput) {
        PromoCodeEntity promoCodeEntity = appMapper.map(promoCodeInput);
        promoCodeEntity = promoCodeRepository.save(promoCodeEntity);
        return appMapper.map(promoCodeEntity);
    }

    @Override
    public Page<PromoCodeDto> listaddPromoCodeDto(Pageable pageable, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        String promoCode = params.get("promoCode");
        String startDate = params.get("startDate");
        String endDate = params.get("endDate");
        String percent = params.get("percent");

        Specification<PromoCodeEntity> specification = Specification
                .where(StringUtils.hasText(percent) ? percentEqual(Long.valueOf(percent)) : null)
                .and(StringUtils.hasText(promoCode) ? likePromoCode(promoCode) : null)
                .and(StringUtils.hasText(startDate) ? greaterThanStartDate(changeDateTimeFormatToL(startDate)) : null)
                .and(StringUtils.hasText(endDate) ? lessThanEndDate(changeDateTimeFormatToL(endDate)) : null);

        Page<PromoCodeDto> lst = promoCodeRepository.findAll(specification, pageable).map(appMapper::map);

        return lst;
    }

    @Override
    public PromoCodeDto addPromoCodeDto(PromoCodeInput promoCodeInput) {
        PromoCodeEntity promoCodeEntity = appMapper.map(promoCodeInput);
        promoCodeEntity.setType(ObjectTypeEnum.PROMO_CODE);
        promoCodeEntity = promoCodeRepository.save(promoCodeEntity);

        return appMapper.map(promoCodeEntity);
    }
}
