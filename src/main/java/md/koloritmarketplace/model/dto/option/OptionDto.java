package md.koloritmarketplace.model.dto.option;

import lombok.Data;
import md.koloritmarketplace.model.dto.AppDto;
import md.koloritmarketplace.model.dto.LanguageEmbDto;
import md.koloritmarketplace.model.entity.TypeMeasureContentEntity;
import md.koloritmarketplace.model.entity.TypeMeasureEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class OptionDto extends AppDto {
    private Long optionId;
    private LanguageEmbDto optionName;
    private LanguageEmbDto showcaseOptionName;
    private Long categoryId;
    private TypeMeasureEntity typeMeasure;
    private String typeOption;
    private List<TypeMeasureContentEntity> typeMeasureContent = new ArrayList<>();
}
