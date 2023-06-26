package md.koloritmarketplace.model.dto.account;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.enums.AccountRole;

@Data
public class AccountRoleDto {
    private Long accountRoleId;

    private AccountRole accountRoleName;

    private LanguageEmb languageEmb;
}
