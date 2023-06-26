package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.enums.CountryTypeEnum;

@Data
public class LinkCountryBrandDto {
    private Long linkCountryBrandId;

    private CountryDto countryId;

    private Long brandId;

    private CountryTypeEnum type;
}
