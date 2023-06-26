package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.ActionDto;
import md.koloritmarketplace.model.dto.*;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.option.OptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Page<AppDto> getAppList(Pageable pageable, Map<String, String> params);

    BrandDto getBrandDto(Long brandId);

    BrandDto addBrandDto(BrandDto brandDto);

    BrandDto updateBrandDto(BrandDto brandDto);

    Page<BrandDto> listBrandDto(Pageable pageable, Map<String, String> params);


    BucketDto getBucketDto(Long bucketId);

    BucketDto addBucketDto(BucketDto bucketDto);

    BucketDto updateBucketDto(BucketDto bucketDto);

    Page<BucketDto> listBucketDto(Pageable pageable, Map<String, String> params);


    CategoryDto getCategoryDto(Long categoryId);

    CategoryDto addCategoryDto(CategoryDto categoryDto);

    CategoryDto updateCategoryDto(CategoryDto categoryDto);

    Page<CategoryDto> listCategoryDto(Pageable pageable, Map<String, String> params);


    ItemDto getItemDto(Long itemId);

    ItemDto addItemDto(ItemDto categoryDto);

    ItemDto updateItemDto(ItemDto categoryDto);

    Page<ItemDto> listItemDto(Pageable pageable, Map<String, String> params);


    OptionDto getOptionDto(Long optionId);

    OptionDto addOptionDto(OptionDto optionDto);

    OptionDto updateOptionDto(OptionDto optionDto);

    Page<OptionDto> listOptionDto(Pageable pageable, Map<String, String> params);


    TypeMeasureDto getTypeMeasureDto(Long TypeMeasureId);

    TypeMeasureDto addTypeMeasureDto(TypeMeasureDto typeMeasureDto);

    TypeMeasureDto updateTypeMeasureDto(TypeMeasureDto typeMeasureDto);

    Page<TypeMeasureDto> listTypeMeasureDto(Pageable pageable, Map<String, String> params);

    ActionDto getActionDto(Long actionId);

    ActionDto addActionDto(ActionDto actionDto);

    ActionDto updateActionDto(ActionDto actionDto);

    Page<ActionDto> listActionDto(Pageable pageable, Map<String, String> params);

    NewsDto getNewsDto(Long newsId);

    NewsDto addNewsDto(NewsDto newsDto);

    NewsDto updateNewsDto(NewsDto newsDto);

    List<NewsDto> listNewsDto();


    CountryDto addCountryDto(CountryDto countryDto);

    CountryDto updateCountryDto(CountryDto countryDto);

    List<CountryDto> listCountryDto();
}
