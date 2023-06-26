package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.CategoryDto;
import md.koloritmarketplace.model.entity.CategoryEntity;
import md.koloritmarketplace.model.entity.ImageEntity;
import md.koloritmarketplace.model.enums.ObjectStatus;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.CategoryRepository;
import md.koloritmarketplace.repository.ImageRepository;
import md.koloritmarketplace.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.CategorySpec.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final AppMapper appMapper;

    @Override
    public CategoryDto getCategoryDto(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
        return appMapper.map(categoryEntity);
    }

    @Override
    @Transactional
    public CategoryDto addCategoryDto(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = appMapper.map(categoryDto);
        categoryEntity.setType(ObjectTypeEnum.CATEGORY);
        List<ImageEntity> images = categoryEntity.getImages();
        categoryEntity.setImages(List.of());
        categoryEntity = categoryRepository.save(categoryEntity);
        categoryEntity=updateImagesByAppId(categoryEntity,images);

        return appMapper.map(categoryEntity);
    }

    @Override
    @Transactional
    public CategoryDto updateCategoryDto(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = appMapper.map(categoryDto);
        categoryEntity.setType(ObjectTypeEnum.CATEGORY);
//        List<ImageEntity> images = categoryEntity.getImages();
//        categoryEntity.setImages(List.of());
        categoryEntity = categoryRepository.save(categoryEntity);
//        if(images.size()!=0)
//        {
//            Long id=categoryEntity.getId();
//            images.stream().forEach(image->image.setAppId(id));
//            categoryEntity.getImages().addAll(images);
//           categoryEntity= categoryRepository.save(categoryEntity);
//
//        }

        return appMapper.map(categoryEntity);
    }

    @Override
    public Page<CategoryDto> listCategoryDto(Pageable pageable, Map<String, String> params) {

        Specification<CategoryEntity> specification = Specification
                .where(StringUtils.hasText(params.get("status")) ? equalStatus(ObjectStatus.valueOf(params.get("status"))) : null)
                .and(StringUtils.hasText(params.get("parentId")) ? equalParentId(Long.valueOf(params.get("parentId"))) : equalParentIdIsNull());

        Page<CategoryEntity> lst = categoryRepository.findAll(specification, pageable);

        Page<CategoryDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToCategoryDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }

    private CategoryEntity  updateImagesByAppId( CategoryEntity categoryEntity, List<ImageEntity> images) {
        images.stream().forEach(image -> image.setAppId(categoryEntity.getId()));
        categoryEntity.setImages(images);
        return   categoryRepository.save(categoryEntity);
    }
}
