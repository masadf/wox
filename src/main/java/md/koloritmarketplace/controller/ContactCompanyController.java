package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.ContactCompanyDto;
import md.koloritmarketplace.service.ContactCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "contactCompany")
@RequestMapping("/api/v1/contact-company")
@RequiredArgsConstructor
public class ContactCompanyController {
    private final ContactCompanyService contactCompanyService;


    @GetMapping
    @Operation( tags = {"Contact company"})
    public ResponseEntity<List<ContactCompanyDto>> list() {
        return ResponseEntity.ok(contactCompanyService.listContactCompanyDto());
    }

    @GetMapping("/{contactCompanyId}")
    @Operation( tags = {"Contact company"})
    public ResponseEntity<ContactCompanyDto> getById(@PathVariable("contactCompanyId") Long contactCompanyId) {
        return ResponseEntity.ok(contactCompanyService.getContactCompanyDto(contactCompanyId));
    }

    @PostMapping
    @Operation( tags = {"Contact company"})
    public ResponseEntity<ContactCompanyDto> add(@RequestBody ContactCompanyDto contactCompanyDto) {
        return ResponseEntity.ok(contactCompanyService.addContactCompanyDto(contactCompanyDto));
    }

    @PutMapping
    @Operation( tags = {"Contact company"})
    public ResponseEntity<ContactCompanyDto> update(@RequestBody ContactCompanyDto contactCompanyDto) {
        return ResponseEntity.ok(contactCompanyService.updateContactCompanyDto(contactCompanyDto));
    }

    @DeleteMapping("/{contactCompanyId}")
    @Operation( tags = {"Contact company"})
    void delete(@PathVariable("contactCompanyId") Long contactCompanyId) {
        contactCompanyService.deleteContactCompanyDto(contactCompanyId);
    }
}
