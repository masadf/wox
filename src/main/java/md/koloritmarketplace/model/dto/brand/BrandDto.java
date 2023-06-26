package md.koloritmarketplace.model.dto.brand;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.LanguageEmbDto;
import md.koloritmarketplace.model.dto.LinkCountryBrandDto;

import java.util.HashSet;
import java.util.Set;

@Data
public class BrandDto extends AppDto {
    private Long brandId;
    private String brandName;
    Set<LinkCountryBrandDto> countries = new HashSet<>();
}
