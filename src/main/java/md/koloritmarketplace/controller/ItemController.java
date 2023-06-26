package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.item.ItemShowListDto;
import md.koloritmarketplace.model.input.params.PageOptionParamDto;
import md.koloritmarketplace.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "item")
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
private final ItemService itemService;

    @PostMapping("/item")
    @Operation( tags = {"Item"})
    ResponseEntity<Page<ItemShowListDto>> getItems(@RequestBody PageOptionParamDto pageParamDto) {
        return ResponseEntity.ok(itemService.listItemDto(pageParamDto));
    }

    @PostMapping("/save-item")
    @Operation( tags = {"Item"})
    ResponseEntity<ItemDto> addItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.addItemDto(itemDto));
    }

    @PutMapping("/save-item")
    @Operation( tags = {"Item"})
    ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.updateItemDto(itemDto));
    }

    @GetMapping("/item/{itemId}")
    @Operation( tags = {"Item"})
    public ResponseEntity<ItemDto> getItem(@PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(itemService.getItemDto(itemId));
    }
}
