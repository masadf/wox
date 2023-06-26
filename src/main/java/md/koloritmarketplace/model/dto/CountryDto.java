package md.koloritmarketplace.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private Long countryId;
    private String image;
    private LanguageEmbDto languageEmb;
}
