package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.item.ItemEntity;

import java.util.HashSet;
import java.util.Set;

@Data
public class NewsDto extends AppDto {
    private Long newsId;
    private LanguageEmb languageTitleEmb;
    private LanguageEmb languageContentEmb;
}
