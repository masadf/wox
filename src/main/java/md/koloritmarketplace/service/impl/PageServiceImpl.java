package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.PageDto;
import md.koloritmarketplace.model.entity.PageEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.model.enums.TypePageEnum;
import md.koloritmarketplace.repository.PageRepository;
import md.koloritmarketplace.service.PageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.PageSpec.fetchSome;
import static md.koloritmarketplace.repository.specification.PageSpec.pageTypeEqual;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final AppMapper appMapper;
    private final PageRepository pageRepository;

    @Override
    public PageDto get(Long pageId) {
        return appMapper.map(pageRepository.findById(pageId).get());
    }

    @Override
    public PageDto addPageDto(PageDto pageDto) {
        PageEntity pageEntity = appMapper.map(pageDto);
        pageEntity.setType(ObjectTypeEnum.PAGE);
        pageEntity = pageRepository.save(pageEntity);
        return appMapper.map(pageEntity);
    }

    @Override
    public PageDto updatePageDto(PageDto pageDto) {
        PageEntity pageEntity = appMapper.map(pageDto);
        pageEntity.setType(ObjectTypeEnum.PAGE);
        pageEntity = pageRepository.save(pageEntity);
        return appMapper.map(pageEntity);
    }

    @Override
    public Page<PageDto> listPageDto(Pageable pageable, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        String pageType = params.get("pageType");


        Specification<PageEntity> specification = Specification
                .where(fetchSome())
                .and(StringUtils.hasText(pageType) ? pageTypeEqual(TypePageEnum.valueOf(pageType)) : null);

        Page<PageEntity> lst = pageRepository.findAll(specification, pageable);
        Page<PageDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListPageDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }
}
