package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.CardDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.PromoCodeDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import md.koloritmarketplace.model.dto.input.PromoCodeInput;
import md.koloritmarketplace.service.PromoCodeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "promo")
@RequestMapping("/api/v1/promo")
@RequiredArgsConstructor
public class PromoCodeController {

    private final PromoCodeService promoCodeService;


    @PostMapping
    @Operation( tags = {"Promo code"})
    ResponseEntity<PromoCodeDto> addPromoCodeDto(@RequestBody PromoCodeInput promoCodeInput) {
        return ResponseEntity.ok(promoCodeService.addPromoCodeDto(promoCodeInput));
    }

    @PutMapping
    @Operation( tags = {"Promo code"})
    ResponseEntity<PromoCodeDto> updatePromoCodeDto(@RequestBody PromoCodeInput promoCodeInput) {
        return ResponseEntity.ok(promoCodeService.updatePromoCodeDto(promoCodeInput));
    }
    @GetMapping("/{id}")
    @Operation( tags = {"Promo code"})
    ResponseEntity<PromoCodeDto> getPromoCode(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promoCodeService.getaddPromoCodeDto(id));
    }

    @PostMapping("/all")
    @Operation( tags = {"Promo code"})
    ResponseEntity<Page<PromoCodeDto>> listaddPromoCodeDto(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(promoCodeService.listaddPromoCodeDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

}
