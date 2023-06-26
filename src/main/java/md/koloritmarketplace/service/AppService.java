package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.entity.AppEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AppService {
    Page<AppDto> findAllApps(Pageable pageable, Map<String, String> params);
}
