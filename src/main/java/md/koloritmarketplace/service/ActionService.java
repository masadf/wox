package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.ActionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ActionService {
    ActionDto getActionDto(Long actionId);

    ActionDto addActionDto(ActionDto actionDto);

    ActionDto updateActionDto(ActionDto actionDto);

    Page<ActionDto> listActionDto(Pageable pageable, Map<String, String> params);
}
