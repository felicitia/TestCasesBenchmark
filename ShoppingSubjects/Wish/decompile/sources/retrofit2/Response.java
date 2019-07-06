package retrofit2;

import okhttp3.ResponseBody;

public final class Response<T> {
    private final T body;
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;

    public static <T> Response<T> success(T t, okhttp3.Response response) {
        if (response == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (response.isSuccessful()) {
            return new Response<>(response, t, null);
        } else {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
    }

    public static <T> Response<T> error(ResponseBody responseBody, okhttp3.Response response) {
        if (responseBody == null) {
            throw new NullPointerException("body == null");
        } else if (response == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (!response.isSuccessful()) {
            return new Response<>(response, null, responseBody);
        } else {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
    }

    private Response(okhttp3.Response response, T t, ResponseBody responseBody) {
        this.rawResponse = response;
        this.body = t;
        this.errorBody = responseBody;
    }

    public int code() {
        return this.rawResponse.code();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    public T body() {
        return this.body;
    }

    public ResponseBody errorBody() {
        return this.errorBody;
    }
}
