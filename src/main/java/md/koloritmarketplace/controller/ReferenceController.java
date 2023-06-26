package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.CountryDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.TypeMeasureDto;
import md.koloritmarketplace.service.CountryService;
import md.koloritmarketplace.service.TypeMeasureService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "reference")
@RequestMapping("/api/v1/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final TypeMeasureService typeMeasureService;
    private final CountryService countryService;

    @PostMapping("/type-measure")
    @Operation( tags = {"Reference"})
    ResponseEntity<Page<TypeMeasureDto>> getTtpeMeasures(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(typeMeasureService.listTypeMeasureDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/save-type-measure")
    @Operation( tags = {"Reference"})
    ResponseEntity<TypeMeasureDto> addTypeMeasure(@RequestBody TypeMeasureDto typeMeasureDto) {
        return ResponseEntity.ok(typeMeasureService.addTypeMeasureDto(typeMeasureDto));
    }

    @PutMapping("/save-type-measure")
    @Operation( tags = {"Reference"})
    ResponseEntity<TypeMeasureDto> updateTypeMeasure(@RequestBody TypeMeasureDto typeMeasureDto) {
        return ResponseEntity.ok(typeMeasureService.updateTypeMeasureDto(typeMeasureDto));
    }

    @GetMapping("/type-measure/{typeMeasureId}")
    @Operation( tags = {"Reference"})
    public ResponseEntity<TypeMeasureDto> getTypeMeasure(@PathVariable("typeMeasureId") Long typeMeasureId) {
        return ResponseEntity.ok(typeMeasureService.getTypeMeasureDto(typeMeasureId));
    }


    @GetMapping("/country")
    @Operation( tags = {"Reference"})
    ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.listCountryDto());
    }

    @PostMapping("/save-country")
    @Operation( tags = {"Reference"})
    ResponseEntity<CountryDto> addCountryDto(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.addCountryDto(countryDto));
    }

    @PutMapping("/save-country")
    @Operation( tags = {"Reference"})
    ResponseEntity<CountryDto> updateCountryDto(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountryDto(countryDto));
    }
}
