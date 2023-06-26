package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.TypeMeasureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TypeMeasureService {
    TypeMeasureDto getTypeMeasureDto(Long TypeMeasureId);

    TypeMeasureDto addTypeMeasureDto(TypeMeasureDto typeMeasureDto);

    TypeMeasureDto updateTypeMeasureDto(TypeMeasureDto typeMeasureDto);

    Page<TypeMeasureDto> listTypeMeasureDto(Pageable pageable, Map<String, String> param);
}
