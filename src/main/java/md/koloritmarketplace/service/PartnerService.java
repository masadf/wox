package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.CategoryDto;
import md.koloritmarketplace.model.dto.PartnerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PartnerService {
    PartnerDto getPartnerDto(Long partnerId);

    PartnerDto addPartnerDto(PartnerDto categoryDto);

    PartnerDto updatePartnerDto(PartnerDto categoryDto);

    Page<PartnerDto> listPartnerDto(Pageable pageable, Map<String, String> params);
}
