package md.koloritmarketplace.model.dto.bucket;

import lombok.*;
import md.koloritmarketplace.model.dto.item.ItemDto;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketContentDto {
    private Long bucketContentId;

    private ItemDto item;

    private Long bucketId;

    private Long countUnit;

    private Double amountUnit;

    private Double amount;
}
