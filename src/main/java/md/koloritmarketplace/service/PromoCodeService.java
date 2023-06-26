package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.CardDto;
import md.koloritmarketplace.model.dto.PromoCodeDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import md.koloritmarketplace.model.dto.input.PromoCodeInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PromoCodeService {

    PromoCodeDto getaddPromoCodeDto(Long cardId);

    PromoCodeDto addPromoCodeDto(PromoCodeInput promoCodeInput);

    List<PromoCodeDto> addPromoCodeDto(List<PromoCodeInput> promoCodeInputs);

    PromoCodeDto updatePromoCodeDto(PromoCodeInput promoCodeInput);

    Page<PromoCodeDto> listaddPromoCodeDto(Pageable pageable, Map<String, String> params);
}
