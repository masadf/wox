package md.koloritmarketplace.model.dto.account;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.entity.account.AccountAddressEntity;

import java.util.HashSet;
import java.util.Set;

@Data
public class AccountDto extends AppDto {
    private Long accountId;

    private String userName;

    private String fio;

    private String password;

    private String email;

    private Long cardId;

    private Set<AccountRoleDto> roles = new HashSet<>();

    private Set<AccountAddressEntity> contacts;
}
