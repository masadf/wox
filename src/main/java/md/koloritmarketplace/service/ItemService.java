package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.item.ItemShowListDto;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.input.params.PageOptionParamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ItemService {
    List<ItemEntity> findInIds(List<Long> ids);

    List<ItemEntity> saveAll(List<ItemEntity> ids) ;

    ItemDto getItemDto(Long itemId);

    ItemDto addItemDto(ItemDto categoryDto);

    ItemDto updateItemDto(ItemDto categoryDto);

    Page<ItemShowListDto> listItemDto(PageOptionParamDto pageParamDto);
}
