package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.dto.option.OptionDto;

import javax.persistence.Column;
import java.util.List;

@Data
public class CategoryDto extends AppDto {
    private Long categoryId;
    private LanguageEmbDto languageEmb;
    private Long parentId;


    private String position;
    List<OptionDto> options;
    private List<CategoryDto> childs;
}
