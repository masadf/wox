package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.brand.BrandInputSaveDto;
import md.koloritmarketplace.model.dto.brand.BrandViewDto;
import md.koloritmarketplace.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "brand")
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
private final BrandService brandService;
    @PostMapping("/save-brand")
    @Operation(description = "Add new brand", tags = {"Brand"})
    ResponseEntity<BrandDto> addBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.addBrandDto(brandDto));
    }

    @PutMapping("/save-brand")
    @Operation(description = "Update an existed brand", tags = {"Brand"})
    ResponseEntity<BrandDto> updateBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.updateBrandDto(brandDto));
    }

    @PostMapping()
    @Operation(description = "Get brand by params", tags = {"Brand"})
    public ResponseEntity<Page<BrandDto>> getBrands(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(brandService.listBrandDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/list-view")
    @Operation(description = "Get brand view (where there  is counts) by params", tags = {"Brand"})
    public ResponseEntity<Page<BrandViewDto>> getBrandsView(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(brandService.listBrandViewDto(pageParamDto));
    }
    @PostMapping("/save-brand-view")
    @Operation(description = "Save brand view (where there  is counts) by params", tags = {"Brand"})
    public ResponseEntity<BrandInputSaveDto> saveBrandView(@RequestBody BrandInputSaveDto brandViewDto) {
        return ResponseEntity.ok(brandService.saveBrandView(brandViewDto));
    }
    @PutMapping("/save-brand-view")
    @Operation(description = "Update brand view (where there  is counts) by params", tags = {"Brand"})
    public ResponseEntity<BrandInputSaveDto> updateBrandView(@RequestBody BrandInputSaveDto brandViewDto) {
        return ResponseEntity.ok(brandService.updateBrandView(brandViewDto));
    }
    @GetMapping("/{brandId}")
    @Operation(description = "Get an brand by id", tags = {"Brand"})
    public ResponseEntity<BrandDto> getBrand(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(brandService.getBrandDto(brandId));
    }

    @GetMapping("/get/{brandId}")
    @Operation(description = "Get an brand by id", tags = {"Brand"})
    public ResponseEntity<BrandInputSaveDto> getBrandForEditing(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(brandService.getBrandForEditingDto(brandId));
    }
}
