package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import java.io.UnsupportedEncodingException;

public class StringRequest extends Request<String> {
    private final Listener<String> mListener;

    public StringRequest(int i, String str, Listener<String> listener, ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
    }

    public StringRequest(String str, Listener<String> listener, ErrorListener errorListener) {
        this(0, str, listener, errorListener);
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(String str) {
        this.mListener.onResponse(str);
    }

    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String str;
        try {
            str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
        } catch (UnsupportedEncodingException unused) {
            str = new String(networkResponse.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
