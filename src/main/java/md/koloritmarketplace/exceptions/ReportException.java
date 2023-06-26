package md.koloritmarketplace.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rsv on 16.03.2016.
 */
public class ReportException extends Exception {
    private final Logger logger = LoggerFactory.getLogger(ReportException.class);

    public ReportException(String message) {
        super(message);
        logger.warn("message:" + message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
        logger.warn("message:" + message + ";" + "cause:" + cause.getMessage());
    }
}
