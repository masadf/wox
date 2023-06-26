package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.amdaris.common.exception.EntityNotFoundException;
import md.amdaris.common.security.jwt.JwtTokenUtil;
import md.amdaris.common.util.ErrorMessage;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.AuthenticationRequestDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.entity.account.AccountAddressEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.AccountRepository;
import md.koloritmarketplace.repository.AccountRoleRepository;
import md.koloritmarketplace.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

import static md.koloritmarketplace.repository.specification.AccountSpec.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppMapper appMapper;
    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String authentication(AuthenticationRequestDto authenticationRequest) {
        String token;
        try {
            AccountEntity account = accountRepository.findByUserNameAndPassword(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
                    .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));

//            if (account.getStatus().equals(ObjectStatusEnum.DISABLE))
//            {
//                throw new UserNotAvailableException("Данный пользователь заблокирован");
//            }
            List<String> roles=account.getRoles().stream().map(role-> role.getAccountRoleName().toString()
            ).toList();
            token = jwtTokenUtil.createToken(account.getUserName(),account.getFio(),account.getId().toString(),roles );
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return token;
    }

    @Override
    public AccountDto getAccountDto(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).get();

        return appMapper.map(accountEntity);
    }

    @Override
    @Transactional
    public AccountDto addAccountDto(AccountDto accountDto) {
        AccountEntity accountEntity = appMapper.map(accountDto);
        Set<AccountRoleEntity> list = accountEntity.getRoles();
        Set<AccountAddressEntity> accountAddressEntities = accountEntity.getContacts();
        accountEntity.setRoles(Set.of());
        accountEntity.setType(ObjectTypeEnum.ACCOUNT);
        accountEntity = accountRepository.save(accountEntity);
        if (!list.isEmpty()) {
            accountEntity.setRoles(getUpdatedRoles(list));
        }

        if (!accountAddressEntities.isEmpty()) {
            accountEntity.setContacts(getUpdatedContacts(accountAddressEntities, accountEntity.getId()));
        }

        accountEntity = accountRepository.save(accountEntity);
        return appMapper.map(accountEntity);
    }

    @Override
    public AccountDto updateAccountDto(AccountDto accountDto) {
        AccountEntity accountEntity = appMapper.map(accountDto);
        accountEntity.setType(ObjectTypeEnum.ACCOUNT);
        accountEntity.setRoles(getUpdatedRoles(accountEntity.getRoles()));
        accountEntity.setContacts(getUpdatedContacts(accountEntity.getContacts(), accountEntity.getId()));
        accountEntity.setId(accountEntity.getAccountId());
        accountEntity = accountRepository.save(accountEntity);
        return appMapper.map(accountEntity);
    }

    @Override
    public Page<AccountDto> listAccountDto(Pageable pageable, Map<String, String> params) {

        if (params == null) {
            params = new HashMap<>();
        }

        String accountRole = params.get("accountRole");
        String accountId = params.get("accountId");
        String accountName = params.get("accountName");


        Specification<AccountEntity> specification = Specification
                .where(StringUtils.hasText(accountRole) ? accountRoleEqual(AccountRole.valueOf(accountRole)) : null)
                .and(StringUtils.hasText(accountId) ? accountIdEqual(Long.parseLong(accountId)) : null)
                .and(StringUtils.hasText(accountName) ? likeFio(accountName.toUpperCase()) : null);


        Page<AccountEntity> list = accountRepository.findAll(specification, pageable);

        Page<AccountDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToAccountDto(list.getContent()), list.getPageable(), list.getTotalElements());
        return pageDTOs;
    }

    private Set<AccountRoleEntity> getUpdatedRoles(Set<AccountRoleEntity> accountRoleEntities) {
        Set<AccountRoleEntity> updated = new HashSet<>();
        accountRoleEntities.stream().forEach(r -> {
            Optional<AccountRoleEntity> accountRoleEntity = accountRoleRepository.findById(r.getAccountRoleId());
            updated.add(accountRoleEntity.get());
        });
        return updated;
    }

    private Set<AccountAddressEntity> getUpdatedContacts(Set<AccountAddressEntity> accountAddressEntities, Long id) {
        Set<AccountAddressEntity> updated = new HashSet<>();
        accountAddressEntities.stream().forEach(r -> {
            r.setAccountId(id);
            updated.add(r);
        });
        return updated;
    }
}
