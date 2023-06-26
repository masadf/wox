package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.ContactDto;

import java.util.List;

public interface ContactService {
    ContactDto getContactDto(Long contactId);

    ContactDto addContactDto(ContactDto categoryDto);

    ContactDto updateContactDto(ContactDto categoryDto);

    List<ContactDto> listContactDto();

    void deleteById(Long contactId);
}
