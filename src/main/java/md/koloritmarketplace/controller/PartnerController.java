package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.PartnerDto;
import md.koloritmarketplace.service.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "partner")
@RequestMapping("/api/v1/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping("/all")
    @Operation( tags = {"Partner"})
    public ResponseEntity<Page<PartnerDto>> list(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(partnerService.listPartnerDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }
    @PostMapping
    @Operation( tags = {"Partner"})
    public ResponseEntity<PartnerDto> add(@RequestBody PartnerDto partnerDto) {
        return ResponseEntity.ok(partnerService.addPartnerDto(partnerDto));
    }
    @PutMapping
    @Operation( tags = {"Partner"})
    public ResponseEntity<PartnerDto> update(@RequestBody PartnerDto partnerDto) {
        return ResponseEntity.ok(partnerService.updatePartnerDto(partnerDto));
    }
    @GetMapping("/{partnerId}")
    @Operation( tags = {"Partner"})
    public ResponseEntity<PartnerDto> update(@PathVariable("partnerId") Long partnerDto) {
        return ResponseEntity.ok(partnerService.getPartnerDto(partnerDto));
    }
}
