package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.CategoryDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.service.AppService;
import md.koloritmarketplace.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "category")
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation( tags = {"Category"})
    ResponseEntity<Page<CategoryDto>> getCatigories(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(categoryService.listCategoryDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/save-category")
    @Operation( tags = {"Category"})
    ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.addCategoryDto(categoryDto));
    }

    @PutMapping("/save-category")
    @Operation( tags = {"Category"})
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategoryDto(categoryDto));
    }

    @GetMapping("/{categoryId}")
    @Operation( tags = {"Category"})
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryDto(categoryId));
    }
}
