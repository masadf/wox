package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.brand.BrandInputSaveDto;
import md.koloritmarketplace.model.dto.brand.BrandViewDto;
import md.koloritmarketplace.model.entity.BrandEntity;
import md.koloritmarketplace.model.entity.ImageEntity;
import md.koloritmarketplace.model.entity.LinkCountryBrandEntity;
import md.koloritmarketplace.model.entity.view.BrandViewEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.BrandRepository;
import md.koloritmarketplace.repository.ImageRepository;
import md.koloritmarketplace.repository.view.BrandViewRepository;
import md.koloritmarketplace.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

import static md.koloritmarketplace.repository.specification.BrandSpec.likeName;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandViewRepository brandViewRepository;
    private final ImageRepository imageRepository;
    private final AppMapper appMapper;

    @Override
    public BrandDto getBrandDto(Long brandId) {
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);
        return appMapper.map(brandEntity.get());
    }

    @Override
    public BrandInputSaveDto getBrandForEditingDto(Long brandId) {
        BrandEntity brandEntity = brandRepository.findById(brandId).get();
        BrandInputSaveDto brandInputSaveDto = appMapper.mapToBrandInput(brandEntity);
        return brandInputSaveDto;
    }

    @Override
    @Transactional
    public BrandInputSaveDto saveBrandView(BrandInputSaveDto brandViewDto) {
        BrandEntity brandEntity = appMapper.map(brandViewDto);
        brandEntity = saveBrand(brandEntity);
        return appMapper.mapToBrandInput(brandEntity);
    }

    @Override
    public BrandInputSaveDto updateBrandView(BrandInputSaveDto brandViewDto) {
        BrandEntity brandEntity = appMapper.map(brandViewDto);
        brandEntity = saveBrand(brandEntity);
        return appMapper.mapToBrandInput(brandEntity);
    }

    @Override
    @Transactional
    public BrandDto addBrandDto(BrandDto brandDto) {
        BrandEntity brandEntity = appMapper.map(brandDto);

        List<ImageEntity> imageEntities = brandEntity.getImages();
        Set<LinkCountryBrandEntity> countriesList = brandEntity.getCountries();
        brandEntity.setType(ObjectTypeEnum.BRAND);

        brandEntity.setImages(List.of());
        brandEntity.setCountries(Set.of());

        brandEntity = brandRepository.save(brandEntity);
        if (!imageEntities.isEmpty()) {
            Long id = brandEntity.getId();
            imageEntities.stream().forEach(r -> {
                r.setAppId(id);
            });
            brandEntity.setImages(imageEntities);

            brandEntity = brandRepository.save(brandEntity);
        }

        if (!countriesList.isEmpty()) {
            Long id = brandEntity.getId();
            countriesList.stream().forEach(r -> {
                r.setBrandId(id);
            });
            brandEntity.setCountries(countriesList);
            brandEntity = brandRepository.save(brandEntity);
        }

        return appMapper.map(brandEntity);
    }

    @Override
    public BrandDto updateBrandDto(BrandDto brandDto) {
        BrandEntity brandEntity = appMapper.map(brandDto);
        brandEntity.setType(ObjectTypeEnum.BRAND);
        brandEntity = brandRepository.save(brandEntity);
        return appMapper.map(brandEntity);
    }

    @Override
    public Page<BrandDto> listBrandDto(Pageable pageable, Map<String, String> params) {
        Page<BrandEntity> lst = brandRepository.findAll(pageable);
        Page<BrandDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToBrandDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }

    @Override
    @Transactional
    public Page<BrandViewDto> listBrandViewDto(PageParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();

        if (params == null) {
            params = new HashMap<>();
        }
        Specification<BrandViewEntity> specification = Specification
                .where(StringUtils.hasText(params.get("brandName")) ? likeName(params.get("brandName")) : null);

        Page<BrandViewEntity> brandViewEntities = brandViewRepository.findAll(specification, pageParamDto.getPageRequest());
        List<BrandViewDto> list = appMapper.mapToListToBrandViewDto(brandViewEntities.getContent());
        list.stream().forEach(r -> {
            r.setImages(appMapper.mapToListToImageDto(imageRepository.findByAppId(r.getBrandId())));
        });

        return new PageImpl<>(list, brandViewEntities.getPageable(), brandViewEntities.getTotalElements());

    }

    private BrandEntity saveBrand(BrandEntity brandEntity) {
        if (brandEntity.getType() == null) {
            brandEntity.setType(ObjectTypeEnum.BRAND);
        }

        if (brandEntity.getBrandId() != null) {
            Long appId = brandEntity.getId();
            brandEntity.getImages().stream().forEach(r -> r.setAppId(appId));

        }

        else if (brandEntity.getImages() != null || brandEntity.getImages().size() != 0) {
            List<ImageEntity> images = brandEntity.getImages();
            brandEntity.setImages(List.of());
            brandEntity = brandRepository.save(brandEntity);
            Long brandId = brandEntity.getId();
            images.stream().forEach(image -> image.setAppId(brandId));
            brandEntity.setImages(images);

        }

        return brandRepository.save(brandEntity);
    }
}
