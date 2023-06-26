package md.koloritmarketplace.model.dto.item;

import lombok.Data;
import md.koloritmarketplace.model.dto.ImageDto;
import md.koloritmarketplace.model.enums.ObjectStatus;

import java.util.List;

@Data
public class ItemShowListDto {

    private Long id;
    private Long itemId;
    private String model;
    private String name;
    private String articul;
    private Double amount;
    private Double disAmount;
    private String itemCode;
    private Long count;
    private ObjectStatus status;
    private ImageDto image;
}
