package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RouteException extends RuntimeException {
    private static final Method a;
    private IOException lastException;

    static {
        Method method;
        try {
            method = Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class});
        } catch (Exception unused) {
            method = null;
        }
        a = method;
    }

    public RouteException(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }

    public IOException getLastConnectException() {
        return this.lastException;
    }

    public void addConnectException(IOException iOException) {
        a(iOException, this.lastException);
        this.lastException = iOException;
    }

    private void a(IOException iOException, IOException iOException2) {
        if (a != null) {
            try {
                a.invoke(iOException, new Object[]{iOException2});
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }
}
