package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.AuthenticationRequestDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AccountService {
    String authentication(AuthenticationRequestDto dto);

    AccountDto getAccountDto(Long accountId);

    AccountDto addAccountDto(AccountDto accountDto);

    AccountDto updateAccountDto(AccountDto accountDto);

    Page<AccountDto> listAccountDto(Pageable pageable, Map<String, String> params);


}
