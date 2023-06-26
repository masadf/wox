package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.CardDto;
import md.koloritmarketplace.model.dto.CategoryDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CardService {

    CardDto getCardDto(Long cardId);

    CardDto addCardDto(CardInputDto cardDto);
    List<CardDto> addCardDto( List<CardInputDto> cardDto);

    CardDto updateCardDto(CardInputDto cardDto);

    Page<CardDto> listCardDto(Pageable pageable, Map<String, String> params);
}
