package md.koloritmarketplace.model.dto.item;

import lombok.Data;
import md.koloritmarketplace.model.dto.LanguageEmbDto;

@Data
public class ItemDescriptionDto {
    private Long itemDescriptionId;
    private LanguageEmbDto languageEmb;
}
