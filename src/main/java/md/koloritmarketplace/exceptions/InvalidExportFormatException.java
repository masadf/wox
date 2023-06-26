package md.koloritmarketplace.exceptions;

/**
 * Created by rsv on 16.03.2016.
 */
public class InvalidExportFormatException extends ReportException {
    public InvalidExportFormatException(String message) {
        super(message);
    }

    public InvalidExportFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
