package md.koloritmarketplace.service.impl;

import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.model.dto.report.BucketContentReportDto;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.service.PdfService;
import md.koloritmarketplace.util.ReportFormatEnum;
import md.koloritmarketplace.util.ReportFormatFactory;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ResponseEntity<byte[]> printOrder(HttpServletResponse response, AppEntity appEntity, Map<String, Object> params, List<BucketContentReportDto> fields) throws FileNotFoundException, JRException, ReportException {
     JRBeanCollectionDataSource reportDS = new JRBeanCollectionDataSource(fields);
        if (fields.size() != 0)
            params.put(JRParameter.REPORT_DATA_SOURCE, reportDS);
        params.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));
        ReportFormatFactory reportFormatFactory = new ReportFormatFactory(ReportFormatEnum.PDF_FORMAT, "Invoice", ("Invoice-" + ((BucketEntity)appEntity).getOrderCode()), params, response);
        reportFormatFactory.getReport();
//        JasperReport compileReport = JasperCompileManager
//                .compileReport(new FileInputStream("src/main/resources/jasper_reports/Invoice.jrxml"));
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, params, reportDS);
//
//         JasperExportManager.exportReportToPdfFile(jasperPrint,
//         System.currentTimeMillis() + ".pdf");
//
//        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
//
//        System.err.println(data);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
//
//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
//
        return null;
    }
}
