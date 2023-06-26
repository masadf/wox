package md.koloritmarketplace.model.dto.account;

import lombok.Data;

@Data
public class AccountAddressDto {
    private Long accountAddressId;
    private String phoneNumber;
    private String region;
    private String town;
    private String adress;
    private String zipIndex;
    private Long accountId;

}
