package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CategoryService {
    CategoryDto getCategoryDto(Long categoryId);

    CategoryDto addCategoryDto(CategoryDto categoryDto);

    CategoryDto updateCategoryDto(CategoryDto categoryDto);

    Page<CategoryDto> listCategoryDto(Pageable pageable, Map<String, String> params);
}
