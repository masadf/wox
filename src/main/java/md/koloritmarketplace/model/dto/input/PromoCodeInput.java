package md.koloritmarketplace.model.dto.input;

import lombok.Data;
import md.koloritmarketplace.model.enums.ObjectStatus;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class PromoCodeInput {
    private Long promoCodeId;
    private String promoCode;
    private String startDate;
    private String endDate;
    private Long percent;
    private ObjectStatus status;
}
