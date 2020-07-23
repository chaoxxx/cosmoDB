package fun.cosmo.exception;

import java.lang.Exception;

/** Generic database exception class */
public class DbException extends Exception {
    private static final long serialVersionUID = 1L;

    public DbException(String s) {
        super(s);
    }

    public DbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbException(Throwable cause) {
        super(cause);
    }

    public DbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public DbException() {
    }
}
