package md.koloritmarketplace.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PromoCodeDto extends AppDto {

    private Long promoCodeId;
    private String promoCode;
    private String startDate;
    private String endDate;
    private Long percent;
}
