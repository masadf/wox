package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

@Data
public class PartnerDto extends AppDto {
    private Long partnerId;
    private LanguageEmb languageEmb;
}
