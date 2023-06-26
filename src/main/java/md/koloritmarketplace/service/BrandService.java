package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.brand.BrandInputSaveDto;
import md.koloritmarketplace.model.dto.brand.BrandViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BrandService {
    BrandDto getBrandDto(Long brandId);

    BrandInputSaveDto getBrandForEditingDto(Long brandId);

    BrandInputSaveDto saveBrandView(BrandInputSaveDto brandViewDto);

    BrandInputSaveDto updateBrandView(BrandInputSaveDto brandViewDto);

    BrandDto addBrandDto(BrandDto brandDto);

    BrandDto updateBrandDto(BrandDto brandDto);

    Page<BrandDto> listBrandDto(Pageable pageable, Map<String, String> params);

    Page<BrandViewDto> listBrandViewDto(PageParamDto pageParamDto);
}
