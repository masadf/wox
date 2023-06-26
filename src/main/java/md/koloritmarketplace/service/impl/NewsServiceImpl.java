package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.NewsDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.entity.NewsEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.NewsRepository;
import md.koloritmarketplace.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final AppMapper appMapper;
    private final NewsRepository newsRepository;

    @Override
    public NewsDto getNewsDto(Long newsId) {
        return appMapper.map(newsRepository.findById(newsId).get());
    }

    @Override
    public NewsDto addNewsDto(NewsDto newsDto) {
        NewsEntity newsEntity = appMapper.map(newsDto);
        newsEntity.setType(ObjectTypeEnum.NEWS);
        newsEntity = newsRepository.save(newsEntity);

        return appMapper.map(newsEntity);
    }

    @Override
    public NewsDto updateNewsDto(NewsDto newsDto) {
        NewsEntity newsEntity = appMapper.map(newsDto);
        newsEntity.setType(ObjectTypeEnum.NEWS);
        newsEntity = newsRepository.save(newsEntity);

        return appMapper.map(newsEntity);
    }

    @Override
    public List<NewsDto> listNewsDto() {
        return appMapper.mapToListToNewsDto(newsRepository.findAll());
    }

    @Override
    public Page<NewsDto> listNewsDto(PageParamDto pageParamDto) {
        Page<NewsEntity> newsEntities=newsRepository.findAll(pageParamDto.getPageRequest());

        return  new PageImpl<>(appMapper.mapToListToNewsDto(newsEntities.getContent()), newsEntities.getPageable(), newsEntities.getTotalElements());
    }
}
