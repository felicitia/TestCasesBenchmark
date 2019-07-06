package retrofit2;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class HttpException extends RuntimeException {
    private final transient l<?> a;
    private final int code;
    private final String message;

    private static String a(l<?> lVar) {
        o.a(lVar, "response == null");
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP ");
        sb.append(lVar.a());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(lVar.b());
        return sb.toString();
    }

    public HttpException(l<?> lVar) {
        super(a(lVar));
        this.code = lVar.a();
        this.message = lVar.b();
        this.a = lVar;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public l<?> response() {
        return this.a;
    }
}
