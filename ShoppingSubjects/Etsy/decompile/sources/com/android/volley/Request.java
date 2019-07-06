package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache.Entry;
import com.android.volley.Response.ErrorListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 3000;
    private Entry mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private final ErrorListener mErrorListener;
    /* access modifiers changed from: private */
    public final MarkerLog mEventLog;
    private final int mMethod;
    private long mRequestBirthTime;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private String mTagHashcode;
    private final String mUrl;

    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    /* access modifiers changed from: protected */
    public abstract void deliverResponse(T t);

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getParamsEncoding() {
        return "UTF-8";
    }

    /* access modifiers changed from: protected */
    public VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    @Deprecated
    public Request(String str, ErrorListener errorListener) {
        this(-1, str, errorListener);
    }

    public Request(int i, String str, ErrorListener errorListener) {
        this.mEventLog = MarkerLog.ENABLED ? new MarkerLog() : null;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mRequestBirthTime = 0;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = errorListener;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mTagHashcode = "";
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Request<?> setTag(Object obj) {
        this.mTagHashcode = createTag(obj);
        return this;
    }

    public Request<?> setTag(String str) {
        if (str == null) {
            this.mTagHashcode = "";
        } else {
            this.mTagHashcode = str;
        }
        return this;
    }

    public static String createTag(Object obj) {
        if (obj == null) {
            return "";
        }
        return String.format(Locale.US, "%s.%d", new Object[]{obj.getClass().getSimpleName(), Integer.valueOf(obj.hashCode())});
    }

    public String getTag() {
        return this.mTagHashcode;
    }

    public ErrorListener getErrorListener() {
        return this.mErrorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public void addMarker(String str) {
        if (MarkerLog.ENABLED) {
            this.mEventLog.add(str, Thread.currentThread().getId());
        } else if (this.mRequestBirthTime == 0) {
            this.mRequestBirthTime = SystemClock.elapsedRealtime();
        }
    }

    /* access modifiers changed from: 0000 */
    public void finish(final String str) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.finish(this);
        }
        if (MarkerLog.ENABLED) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        Request.this.mEventLog.add(str, id);
                        Request.this.mEventLog.finish(toString());
                    }
                });
            } else {
                this.mEventLog.add(str, id);
                this.mEventLog.finish(toString());
            }
        } else {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.mRequestBirthTime;
            if (elapsedRealtime >= SLOW_REQUEST_THRESHOLD_MS) {
                VolleyLog.d("%d ms: %s", Long.valueOf(elapsedRealtime), toString());
            }
        }
    }

    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    public final int getSequence() {
        if (this.mSequence != null) {
            return this.mSequence.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(Entry entry) {
        this.mCacheEntry = entry;
        return this;
    }

    public Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Map<String, String> getPostParams() throws AuthFailureError {
        return getParams();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding());
    }

    public String getBodyContentType() {
        StringBuilder sb = new StringBuilder();
        sb.append("application/x-www-form-urlencoded; charset=");
        sb.append(getParamsEncoding());
        return sb.toString();
    }

    public byte[] getBody() throws AuthFailureError {
        Map params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry entry : map.entrySet()) {
                sb.append(URLEncoder.encode((String) entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Encoding not supported: ");
            sb2.append(str);
            throw new RuntimeException(sb2.toString(), e);
        }
    }

    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.getCurrentTimeout();
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    public void deliverError(VolleyError volleyError) {
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(volleyError);
        }
    }

    public int compareTo(Request<T> request) {
        Priority priority = getPriority();
        Priority priority2 = request.getPriority();
        if (priority == priority2) {
            return this.mSequence.intValue() - request.mSequence.intValue();
        }
        return priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        sb.append(Integer.toHexString(getTrafficStatsTag()));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.mCanceled ? "[X] " : "[ ] ");
        sb3.append(getUrl());
        sb3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb3.append(sb2);
        sb3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb3.append(getPriority());
        sb3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb3.append(this.mSequence);
        return sb3.toString();
    }
}
