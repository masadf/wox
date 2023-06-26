package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.TypePageEnum;

@Data
public class PageDto extends AppDto {
    private Long pageId;
    private Long year;
    private LanguageEmb languageTitleEmb;
    private LanguageEmb languageContentEmb;
    private TypePageEnum pageType;
}
