package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;

public class ImageRequest extends Request<Bitmap> {
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final int IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock = new Object();
    private final Config mDecodeConfig;
    private final Listener<Bitmap> mListener;
    private final int mMaxHeight;
    private final int mMaxWidth;

    private static int getResizedDimension(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            return (int) (((double) i3) * (((double) i2) / ((double) i4)));
        } else if (i2 == 0) {
            return i;
        } else {
            double d = ((double) i4) / ((double) i3);
            double d2 = (double) i2;
            if (((double) i) * d > d2) {
                i = (int) (d2 / d);
            }
            return i;
        }
    }

    public ImageRequest(String str, Listener<Bitmap> listener, int i, int i2, Config config, ErrorListener errorListener) {
        super(0, str, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(1000, 2, IMAGE_BACKOFF_MULT));
        this.mListener = listener;
        this.mDecodeConfig = config;
        this.mMaxWidth = i;
        this.mMaxHeight = i2;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public Response<Bitmap> parseNetworkResponse(NetworkResponse networkResponse) {
        Response<Bitmap> doParse;
        synchronized (sDecodeLock) {
            try {
                doParse = doParse(networkResponse);
            } catch (OutOfMemoryError e) {
                VolleyLog.e("Caught OOM for %d byte image, url=%s", Integer.valueOf(networkResponse.data.length), getUrl());
                return Response.error(new ParseError((Throwable) e));
            } catch (Throwable th) {
                throw th;
            }
        }
        return doParse;
    }

    private Response<Bitmap> doParse(NetworkResponse networkResponse) {
        Bitmap bitmap;
        byte[] bArr = networkResponse.data;
        Options options = new Options();
        options.inPreferredConfig = this.mDecodeConfig;
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int resizedDimension = getResizedDimension(this.mMaxWidth, this.mMaxHeight, i, i2);
            int resizedDimension2 = getResizedDimension(this.mMaxHeight, this.mMaxWidth, i2, i);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i, i2, resizedDimension, resizedDimension2);
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (bitmap != null && (bitmap.getWidth() > resizedDimension || bitmap.getHeight() > resizedDimension2)) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, resizedDimension, resizedDimension2, true);
                bitmap.recycle();
                bitmap = createScaledBitmap;
            }
        }
        if (bitmap == null) {
            return Response.error(new ParseError(networkResponse));
        }
        return Response.success(bitmap, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(Bitmap bitmap) {
        this.mListener.onResponse(bitmap);
    }

    static int findBestSampleSize(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = 1.0f;
        while (true) {
            float f2 = IMAGE_BACKOFF_MULT * f;
            if (((double) f2) > min) {
                return (int) f;
            }
            f = f2;
        }
    }
}
