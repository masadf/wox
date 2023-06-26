package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.enums.ImageTypeEnum;

@Data
public class ImageDto {
    private Long imageId;
    private Long appId;
    private ImageTypeEnum imageType;
    private String value;
}
