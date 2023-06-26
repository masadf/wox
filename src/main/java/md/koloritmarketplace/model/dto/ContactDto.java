package md.koloritmarketplace.model.dto;

import lombok.Data;

@Data
public class ContactDto extends AppDto {
    private Long contactId;
    private String value;
    private String contactType;
}
