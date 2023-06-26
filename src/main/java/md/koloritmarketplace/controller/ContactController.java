package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.ContactDto;
import md.koloritmarketplace.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "contact")
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;


    @GetMapping()
    @Operation( tags = {"Contact"})
    ResponseEntity<List<ContactDto>> getContact() {
        return ResponseEntity.ok(contactService.listContactDto());
    }

    @PostMapping("/save-contact")
    @Operation( tags = {"Contact"})
    ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.addContactDto(contactDto));
    }

    @PutMapping("/save-contact")
    @Operation( tags = {"Contact"})
    ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.updateContactDto(contactDto));
    }

    @GetMapping("/{contactId}")
    @Operation( tags = {"Contact"})
    public ResponseEntity<ContactDto> getContact(@PathVariable("contactId") Long contactId) {
        return ResponseEntity.ok(contactService.getContactDto(contactId));
    }

    @DeleteMapping("/{contactId}")
    @Operation( tags = {"Contact"})
    public void deleteContact(@PathVariable("contactId") Long contactId) {
        contactService.deleteById(contactId);
    }
}
