package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.CardDto;
import md.koloritmarketplace.model.dto.CategoryDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import md.koloritmarketplace.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "card")
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


    @PostMapping("/all")
    @Operation( tags = {"Card"})
    ResponseEntity<Page<CardDto>> getCards(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(cardService.listCardDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping
    @Operation( tags = {"Card"})
    ResponseEntity<CardDto> addCard(@RequestBody CardInputDto cardDto) {
        return ResponseEntity.ok(cardService.addCardDto(cardDto));
    }

    @PostMapping("/list")
    @Operation( tags = {"Card"})
    ResponseEntity<List<CardDto>> addCards(@RequestBody List<CardInputDto> cardDto) {
        return ResponseEntity.ok(cardService.addCardDto(cardDto));
    }

    @PutMapping
    @Operation( tags = {"Card"})
    ResponseEntity<CardDto> updateCard(@RequestBody CardInputDto cardDto) {
        return ResponseEntity.ok(cardService.updateCardDto(cardDto));
    }

    @GetMapping("/{cardId}")
    @Operation( tags = {"Card"})
    public ResponseEntity<CardDto> getCard(@PathVariable("cardId") Long cardId) {
        return ResponseEntity.ok(cardService.getCardDto(cardId));
    }
}
