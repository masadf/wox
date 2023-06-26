//package md.koloritmarketplace.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import md.koloritmarketplace.model.dto.*;
//import md.koloritmarketplace.model.dto.bucket.BucketDto;
//import md.koloritmarketplace.model.dto.item.ItemDto;
//import md.koloritmarketplace.model.dto.option.OptionDto;
//import md.koloritmarketplace.service.*;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class AdminServiceImpl implements AdminService {
//    private final AppService appService;
//    private final BrandService brandService;
//    private final BucketService bucketService;
//    private final ItemService itemService;
//    private final OptionService optionService;
//    private final TypeMeasureService typeMeasureService;
//    private final CategoryService categoryService;
//    private final CountryService countryService;
//    private final ActionService actionService;
//    private final NewsService newsService;
//
//
//    @Override
//    public Page<AppDto> getAppList(Pageable pageable, Map<String, String> params) {
//        return appService.findAllApps(pageable, params);
//    }
//
//    @Override
//    public BrandDto getBrandDto(Long brandId) {
//        return brandService.getBrandDto(brandId);
//    }
//
//    @Override
//    public BrandDto addBrandDto(BrandDto brandDto) {
//        return brandService.addBrandDto(brandDto);
//    }
//
//    @Override
//    public BrandDto updateBrandDto(BrandDto brandDto) {
//        return brandService.updateBrandDto(brandDto);
//    }
//
//    @Override
//    public Page<BrandDto> listBrandDto(Pageable pageable, Map<String, String> params) {
//        return brandService.listBrandDto(pageable, params);
//    }
//
//    @Override
//    public BucketDto getBucketDto(Long bucketId) {
//        return bucketService.getBucketDto(bucketId);
//    }
//
//    @Override
//    public BucketDto addBucketDto(BucketDto bucketDto) {
//        return bucketService.addBucketDto(bucketDto);
//    }
//
//    @Override
//    public BucketDto updateBucketDto(BucketDto bucketDto) {
//        return bucketService.updateBucketDto(bucketDto);
//    }
//
//    @Override
//    public Page<BucketDto> listBucketDto(Pageable pageable, Map<String, String> params) {
//        return bucketService.listBucketDto(pageable, params);
//    }
//
//    @Override
//    public CategoryDto getCategoryDto(Long categoryId) {
//        return categoryService.getCategoryDto(categoryId);
//    }
//
//    @Override
//    public CategoryDto addCategoryDto(CategoryDto categoryDto) {
//        return categoryService.addCategoryDto(categoryDto);
//    }
//
//    @Override
//    public CategoryDto updateCategoryDto(CategoryDto categoryDto) {
//        return categoryService.updateCategoryDto(categoryDto);
//    }
//
//    @Override
//    public Page<CategoryDto> listCategoryDto(Pageable pageable, Map<String, String> params) {
//        return categoryService.listCategoryDto(pageable, params);
//    }
//
//    @Override
//    public ItemDto getItemDto(Long itemId) {
//        return itemService.getItemDto(itemId);
//    }
//
//    @Override
//    public ItemDto addItemDto(ItemDto itemDto) {
//        return itemService.addItemDto(itemDto);
//    }
//
//    @Override
//    public ItemDto updateItemDto(ItemDto itemDto) {
//        return itemService.updateItemDto(itemDto);
//    }
//
//    @Override
//    public Page<ItemDto> listItemDto(Pageable pageable, Map<String, String> params) {
//        return itemService.listItemDto(pageable, params);
//    }
//
//    @Override
//    public OptionDto getOptionDto(Long optionId) {
//        return optionService.getOptionDto(optionId);
//    }
//
//    @Override
//    public OptionDto addOptionDto(OptionDto optionDto) {
//        return optionService.addOptionDto(optionDto);
//    }
//
//    @Override
//    public OptionDto updateOptionDto(OptionDto optionDto) {
//        return optionService.updateOptionDto(optionDto);
//    }
//
//    @Override
//    public Page<OptionDto> listOptionDto(Pageable pageable, Map<String, String> params) {
//        return optionService.listOptionDto(pageable, params);
//    }
//
//    @Override
//    public TypeMeasureDto getTypeMeasureDto(Long typeMeasureId) {
//        return typeMeasureService.getTypeMeasureDto(typeMeasureId);
//    }
//
//    @Override
//    public TypeMeasureDto addTypeMeasureDto(TypeMeasureDto typeMeasureDto) {
//        return typeMeasureService.addTypeMeasureDto(typeMeasureDto);
//    }
//
//    @Override
//    public TypeMeasureDto updateTypeMeasureDto(TypeMeasureDto typeMeasureDto) {
//        return typeMeasureService.updateTypeMeasureDto(typeMeasureDto);
//    }
//
//    @Override
//    public Page<TypeMeasureDto> listTypeMeasureDto(Pageable pageable, Map<String, String> params) {
//        return typeMeasureService.listTypeMeasureDto(pageable, params);
//    }
//
//    @Override
//    public ActionDto getActionDto(Long actionId) {
//        return actionService.getActionDto(actionId);
//    }
//
//    @Override
//    public ActionDto addActionDto(ActionDto actionDto) {
//        return actionService.addActionDto(actionDto);
//    }
//
//    @Override
//    public ActionDto updateActionDto(ActionDto actionDto) {
//        return actionService.updateActionDto(actionDto);
//    }
//
//    @Override
//    public Page<ActionDto> listActionDto(Pageable pageable, Map<String, String> params) {
//        return actionService.listActionDto(pageable, params);
//    }
//
//    @Override
//    public NewsDto getNewsDto(Long newsId) {
//        return newsService.getItemDto(newsId);
//    }
//
//    @Override
//    public NewsDto addNewsDto(NewsDto newsDto) {
//        return newsService.addItemDto(newsDto);
//    }
//
//    @Override
//    public NewsDto updateNewsDto(NewsDto newsDto) {
//        return newsService.updateItemDto(newsDto);
//    }
//
//    @Override
//    public List<NewsDto> listNewsDto() {
//        return newsService.listItemDto();
//    }
//
//    @Override
//    public CountryDto addCountryDto(CountryDto countryDto) {
//        return countryService.addCountryDto(countryDto);
//    }
//
//    @Override
//    public CountryDto updateCountryDto(CountryDto countryDto) {
//        return countryService.updateCountryDto(countryDto);
//    }
//
//    @Override
//    public List<CountryDto> listCountryDto() {
//        return countryService.listCountryDto();
//    }
//}
