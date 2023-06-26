package md.koloritmarketplace.model.dto.brand;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.ImageDto;
import md.koloritmarketplace.model.dto.LanguageEmbDto;
import md.koloritmarketplace.model.dto.LinkCountryBrandDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class BrandViewDto {
    private Long brandId;
    private String brandName;
    private List<ImageDto> images;
    private Long counts;
}