package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.PartnerDto;
import md.koloritmarketplace.model.entity.PartnerEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.PartnerRepository;
import md.koloritmarketplace.service.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final AppMapper appMapper;
    private final PartnerRepository partnerRepository;

    @Override
    public PartnerDto getPartnerDto(Long partnerId) {
        return appMapper.map(partnerRepository.findById(partnerId).get());
    }

    @Override
    public PartnerDto addPartnerDto(PartnerDto partnerDto) {
        PartnerEntity partnerEntity = appMapper.map(partnerDto);
        partnerEntity.setType(ObjectTypeEnum.PARTNER);
        return appMapper.map(partnerRepository.save(partnerEntity));
    }

    @Override
    public PartnerDto updatePartnerDto(PartnerDto partnerDto) {
        PartnerEntity partnerEntity = appMapper.map(partnerDto);
        partnerEntity.setType(ObjectTypeEnum.PARTNER);
        return appMapper.map(partnerRepository.save(partnerEntity));
    }

    @Override
    public Page<PartnerDto> listPartnerDto(Pageable pageable, Map<String, String> params) {
        Page<PartnerEntity> lst = partnerRepository.findAll(pageable);
        Page<PartnerDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToPartnerDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }
}
