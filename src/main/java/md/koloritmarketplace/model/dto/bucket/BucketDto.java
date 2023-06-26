package md.koloritmarketplace.model.dto.bucket;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.account.AccountAddressDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import md.koloritmarketplace.model.enums.TypeDeliveryEnum;
import md.koloritmarketplace.model.enums.TypePayEnum;

import java.util.Set;

@Data
public class BucketDto extends AppDto {
    private Long bucketId;
    private AccountDto buyer;
    private String orderCode;
    private Double amount;
    private TypeDeliveryEnum typeDelivery;
    private TypePayEnum typePay;
    private BuckeStatusEnum bucketStatus;
    private Set<BucketContentDto> content;
    private AccountAddressDto address;
}
