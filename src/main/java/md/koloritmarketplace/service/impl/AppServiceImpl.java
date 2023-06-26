package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.repository.AppRepository;
import md.koloritmarketplace.service.AppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AppRepository appRepository;
    private final AppMapper appMapper;


    @Override
    public Page<AppDto> findAllApps(Pageable pageable, Map<String, String> params) {
        Page<AppEntity> list = appRepository.findAll(pageable);
        Page<AppDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToAppDto(list.getContent()), list.getPageable(), list.getTotalElements());
        return pageDTOs;
    }
}
