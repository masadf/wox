package md.koloritmarketplace.model.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Data
public class PageParamDto {
    private Map<String, String> params;
    int page;
    int size;
    Sort.Direction sortDir;
    String sortField;

    public PageRequest getPageRequest() {
        PageRequest pageRequest;
        if (getSortField() != null) {
            pageRequest =
                    PageRequest.of(getPage()
                            , getSize()
                            , getSortDir()
                            , getSortField());
        } else {
            pageRequest =
                    PageRequest.of(getPage()
                            , getSize());

        }
        return pageRequest;
    }
}
