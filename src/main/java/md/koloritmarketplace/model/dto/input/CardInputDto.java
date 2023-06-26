package md.koloritmarketplace.model.dto.input;

import lombok.Data;
import md.koloritmarketplace.model.enums.ObjectStatus;

@Data
public class CardInputDto {
    private  Long cardId;
    private String numberCard;
    private Long percent;
    private Long ownerId;
    private ObjectStatus status;
}
