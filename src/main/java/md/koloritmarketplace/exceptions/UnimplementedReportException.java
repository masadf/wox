package md.koloritmarketplace.exceptions;

/**
 * Created by rsv on 17.03.2016.
 */
public class UnimplementedReportException extends ReportException {
    public UnimplementedReportException(String message) {
        super(message);
    }

    public UnimplementedReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
