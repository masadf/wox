package md.koloritmarketplace.service;

import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface BucketService {
    BucketDto getBucketDto(Long bucketId);

    BucketDto addBucketDto(BucketDto bucketDto);

    BucketDto updateBucketDto(BucketDto bucketDto);

    Page<BucketDto> listBucketDto(Pageable pageable, Map<String, String> params);

    void generatePdf(HttpServletResponse response, Long id) throws  ReportException;
}
