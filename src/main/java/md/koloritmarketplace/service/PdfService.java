package md.koloritmarketplace.service;

import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.model.dto.report.BucketContentReportDto;
import md.koloritmarketplace.model.entity.AppEntity;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface PdfService {
    ResponseEntity<byte[]> printOrder(HttpServletResponse response, AppEntity appEntity, Map<String, Object> params, List<BucketContentReportDto> fields) throws FileNotFoundException, JRException, ReportException;
}
