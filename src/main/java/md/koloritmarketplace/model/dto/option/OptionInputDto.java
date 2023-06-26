package md.koloritmarketplace.model.dto.option;

import lombok.Data;
import md.koloritmarketplace.model.dto.LanguageEmbDto;
import md.koloritmarketplace.model.dto.TypeMeasureContentDto;
import md.koloritmarketplace.model.dto.TypeMeasureDto;

import java.util.List;

@Data
public class OptionInputDto {
    private Long optionId;
    private LanguageEmbDto optionName;
    private LanguageEmbDto showcaseOptionName;
    private TypeMeasureDto typeMeasure;
    private Long categoryId;
    private String typeOption;
    private List<TypeMeasureContentDto> typeMeasureContent;
}
