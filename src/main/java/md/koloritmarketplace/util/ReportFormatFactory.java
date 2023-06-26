package md.koloritmarketplace.util;

import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.exceptions.UnimplementedReportException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.export.type.PdfPrintScalingEnum;
import net.sf.jasperreports.export.type.PdfVersionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by rsv on 16.03.2016.
 */
public class ReportFormatFactory {

    private static final Logger logger = LoggerFactory.getLogger(ReportFormatFactory.class);
    private static final String REPORT_TEMPLATE_PATH = "/jasper_reports/";
    private static final String COMPILED_FILE_SUFFIX = ".jasper";
    private static final String UNCOMPILED_FILE_SUFFIX = ".jrxml";

    private ReportFormatEnum format;
    private String templateFileName;
    private String exportFileName;
    private HttpServletResponse response;
    private Map<String, Object> params;


    public ReportFormatFactory(ReportFormatEnum format, String templateFileName, String exportFileName, Map<String, Object> params, HttpServletResponse response) {
        this.format = format;
        this.exportFileName = exportFileName;
        this.response = response;
        this.templateFileName = templateFileName;
        this.params = params;
    }

    public void getReport() throws ReportException {
        try {
            switch (this.format) {
                case PDF_FORMAT:
                    getPdf();
                    break;
                case DBF_FORMAT:
                    getPdf();
                    break;
                case XLS_FORMAT:
                    getXls();
                    break;
                case CSV_FORMAT:
                    getCsv();
                    break;
                case RTF_FORMAT:
                    getRtf();
                    break;
                case DOCX_FORMAT:
                    getDocx();
                    break;
                default:
                    throw new UnimplementedReportException("Unimplemented report export to format " + format + "!");
            }
        } catch (JRException e) {
            throw new ReportException("Report datatable error!", e);
        } catch (IOException e) {
            throw new ReportException("Error obtaining output stream!", e);
        }
    }

    private static void compileFile(String fileName) throws JRException {
        String folderName = "classpath:/jasper_reports/";

        JasperCompileManager.compileReportToFile(folderName + fileName + ".jrxml", fileName + ".jasper");
    }

    private JasperPrint getJasperPrint() throws JRException, FileNotFoundException {

        params.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));
//        InputStream jasperStream = this.getClass().getResourceAsStream(REPORT_TEMPLATE_PATH + this.templateFileName + COMPILED_FILE_SUFFIX);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new FileInputStream("src/main/resources/jasper_reports/Invoice.jasper"));
        JRDataSource jrDataSource = (JRDataSource) params.get(JRParameter.REPORT_DATA_SOURCE);
        if (jrDataSource == null) {
            jrDataSource = new JREmptyDataSource();
            logger.warn("Report datasource is empty, using default JREmptyDataSource()!");
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, this.params, jrDataSource);
        return jasperPrint;
    }

    public void getPdf() throws JRException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=\"" + this.exportFileName + ".pdf\"");
        params.put(JRParameter.IS_IGNORE_PAGINATION, false);
        JRPdfExporter exporter = new JRPdfExporter();
        final OutputStream outStream = response.getOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(getJasperPrint()));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setMetadataAuthor("KOLORIT");
        configuration.setPdfVersion(PdfVersionEnum.VERSION_1_5);
        configuration.setPrintScaling(PdfPrintScalingEnum.NONE);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    public void getCsv() throws JRException, IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-disposition", "attachment; filename=" + this.exportFileName + ".csv");
        params.put(JRParameter.IS_IGNORE_PAGINATION, true);
        final OutputStream outStream = response.getOutputStream();
        JRCsvExporter csvExporter = new JRCsvExporter();
        csvExporter.setExporterInput(new SimpleExporterInput(getJasperPrint()));
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));
        csvExporter.exportReport();
    }

    public void getRtf() throws JRException, IOException {
        response.setContentType("application/rtf");
        response.setHeader("Content-disposition", "attachment; filename=" + this.exportFileName + ".rtf");
        params.put(JRParameter.IS_IGNORE_PAGINATION, false);
        final OutputStream outStream = response.getOutputStream();
        JRRtfExporter rtfExporter = new JRRtfExporter();
        rtfExporter.setExporterInput(new SimpleExporterInput(getJasperPrint()));
        rtfExporter.setExporterOutput(new SimpleWriterExporterOutput(outStream));
        rtfExporter.exportReport();
    }

    public void getDocx() throws JRException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-disposition", "attachment; filename=" + this.exportFileName + ".docx");
        params.put(JRParameter.IS_IGNORE_PAGINATION, false);
        final OutputStream outStream = response.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setExporterInput(new SimpleExporterInput(getJasperPrint()));
        OutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(outStream);
        docxExporter.setExporterOutput(output);
        docxExporter.exportReport();
    }

    public void getXls() throws JRException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + this.exportFileName + ".xls");
        params.put(JRParameter.IS_IGNORE_PAGINATION, true);
        final OutputStream outStream = response.getOutputStream();
        JRXlsExporter xlsExporter = new JRXlsExporter();
        xlsExporter.setExporterInput(new SimpleExporterInput(getJasperPrint()));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
        xlsExporter.exportReport();
    }

//    public void getDbf() throws IOException,  UnimplementedReportException {
//        response.setContentType("application/x-dbase");
//        response.setHeader("Content-Transfer-Encoding", "binary");
//        response.setHeader("Content-disposition", "attachment; filename=" + this.exportFileName + ".dbf");
//
//        final OutputStream outStream = response.getOutputStream();
//        if ("rpt_account_statement".equalsIgnoreCase(this.templateFileName)) {
//            JRBeanCollectionDataSource jrDataSource = (JRBeanCollectionDataSource) params.get(JRParameter.REPORT_DATA_SOURCE);
//            List<AccountStatementRow> accountStatementList = (List<AccountStatementRow>) jrDataSource.getData();
//            byte[] dbfFile = DbfUtil.getAccountStatement(accountStatementList);
//            outStream.write(dbfFile);
//        } else if ("rpt_multi_account_statement".equalsIgnoreCase(this.templateFileName)) {
//            JRBeanCollectionDataSource jrDataSource = (JRBeanCollectionDataSource) params.get(JRParameter.REPORT_DATA_SOURCE);
//            List<AccountStatementRow> accountStatementList = (List<AccountStatementRow>) jrDataSource.getData();
//            byte[] dbfFile = DbfUtil.getMultiAccountStatement(accountStatementList);
//            outStream.write(dbfFile);
//        } else {
//            throw new UnimplementedReportException("No dbf report implementation for template name " + this.templateFileName + "!");
//        }
//
//    }
}
