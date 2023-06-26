package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.NewsDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController(value = "news")
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;


    @Operation(description = "Get all news for Page", tags = {"News"})
    @PostMapping()
    public ResponseEntity<Page<NewsDto>> listAccountDto(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(newsService.listNewsDto(pageParamDto));
    }

    @GetMapping("/news")
    @Operation( tags = {"News"})
    ResponseEntity<List<NewsDto>> getNews() {
        return ResponseEntity.ok(newsService.listNewsDto());
    }

    @PostMapping("/save-news")
    @Operation( tags = {"News"})
    ResponseEntity<NewsDto> addTypeMeasure(@RequestBody NewsDto newsDto) {
        return ResponseEntity.ok(newsService.addNewsDto(newsDto));
    }

    @PutMapping("/save-news")
    @Operation( tags = {"News"})
    ResponseEntity<NewsDto> updateTypeMeasure(@RequestBody NewsDto newsDto) {
        return ResponseEntity.ok(newsService.updateNewsDto(newsDto));
    }

    @GetMapping("/news/{newsId}")
    @Operation( tags = {"News"})
    public ResponseEntity<NewsDto> getNewsById(@PathVariable("newsId") Long newsId) {
        return ResponseEntity.ok(newsService.getNewsDto(newsId));
    }
}
