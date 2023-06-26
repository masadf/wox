package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.ActionDto;
import md.koloritmarketplace.model.entity.ActionEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.ActionRepository;
import md.koloritmarketplace.service.ActionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;
    private final AppMapper appMapper;

    @Override
    public ActionDto getActionDto(Long actionId) {
        return appMapper.map(actionRepository.findById(actionId).get());
    }

    @Override
    @Transactional
    public ActionDto addActionDto(ActionDto actionDto) {
        ActionEntity actionEntity = appMapper.map(actionDto);
        actionEntity.setType(ObjectTypeEnum.ACTION);
        Set<ItemEntity> itemEntities=actionEntity.getItems();
        actionEntity.setItems(Set.of());


        actionEntity = actionRepository.save(actionEntity);

        actionEntity.setItems(itemEntities);

        actionEntity = actionRepository.save(actionEntity);
        return appMapper.map(actionEntity);
    }

    @Override
    @Transactional
    public ActionDto updateActionDto(ActionDto actionDto) {

        ActionEntity actionEntity = appMapper.map(actionDto);
        actionEntity.setType(ObjectTypeEnum.ACTION);
        actionEntity = actionRepository.save(actionEntity);
        return appMapper.map(actionEntity);
    }

    @Override
    public Page<ActionDto> listActionDto(Pageable pageable, Map<String, String> params) {
        Page<ActionEntity> list = actionRepository.findAll(pageable);
        Page<ActionDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToActionDto(list.getContent()), list.getPageable(), list.getTotalElements());
        return pageDTOs;
    }
}
