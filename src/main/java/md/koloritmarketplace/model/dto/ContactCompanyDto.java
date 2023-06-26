package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

@Data
public class ContactCompanyDto extends AppDto {
    private Long contactCompanyId;
    private String numberPhone;
    private LanguageEmb addr;
    private LanguageEmb timeWorkOne;
    private LanguageEmb timeWorkTwo;
}
