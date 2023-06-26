package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.CardDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import md.koloritmarketplace.model.entity.CardEntity;
import md.koloritmarketplace.model.enums.AccountRole;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.CardRepository;
import md.koloritmarketplace.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static md.koloritmarketplace.repository.specification.CardSpec.*;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final AppMapper appMapper;
    private final CardRepository cardRepository;

    @Override
    public CardDto getCardDto(Long cardId) {
        return appMapper.map(cardRepository.findById(cardId).get());
    }

    @Override
    public CardDto addCardDto(CardInputDto cardDto) {
        CardEntity cardEntity = appMapper.map(cardDto);
        cardEntity.setType(ObjectTypeEnum.CARD);
        cardEntity = cardRepository.save(cardEntity);
        return appMapper.map(cardEntity);
    }

    @Override
    public List<CardDto> addCardDto(List<CardInputDto> cardDto) {
        List<CardEntity> list = appMapper.mapToListCardEntityFromInput(cardDto);
        list.stream().forEach(r -> {
            r.setType(ObjectTypeEnum.CARD);
        });

        list = cardRepository.saveAll(list);
        return appMapper.mapToListCardDto(list);
    }

    @Override
    public CardDto updateCardDto(CardInputDto cardDto) {
        CardEntity cardEntity = appMapper.map(cardDto);
        cardEntity.setType(ObjectTypeEnum.CARD);
        cardEntity = cardRepository.save(cardEntity);
        return appMapper.map(cardEntity);
    }

    @Override
    public Page<CardDto> listCardDto(Pageable pageable, Map<String, String> params) {

        if (params == null) {
            params = new HashMap<>();
        }
        String owner = params.get("owner");
        String discountCardNumber=params.get("discountCardNumber");
        String percent=params.get("percent");;

        Specification<CardEntity> specification = Specification
                .where(StringUtils.hasText(owner) ? accountIdEqual(Long.valueOf(owner)) : null)
                .and(StringUtils.hasText(discountCardNumber) ? cardNumberEqual(discountCardNumber) : null)
                .and(StringUtils.hasText(percent) ? percentEqual(Long.valueOf(percent)) : null);

        Page<CardEntity> lst = cardRepository.findAll(specification,pageable);

        Page<CardDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListCardDto(lst.getContent()), lst.getPageable(), lst.getTotalElements());
        return pageDTOs;
    }
}
