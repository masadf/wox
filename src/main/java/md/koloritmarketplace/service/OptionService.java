package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.option.OptionDto;
import md.koloritmarketplace.model.dto.option.OptionInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface OptionService {

    OptionDto getOptionDto(Long optionId);

    OptionDto addOptionDto(OptionDto optionDto);

    OptionInputDto addOptionDto(OptionInputDto optionDto);

    OptionDto updateOptionDto(OptionDto optionDto);

    OptionInputDto updateOptionDto(OptionInputDto optionDto);

    Page<OptionDto> listOptionDto(Pageable pageable, Map<String, String> params);
}
