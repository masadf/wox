package md.koloritmarketplace.controller;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.model.dto.*;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.option.OptionDto;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "admin")
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

//    @PostMapping("/app")
//    ResponseEntity<Page<AppDto>> getApps(@RequestBody PageParamDto pageParamDto) {
//        return ResponseEntity.ok(adminService.getAppList(pageParamDto.getPageRequest(),pageParamDto.getParams()));
//    }
}
