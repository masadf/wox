package md.koloritmarketplace.model.dto.option;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.LanguageEmbDto;

@Data
public class OptionContentDto  {
    private Long optionsContentId;
    private Long optionId;
    private LanguageEmbDto languageEmb;
}
