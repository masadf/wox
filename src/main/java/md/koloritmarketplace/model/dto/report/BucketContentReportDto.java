package md.koloritmarketplace.model.dto.report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BucketContentReportDto {
    private String itemName;

    private Long countUnit;

    private Double amountUnit;

    private Double amount;
}
