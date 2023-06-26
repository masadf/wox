package md.koloritmarketplace.model.dto.item;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

@Data
public class ItemComplectationsDto {
    private Long itemDopInfoId;

    private Long itemId;

    private LanguageEmb languageValue;

}
