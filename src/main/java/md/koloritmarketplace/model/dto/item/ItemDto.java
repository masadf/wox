package md.koloritmarketplace.model.dto.item;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.LinkOptionItemDto;
import md.koloritmarketplace.model.entity.item.ItemDescriptionEntity;
import md.koloritmarketplace.model.entity.item.ItemDopInfoEntity;

import java.util.List;

@Data
public class ItemDto extends AppDto {

    private Long itemId;
    private String model;
    private String name;
    private String articul;
    private Long categoryId;
    private BrandDto brandEntity;
    private Double amount;
    private Double disAmount;
    private String itemCode;
    private Long count;
    private List<LinkOptionItemDto> options;
    private List<ItemDopInfoDto> dopsList;
    private List<ItemComplectationsDto> complectationsList;

    private ItemDescriptionEntity descriptions;
}
