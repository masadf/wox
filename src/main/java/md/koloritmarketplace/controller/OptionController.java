package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.option.OptionDto;
import md.koloritmarketplace.model.dto.option.OptionInputDto;
import md.koloritmarketplace.service.OptionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "option")
@RequestMapping("/api/v1/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    @Operation( tags = {"Option"})
    ResponseEntity<Page<OptionDto>> getOptions(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(optionService.listOptionDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/save-option")
    @Operation( tags = {"Option"})
    ResponseEntity<OptionInputDto> addOption(@RequestBody OptionInputDto optionDto) {
        return ResponseEntity.ok(optionService.addOptionDto(optionDto));
    }

    @PutMapping("/save-option")
    @Operation( tags = {"Option"})
    ResponseEntity<OptionInputDto> updateOption(@RequestBody OptionInputDto optionDto) {
        return ResponseEntity.ok(optionService.updateOptionDto(optionDto));
    }

    @GetMapping("/{optionId}")
    @Operation( tags = {"Option"})
    public ResponseEntity<OptionDto> getOption(@PathVariable("optionId") Long optionId) {
        return ResponseEntity.ok(optionService.getOptionDto(optionId));
    }
}
