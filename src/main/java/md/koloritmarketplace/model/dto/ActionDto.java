package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.item.ItemEntity;

import java.util.HashSet;
import java.util.Set;

@Data
public class ActionDto extends AppDto {
    private Long actionsId;
    private LanguageEmb languageTitleEmb;
    private LanguageEmb languageContentEmb;
    private String endDate;
    private Set<ItemEntity> items = new HashSet<>();
}
