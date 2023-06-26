package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.ContactCompanyDto;
import md.koloritmarketplace.model.entity.ContactCompanyEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.ContactCompanyRepository;
import md.koloritmarketplace.service.ContactCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactCompanyServiceImpl implements ContactCompanyService {
    private final AppMapper appMapper;
    private final ContactCompanyRepository contactCompanyRepository;

    @Override
    public ContactCompanyDto getContactCompanyDto(Long contactCompanyId) {
        return appMapper.map(contactCompanyRepository.findById(contactCompanyId).get());
    }

    @Override
    public void deleteContactCompanyDto(Long contactCompanyId) {
        contactCompanyRepository.deleteById(contactCompanyId);
    }

    @Override
    public ContactCompanyDto addContactCompanyDto(ContactCompanyDto contactCompanyDto) {
        ContactCompanyEntity contactCompanyEntity = appMapper.map(contactCompanyDto);
        contactCompanyEntity.setType(ObjectTypeEnum.CONTACTCOMPANY);
        return appMapper.map(contactCompanyRepository.save(contactCompanyEntity));
    }

    @Override
    public ContactCompanyDto updateContactCompanyDto(ContactCompanyDto contactCompanyDto) {
        ContactCompanyEntity contactCompanyEntity = appMapper.map(contactCompanyDto);
        contactCompanyEntity.setType(ObjectTypeEnum.CONTACTCOMPANY);
        return appMapper.map(contactCompanyRepository.save(contactCompanyEntity));
    }

    @Override
    public List<ContactCompanyDto> listContactCompanyDto() {
        return appMapper.mapToListToContactCompanyDto(contactCompanyRepository.findAll());
//        return null;
    }
}
