package md.koloritmarketplace.model.dto;

import lombok.Data;

@Data
public class CardDto extends AppDto {

    private Long cardId;
    private String numberCard;
    private String ownerId;
    private String ownerFio;
    private Long percent;
}
