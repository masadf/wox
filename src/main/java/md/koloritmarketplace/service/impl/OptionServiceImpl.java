package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.option.OptionDto;
import md.koloritmarketplace.model.dto.option.OptionInputDto;
import md.koloritmarketplace.model.entity.TypeMeasureContentEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.option.OptionEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.OptionsRepository;
import md.koloritmarketplace.service.OptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.AccountSpec.*;
import static md.koloritmarketplace.repository.specification.OptionSpec.*;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionsRepository optionsRepository;
    private final AppMapper appMapper;

    @Override
    public OptionDto getOptionDto(Long optionId) {

        return appMapper.map(optionsRepository.findById(optionId).get());
    }

    @Override
    @Transactional
    public OptionDto addOptionDto(OptionDto optionDto) {
        OptionEntity optionEntity = appMapper.map(optionDto);
        optionEntity.setType(ObjectTypeEnum.OPTION);
//        Set<OptionContentEntity> optionContentEntities = optionEntity.getContent();
//        optionEntity.setContent(Set.of());

        optionEntity = optionsRepository.save(optionEntity);
        Long id = optionEntity.getId();
//        if (!optionContentEntities.isEmpty()) {
//            optionContentEntities.stream().forEach(r -> {
//                r.setOptionId(id);
//            });
//            optionEntity.setContent(optionContentEntities);
//            optionEntity = optionsRepository.save(optionEntity);
//        }

        return appMapper.map(optionEntity);
    }

    @Override
    @Transactional
    public OptionInputDto addOptionDto(OptionInputDto optionDto) {
        OptionEntity optionEntity = appMapper.map(optionDto);
        if (optionEntity.getType() == null) {
            optionEntity.setType(ObjectTypeEnum.OPTION);
        }

        if (optionEntity.getTypeMeasureContent().size() != 0) {
            List<TypeMeasureContentEntity> typeMeasureContentEntities = optionEntity.getTypeMeasureContent();
            optionEntity.setTypeMeasureContent(List.of());
            optionEntity = optionsRepository.save(optionEntity);
            Long id=optionEntity.getId();
            typeMeasureContentEntities.stream().forEach(r->r.setOptionId(id));
            optionEntity.setTypeMeasureContent(typeMeasureContentEntities);

        }

        optionEntity.setIsAll(1);
        optionEntity = optionsRepository.save(optionEntity);

        OptionInputDto optionInputDto = appMapper.mapToInputDto(optionEntity);
        return optionInputDto;
    }

    @Override
    public OptionDto updateOptionDto(OptionDto optionDto) {
        OptionEntity optionEntity = appMapper.map(optionDto);
        optionEntity.setType(ObjectTypeEnum.OPTION);
        optionEntity = optionsRepository.save(optionEntity);

        return appMapper.map(optionEntity);
    }

    @Override
    @Transactional
    public OptionInputDto updateOptionDto(OptionInputDto optionDto) {
        OptionEntity optionEntity = appMapper.map(optionDto);
        if (optionEntity.getTypeMeasureContent().size() != 0) {
            List<TypeMeasureContentEntity> typeMeasureContentEntities = optionEntity.getTypeMeasureContent();
            optionEntity.setTypeMeasureContent(List.of());
            optionEntity = optionsRepository.save(optionEntity);
            Long id=optionEntity.getId();
            typeMeasureContentEntities.stream().forEach(r->r.setOptionId(id));
            optionEntity.getTypeMeasureContent().addAll(typeMeasureContentEntities);
        }
        optionEntity = optionsRepository.save(optionEntity);
        OptionInputDto optionInputDto = appMapper.mapToInputDto(optionEntity);
        return optionInputDto;
    }

    @Override
    public Page<OptionDto> listOptionDto(Pageable pageable, Map<String, String> params) {
        System.out.println();
        Specification<OptionEntity> specification = Specification
                .where(StringUtils.hasText(params.get("categoryId")) ? categoryEqual(Long.valueOf(params.get("categoryId"))): null)
                .and(StringUtils.hasText(params.get("optionName")) ? likeOptionName(params.get("optionName")): null)
                .and(StringUtils.hasText(params.get("typeMeasureValue")) ? likeTypeMeasureValue(params.get("typeMeasureValue")): null);


        Page<OptionEntity> lst = optionsRepository.findAll(specification,pageable);
        Page<OptionDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToOptionDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }
}
