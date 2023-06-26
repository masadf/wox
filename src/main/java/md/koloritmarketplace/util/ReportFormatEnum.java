package md.koloritmarketplace.util;

import md.koloritmarketplace.exceptions.InvalidExportFormatException;
import md.koloritmarketplace.exceptions.ReportException;

import java.io.Serializable;

/**
 * Created by rsv on 16.03.2016.
 */
public enum ReportFormatEnum implements Serializable {
    PDF_FORMAT, CSV_FORMAT, XLS_FORMAT, DBF_FORMAT, RTF_FORMAT, DOCX_FORMAT;

    public static ReportFormatEnum determineFormat(String format) throws ReportException {
        switch (format.toUpperCase()) {
            case "PDF":
                return ReportFormatEnum.PDF_FORMAT;
            case "CSV":
                return ReportFormatEnum.CSV_FORMAT;
            case "XLS":
                return ReportFormatEnum.XLS_FORMAT;
            case "DBF":
                return ReportFormatEnum.DBF_FORMAT;
            case "RTF":
                return ReportFormatEnum.RTF_FORMAT;
            case "DOCX":
                return ReportFormatEnum.DOCX_FORMAT;
            default:
                throw new InvalidExportFormatException("Unsupported export format " + format + "!");
        }
    }
}
