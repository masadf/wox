package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.AuthenticationRequestDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "account")
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private  final AccountService accountService;



    @PostMapping("/authentication")
    @Operation(description = "Auth by username and passwrod", tags = {"Account"})
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        return ResponseEntity.ok(accountService.authentication(authenticationRequest));
    }

    @Operation(description = "Get all accounts", tags = {"Account"})
    @PostMapping()
    public ResponseEntity<Page<AccountDto>> listAccountDto(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(accountService.listAccountDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/save-account")
    @Operation(description = "Save new Account", tags = {"Account"})
    ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto brandDto) {
        return ResponseEntity.ok(accountService.addAccountDto(brandDto));
    }

    @PutMapping("/save-account")
    @Operation(description = "Update an existed account", tags = {"Account"})
    ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto brandDto) {
        return ResponseEntity.ok(accountService.updateAccountDto(brandDto));
    }

    @GetMapping("/{accountId}")
    @Operation(description = "Find an account by id", tags = {"Account"})
    public ResponseEntity<AccountDto> getAccount(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(accountService.getAccountDto(accountId));
    }

}
