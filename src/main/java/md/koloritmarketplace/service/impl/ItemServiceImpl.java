package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.item.ItemShowListDto;
import md.koloritmarketplace.model.entity.ImageEntity;
import md.koloritmarketplace.model.entity.LinkOptionItemEntity;
import md.koloritmarketplace.model.entity.item.ItemComplectationsEntity;
import md.koloritmarketplace.model.entity.item.ItemDopInfoEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.model.input.params.OptionsFilter;
import md.koloritmarketplace.model.input.params.PageOptionParamDto;
import md.koloritmarketplace.repository.ItemDescriptionRepository;
import md.koloritmarketplace.repository.ItemDopInfoRepository;
import md.koloritmarketplace.repository.ItemRepository;
import md.koloritmarketplace.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.ItemSpec.*;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemDescriptionRepository itemDescriptionRepository;
    private ItemDopInfoRepository itemDopInfoRepository;
    private final AppMapper appMapper;

    @Override
    public List<ItemEntity> findInIds(List<Long> ids) {
        return itemRepository.findByIdIn(ids);
    }

    @Override
    public List<ItemEntity> saveAll(List<ItemEntity> ids) {
        return itemRepository.saveAll(ids);
    }

    @Override
    public ItemDto getItemDto(Long itemId) {
        return appMapper.map(itemRepository.findById(itemId).get());
    }

    @Override
    @Transactional
    public ItemDto addItemDto(ItemDto itemDto) {
        ItemEntity itemEntity = appMapper.map(itemDto);
        itemEntity.setType(ObjectTypeEnum.ITEM);

        List<LinkOptionItemEntity> linkOptionItemEntities = new ArrayList<>();
        List<ItemComplectationsEntity> itemComplectationsEntities = new ArrayList<>();
        List<ItemDopInfoEntity> itemDopInfoEntities = new ArrayList<>();
        List<ImageEntity> imageEntities = new ArrayList<>();
        if (itemEntity.getDescriptions() != null) {
            itemEntity.setDescriptions(itemDescriptionRepository.save(itemEntity.getDescriptions()));
        }


        if (!itemEntity.getOptions().isEmpty()) {
            linkOptionItemEntities = itemEntity.getOptions();
            itemEntity.setOptions(new ArrayList<>());
        }
        if (itemEntity.getComplectationsList().size() != 0) {
            itemComplectationsEntities = itemEntity.getComplectationsList();
            itemEntity.setComplectationsList(new ArrayList<>());
        }
        if (itemEntity.getDopsList().size() != 0) {
            itemDopInfoEntities = itemEntity.getDopsList();
            itemEntity.setDopsList(new ArrayList<>());
        }
        if (!itemEntity.getImages().isEmpty()) {
            imageEntities = itemEntity.getImages();
            itemEntity.setImages(new ArrayList<>());
        }


        itemEntity = itemRepository.save(itemEntity);


        if (!linkOptionItemEntities.isEmpty() || !itemComplectationsEntities.isEmpty() || !itemDopInfoEntities.isEmpty() || !imageEntities.isEmpty()) {
            itemEntity = fillingToNewItemList(itemEntity, linkOptionItemEntities, itemComplectationsEntities, itemDopInfoEntities, imageEntities);
        }

        return appMapper.map(itemEntity);
    }

    @Override
    public ItemDto updateItemDto(ItemDto itemDto) {
        ItemEntity itemEntity = appMapper.map(itemDto);

        List<LinkOptionItemEntity> linkOptionItemEntities = new ArrayList<>();
        List<ItemComplectationsEntity> itemComplectationsEntities = new ArrayList<>();
        List<ItemDopInfoEntity> itemDopInfoEntities = new ArrayList<>();
        List<ImageEntity> imageEntities = new ArrayList<>();
        if (itemEntity.getDescriptions() != null) {
            itemEntity.setDescriptions(itemDescriptionRepository.save(itemEntity.getDescriptions()));
        }


        if (!itemEntity.getOptions().isEmpty()) {
            linkOptionItemEntities = itemEntity.getOptions();
            itemEntity.setOptions(new ArrayList<>());
        }
        if (itemEntity.getComplectationsList().size() != 0) {
            itemComplectationsEntities = itemEntity.getComplectationsList();
            itemEntity.setComplectationsList(new ArrayList<>());
        }
        if (itemEntity.getDopsList().size() != 0) {
            itemDopInfoEntities = itemEntity.getDopsList();
            itemEntity.setDopsList(new ArrayList<>());
        }
        if (!itemEntity.getImages().isEmpty()) {
            imageEntities = itemEntity.getImages();
            itemEntity.setImages(new ArrayList<>());
        }

        if (!linkOptionItemEntities.isEmpty() || !itemComplectationsEntities.isEmpty() || !itemDopInfoEntities.isEmpty() || !imageEntities.isEmpty()) {
            itemEntity = fillingToUpdatedItemList(itemEntity, linkOptionItemEntities, itemComplectationsEntities, itemDopInfoEntities, imageEntities);
        }

        return appMapper.map(itemEntity);
    }

    @Override
    public Page<ItemShowListDto> listItemDto(PageOptionParamDto pageParamDto) {
        Map<String, String> params = pageParamDto.getParams();
        List<OptionsFilter> listFilter = pageParamDto.getOptionsFilterList();
        if (params == null) {
            params = new HashMap<>();
        }
        if (listFilter.isEmpty()) {
            listFilter = new ArrayList<>();
        }
        String categoryId = params.get("categoryId");
        String model = params.get("model");
        String name = params.get("name");
        String brandId = params.get("brandId");
        String articul = params.get("articul");
        String minPrice = params.get("minPrice");
        String maxPrice = params.get("maxPrice");

        List<Long> optionIdList = new ArrayList<>();
        List<String> optionLinkValueList = new ArrayList<>();
        if (!listFilter.isEmpty()) {

            pageParamDto.getOptionsFilterList().forEach(op -> {
                optionIdList.add(op.getId());
                optionLinkValueList.add(op.getValue());
            });

        }
        Specification<ItemEntity> specification = Specification
                .where(StringUtils.hasText(categoryId) ? categoryIdEqual(Long.valueOf(categoryId)) : null)
                .and(StringUtils.hasText(model) ? likeModel(model) : null)
                .and(StringUtils.hasText(name) ? likeName(name.toUpperCase()) : null)
                .and(StringUtils.hasText(brandId) ? brandIdEqual(Long.valueOf(brandId)) : null)
                .and(StringUtils.hasText(articul) ? likeArticul(articul.toUpperCase()) : null)
                .and(StringUtils.hasText(minPrice) ? greaterThanMinPrice(Double.valueOf(minPrice)) : null)
                .and(StringUtils.hasText(maxPrice) ? lessThanMaxPrice(Double.valueOf(maxPrice)) : null)
                .and(optionIdList.size() != 0 ? optionIn(optionIdList) : null)
                .and(optionLinkValueList.size() != 0 ? optionLinkInRu(optionLinkValueList) : null);


        Page<ItemShowListDto> lst = itemRepository.findAll(specification, pageParamDto.getPageRequest()).map(o -> appMapper.mapToItemShowList(o));

        return lst;
    }

    private ItemEntity fillingToNewItemList(ItemEntity itemEntity, List<LinkOptionItemEntity> linkOptionItemEntities, List<ItemComplectationsEntity> itemComplectationsEntities, List<ItemDopInfoEntity> itemDopInfoEntities, List<ImageEntity> imageEntities) {
        Long id = itemEntity.getId();
        if (!linkOptionItemEntities.isEmpty()) {
            linkOptionItemEntities.stream().forEach(r -> {
                r.setItemId(id);
            });
            itemEntity.setOptions(linkOptionItemEntities);
        }

        if (!itemComplectationsEntities.isEmpty()) {
            itemComplectationsEntities.forEach(r -> r.setItemId(id));
            itemEntity.setComplectationsList(itemComplectationsEntities);
        }
        if (!itemDopInfoEntities.isEmpty()) {
            itemDopInfoEntities.forEach(r -> r.setItemId(id));
            itemEntity.setDopsList(itemDopInfoEntities);
        }
        if (!imageEntities.isEmpty()) {
            imageEntities.forEach(r -> r.setAppId(id));
            itemEntity.setImages(imageEntities);
        }
        return itemRepository.save(itemEntity);

    }

    private ItemEntity fillingToUpdatedItemList(ItemEntity itemEntity, List<LinkOptionItemEntity> linkOptionItemEntities, List<ItemComplectationsEntity> itemComplectationsEntities, List<ItemDopInfoEntity> itemDopInfoEntities, List<ImageEntity> imageEntities) {
        Long id = itemEntity.getId();
        if (!linkOptionItemEntities.isEmpty()) {
            linkOptionItemEntities.stream().forEach(r -> {
                r.setItemId(id);
            });
            itemEntity.getOptions().addAll(linkOptionItemEntities);
        }

        if (!itemComplectationsEntities.isEmpty()) {
            itemComplectationsEntities.forEach(r -> r.setItemId(id));
            itemEntity.getComplectationsList().addAll(itemComplectationsEntities);
        }
        if (!itemDopInfoEntities.isEmpty()) {
            itemDopInfoEntities.forEach(r -> r.setItemId(id));
            itemEntity.getDopsList().addAll(itemDopInfoEntities);
        }
        if (!imageEntities.isEmpty()) {
            imageEntities.forEach(r -> r.setAppId(id));
            itemEntity.getImages().addAll(imageEntities);
        }
        return itemRepository.save(itemEntity);

    }

}
