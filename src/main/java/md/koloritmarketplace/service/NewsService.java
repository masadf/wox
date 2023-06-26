package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.NewsDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    NewsDto getNewsDto(Long newsId);

    NewsDto addNewsDto(NewsDto newsDto);

    NewsDto updateNewsDto(NewsDto newsDto);

    List<NewsDto> listNewsDto();

    Page<NewsDto> listNewsDto(PageParamDto pageParamDto);
}
