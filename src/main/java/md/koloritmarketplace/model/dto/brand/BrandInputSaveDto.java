package md.koloritmarketplace.model.dto.brand;

import lombok.Data;
import md.koloritmarketplace.model.dto.ImageDto;

import java.util.List;

@Data
public class BrandInputSaveDto {
    private Long brandId;
    private String brandName;
    private List<ImageDto> images;
}
