package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.TypeMeasureDto;
import md.koloritmarketplace.model.entity.TypeMeasureEntity;
import md.koloritmarketplace.repository.TypeMeasureRepository;
import md.koloritmarketplace.service.TypeMeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TypeMeasureServiceImpl implements TypeMeasureService {
    private final TypeMeasureRepository typeMeasureRepository;
    private final AppMapper appMapper;


    @Override
    public TypeMeasureDto getTypeMeasureDto(Long typeMeasureId) {
        return appMapper.map(typeMeasureRepository.findById(typeMeasureId).get());
    }

    @Override
    public TypeMeasureDto addTypeMeasureDto(TypeMeasureDto typeMeasureDto) {

        TypeMeasureEntity typeMeasureEntity = appMapper.map(typeMeasureDto);

        typeMeasureEntity = typeMeasureRepository.save(typeMeasureEntity);

        return appMapper.map(typeMeasureEntity);

    }

    @Override
    public TypeMeasureDto updateTypeMeasureDto(TypeMeasureDto typeMeasureDto) {
        TypeMeasureEntity typeMeasureEntity = appMapper.map(typeMeasureDto);

        typeMeasureEntity = typeMeasureRepository.save(typeMeasureEntity);

        return appMapper.map(typeMeasureEntity);
    }

    @Override
    public Page<TypeMeasureDto> listTypeMeasureDto(Pageable pageable, Map<String, String> param) {
        Page<TypeMeasureEntity> lst = typeMeasureRepository.findAll(pageable);
        Page<TypeMeasureDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToTypeMeasureDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }
}
