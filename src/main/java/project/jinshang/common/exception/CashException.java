package project.jinshang.common.exception;

public class CashException extends Exception{
    public CashException() {
        super();
    }

    public CashException(String message) {
        super(message);
    }

    public CashException(String message, Throwable cause) {
        super(message, cause);
    }

    public CashException(Throwable cause) {
        super(cause);
    }

    protected CashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
