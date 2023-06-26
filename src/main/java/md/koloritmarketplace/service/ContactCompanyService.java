package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.ContactCompanyDto;

import java.util.List;

public interface ContactCompanyService {
    ContactCompanyDto getContactCompanyDto(Long contactCompanyId);

    void deleteContactCompanyDto(Long contactCompanyId);

    ContactCompanyDto addContactCompanyDto(ContactCompanyDto contactCompanyDto);

    ContactCompanyDto updateContactCompanyDto(ContactCompanyDto contactCompanyDto);

    List<ContactCompanyDto> listContactCompanyDto();
}
