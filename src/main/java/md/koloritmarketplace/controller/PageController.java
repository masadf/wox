package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.PageDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.service.PageService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "page")
@RequestMapping("/api/v1/page")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @PostMapping("/all")
    @Operation( tags = {"Page"})
    public ResponseEntity<Page<PageDto>> list(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(pageService.listPageDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping
    @Operation( tags = {"Page"})
    public ResponseEntity<PageDto> add(@RequestBody PageDto pageDto) {
        return ResponseEntity.ok(pageService.addPageDto(pageDto));
    }

    @PutMapping
    @Operation( tags = {"Page"})
    public ResponseEntity<PageDto> update(@RequestBody PageDto pageDto) {
        return ResponseEntity.ok(pageService.updatePageDto(pageDto));
    }

    @GetMapping("/{pageId}")
    @Operation( tags = {"Page"})
    public ResponseEntity<PageDto> getById(@PathVariable("pageId") Long pageId) {
        return ResponseEntity.ok(pageService.get(pageId));
    }
}
