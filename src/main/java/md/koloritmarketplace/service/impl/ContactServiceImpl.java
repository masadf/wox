package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.ContactDto;
import md.koloritmarketplace.model.entity.ContactEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.ContactRepository;
import md.koloritmarketplace.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final AppMapper appMapper;
    private  final ContactRepository contactRepository;

    @Override
    public ContactDto getContactDto(Long contactId) {
        return appMapper.map(contactRepository.findById(contactId).get());
    }

    @Override
    public ContactDto addContactDto(ContactDto contactDto) {
        ContactEntity contactEntity = appMapper.map(contactDto);
        contactEntity.setType(ObjectTypeEnum.CONTACT);
        contactEntity = contactRepository.save(contactEntity);
        return appMapper.map(contactEntity);
    }

    @Override
    public ContactDto updateContactDto(ContactDto contactDto) {
        ContactEntity contactEntity = appMapper.map(contactDto);
        contactEntity.setType(ObjectTypeEnum.CONTACT);
        contactEntity = contactRepository.save(contactEntity);
        return appMapper.map(contactEntity);
    }

    @Override
    public List<ContactDto> listContactDto() {
        List<ContactEntity> list = contactRepository.findAll();
        return appMapper.mapToListContactDto(list);
    }

    @Override
    public void deleteById(Long contactId) {
        contactRepository.deleteById(contactId);
    }
}
