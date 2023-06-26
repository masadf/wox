package md.koloritmarketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.model.dto.PageParamDto;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import md.koloritmarketplace.service.BucketService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

@RestController(value = "bucket")
@RequestMapping("/api/v1/bucket")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;

    @PostMapping()
    @Operation(description = "Get buckets by id \n" +
            "  bucketId: integer, (айди заказа)\n" +
            "  accountName: string, (имя пользователя, сделавшего заказ)\n" +
            "  phoneNumber: string, (номер для доставки, не обязятально равен номеру телефона пользователя, сделавшего заказ)\n" +
            "  minAmount: integer, (поиск заказов, где сумма заказа >= minAmount)\n" +
            "  maxAmount: integer, (поиск заказов, где сумма заказа <= maxAmount)\n" +
            "  bucketStatus: string, (статус заказа)", tags = {"Bucket"})
    public ResponseEntity<Page<BucketDto>> getBuckets(@RequestBody PageParamDto pageParamDto) {
        return ResponseEntity.ok(bucketService.listBucketDto(pageParamDto.getPageRequest(), pageParamDto.getParams()));
    }

    @GetMapping("/{bucketId}")
    @Operation(description = "Get an bucket by id", tags = {"Bucket"})
    public ResponseEntity<BucketDto> getBucket(@PathVariable("bucketId") Long bucketId) {
        return ResponseEntity.ok(bucketService.getBucketDto(bucketId));
    }

    @PostMapping("/save-bucket")
    @Operation(description = "Add new bucket", tags = {"Bucket"})
    ResponseEntity<BucketDto> addBucket(@RequestBody BucketDto bucketDto) {
        return ResponseEntity.ok(bucketService.addBucketDto(bucketDto));
    }

    @PutMapping("/save-bucket")
    @Operation( tags = {"Bucket"})
    ResponseEntity<BucketDto> updateBucket(@RequestBody BucketDto bucketDto) {
        return ResponseEntity.ok(bucketService.updateBucketDto(bucketDto));
    }

    @GetMapping("/report/{id}")
    @Operation( tags = {"Bucket"})
    public void generatePdf(@PathVariable("id") Long id,
                            HttpServletResponse response) throws JRException, FileNotFoundException, ReportException {
           bucketService.generatePdf(response,id);
    }
}
