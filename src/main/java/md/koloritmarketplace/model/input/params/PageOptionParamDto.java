package md.koloritmarketplace.model.input.params;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import md.koloritmarketplace.model.dto.PageParamDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PageOptionParamDto extends PageParamDto {
    private List<OptionsFilter> optionsFilterList=new ArrayList<>();
}
