package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "test")
@RequestMapping()
@RequiredArgsConstructor
public class TestController {
    @GetMapping()
    @Operation( tags = {"Test"})
    public ResponseEntity<String> getTypeMeasure() {
        return ResponseEntity.ok("work");
    }

}
