package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.dto.option.OptionDto;

@Data
public class LinkOptionItemDto {
    private Long linkOptionItemId;
    OptionDto option;
    TypeMeasureDto typeMeasure;
    private Long itemId;
    private LanguageEmbDto languageEmb;
}
