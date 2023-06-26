package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.ActionDto;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.service.ActionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "action")
@RequestMapping("/api/v1/action")
@RequiredArgsConstructor
public class ActionController {
private final ActionService actionService;



    @PostMapping()
    @Operation(description = "Get Actions by params", tags = {"Action"})
    ResponseEntity<Page<ActionDto>> getAction(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(actionService.listActionDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @PostMapping("/save-action")
    @Operation(description = "Add new action", tags = {"Action"})
    ResponseEntity<ActionDto> addTypeMeasure(@RequestBody ActionDto actionDto) {
        return ResponseEntity.ok(actionService.addActionDto(actionDto));
    }

    @PutMapping("/save-action")
    @Operation(description = "Update an existed action", tags = {"Action"})
    ResponseEntity<ActionDto> updateTypeMeasure(@RequestBody ActionDto actionDto) {
        return ResponseEntity.ok(actionService.updateActionDto(actionDto));
    }

    @GetMapping("/{actionId}")
    @Operation(description = "Find an action by id", tags = {"Action"})
    public ResponseEntity<ActionDto> getAction(@PathVariable("actionId") Long actionId) {
        return ResponseEntity.ok(actionService.getActionDto(actionId));
    }
}
