package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PageService {
    PageDto get(Long pageId);

    PageDto addPageDto(PageDto categoryDto);

    PageDto updatePageDto(PageDto categoryDto);

    Page<PageDto> listPageDto(Pageable pageable, Map<String, String> params);
}
