package com.facebook.share.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.internal.ab;
import com.facebook.internal.z;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VideoUploader */
public class l {
    private static Handler a;
    private static ab b = new ab(8);
    private static Set<d> c = new HashSet();

    /* compiled from: VideoUploader */
    private static class a extends e {
        static final Set<Integer> a = new VideoUploader$FinishUploadWorkItem$1();

        public a(d dVar, int i) {
            super(dVar, i);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            if (this.b.n != null) {
                bundle.putAll(this.b.n);
            }
            bundle.putString("upload_phase", "finish");
            bundle.putString("upload_session_id", this.b.g);
            z.a(bundle, "title", this.b.a);
            z.a(bundle, "description", this.b.b);
            z.a(bundle, "ref", this.b.c);
            return bundle;
        }

        /* access modifiers changed from: protected */
        public void a(JSONObject jSONObject) throws JSONException {
            if (jSONObject.getBoolean("success")) {
                a(null, this.b.h);
            } else {
                a(new FacebookException("Unexpected error in server response"));
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookException facebookException) {
            l.b((Exception) facebookException, "Video '%s' failed to finish uploading", this.b.h);
            b(facebookException);
        }

        /* access modifiers changed from: protected */
        public Set<Integer> b() {
            return a;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            l.d(this.b, i);
        }
    }

    /* compiled from: VideoUploader */
    private static class b extends e {
        static final Set<Integer> a = new VideoUploader$StartUploadWorkItem$1();

        public b(d dVar, int i) {
            super(dVar, i);
        }

        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putString("upload_phase", "start");
            bundle.putLong("file_size", this.b.j);
            return bundle;
        }

        /* access modifiers changed from: protected */
        public void a(JSONObject jSONObject) throws JSONException {
            this.b.g = jSONObject.getString("upload_session_id");
            this.b.h = jSONObject.getString(ResponseConstants.VIDEO_ID);
            l.b(this.b, jSONObject.getString("start_offset"), jSONObject.getString("end_offset"), 0);
        }

        /* access modifiers changed from: protected */
        public void a(FacebookException facebookException) {
            l.b((Exception) facebookException, "Error starting video upload", new Object[0]);
            b(facebookException);
        }

        /* access modifiers changed from: protected */
        public Set<Integer> b() {
            return a;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            l.c(this.b, i);
        }
    }

    /* compiled from: VideoUploader */
    private static class c extends e {
        static final Set<Integer> a = new VideoUploader$TransferChunkWorkItem$1();
        private String d;
        private String e;

        public c(d dVar, String str, String str2, int i) {
            super(dVar, i);
            this.d = str;
            this.e = str2;
        }

        public Bundle a() throws IOException {
            Bundle bundle = new Bundle();
            bundle.putString("upload_phase", "transfer");
            bundle.putString("upload_session_id", this.b.g);
            bundle.putString("start_offset", this.d);
            byte[] a2 = l.b(this.b, this.d, this.e);
            if (a2 != null) {
                bundle.putByteArray("video_file_chunk", a2);
                return bundle;
            }
            throw new FacebookException("Error reading video");
        }

        /* access modifiers changed from: protected */
        public void a(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("start_offset");
            String string2 = jSONObject.getString("end_offset");
            if (z.a(string, string2)) {
                l.d(this.b, 0);
            } else {
                l.b(this.b, string, string2, 0);
            }
        }

        /* access modifiers changed from: protected */
        public void a(FacebookException facebookException) {
            l.b((Exception) facebookException, "Error uploading video '%s'", this.b.h);
            b(facebookException);
        }

        /* access modifiers changed from: protected */
        public Set<Integer> b() {
            return a;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            l.b(this.b, this.d, this.e, i);
        }
    }

    /* compiled from: VideoUploader */
    private static class d {
        public final String a;
        public final String b;
        public final String c;
        public final String d;
        public final AccessToken e;
        public final com.facebook.e<com.facebook.share.c.a> f;
        public String g;
        public String h;
        public InputStream i;
        public long j;
        public String k;
        public boolean l;
        public com.facebook.internal.ab.a m;
        public Bundle n;
    }

    /* compiled from: VideoUploader */
    private static abstract class e implements Runnable {
        protected d b;
        protected int c;

        /* access modifiers changed from: protected */
        public abstract Bundle a() throws Exception;

        /* access modifiers changed from: protected */
        public abstract void a(int i);

        /* access modifiers changed from: protected */
        public abstract void a(FacebookException facebookException);

        /* access modifiers changed from: protected */
        public abstract void a(JSONObject jSONObject) throws JSONException;

        /* access modifiers changed from: protected */
        public abstract Set<Integer> b();

        protected e(d dVar, int i) {
            this.b = dVar;
            this.c = i;
        }

        public void run() {
            if (!this.b.l) {
                try {
                    a(a());
                } catch (FacebookException e) {
                    b(e);
                } catch (Exception e2) {
                    b(new FacebookException("Video upload failed", (Throwable) e2));
                }
            } else {
                b((FacebookException) null);
            }
        }

        /* access modifiers changed from: protected */
        public void a(Bundle bundle) {
            Bundle bundle2 = bundle;
            GraphRequest graphRequest = new GraphRequest(this.b.e, String.format(Locale.ROOT, "%s/videos", new Object[]{this.b.d}), bundle2, HttpMethod.POST, null);
            GraphResponse i = graphRequest.i();
            if (i != null) {
                FacebookRequestError a = i.a();
                JSONObject b2 = i.b();
                if (a != null) {
                    if (!b(a.getSubErrorCode())) {
                        a((FacebookException) new FacebookGraphResponseException(i, "Video upload failed"));
                    }
                } else if (b2 != null) {
                    try {
                        a(b2);
                    } catch (JSONException e) {
                        b(new FacebookException("Unexpected error in server response", (Throwable) e));
                    }
                } else {
                    a(new FacebookException("Unexpected error in server response"));
                }
            } else {
                a(new FacebookException("Unexpected error in server response"));
            }
        }

        private boolean b(int i) {
            if (this.c >= 2 || !b().contains(Integer.valueOf(i))) {
                return false;
            }
            l.b().postDelayed(new Runnable() {
                public void run() {
                    e.this.a(e.this.c + 1);
                }
            }, (long) (5000 * ((int) Math.pow(3.0d, (double) this.c))));
            return true;
        }

        /* access modifiers changed from: protected */
        public void b(FacebookException facebookException) {
            a(facebookException, null);
        }

        /* access modifiers changed from: protected */
        public void a(final FacebookException facebookException, final String str) {
            l.b().post(new Runnable() {
                public void run() {
                    l.b(e.this.b, facebookException, str);
                }
            });
        }
    }

    private static synchronized void a(d dVar) {
        synchronized (l.class) {
            c.remove(dVar);
        }
    }

    /* access modifiers changed from: private */
    public static synchronized Handler b() {
        Handler handler;
        synchronized (l.class) {
            if (a == null) {
                a = new Handler(Looper.getMainLooper());
            }
            handler = a;
        }
        return handler;
    }

    /* access modifiers changed from: private */
    public static void b(d dVar, FacebookException facebookException, String str) {
        a(dVar);
        z.a((Closeable) dVar.i);
        if (dVar.f == null) {
            return;
        }
        if (facebookException != null) {
            k.a(dVar.f, facebookException);
        } else if (dVar.l) {
            k.b(dVar.f);
        } else {
            k.a(dVar.f, str);
        }
    }

    /* access modifiers changed from: private */
    public static void c(d dVar, int i) {
        a(dVar, (Runnable) new b(dVar, i));
    }

    /* access modifiers changed from: private */
    public static void b(d dVar, String str, String str2, int i) {
        a(dVar, (Runnable) new c(dVar, str, str2, i));
    }

    /* access modifiers changed from: private */
    public static void d(d dVar, int i) {
        a(dVar, (Runnable) new a(dVar, i));
    }

    private static synchronized void a(d dVar, Runnable runnable) {
        synchronized (l.class) {
            dVar.m = b.a(runnable);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] b(d dVar, String str, String str2) throws IOException {
        int read;
        if (!z.a(str, dVar.k)) {
            b((Exception) null, "Error reading video chunk. Expected chunk '%s'. Requested chunk '%s'.", dVar.k, str);
            return null;
        }
        int parseLong = (int) (Long.parseLong(str2) - Long.parseLong(str));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Math.min(8192, parseLong)];
        do {
            read = dVar.i.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
                parseLong -= read;
                if (parseLong == 0) {
                }
            }
            dVar.k = str2;
            return byteArrayOutputStream.toByteArray();
        } while (parseLong >= 0);
        b((Exception) null, "Error reading video chunk. Expected buffer length - '%d'. Actual - '%d'.", Integer.valueOf(parseLong + read), Integer.valueOf(read));
        return null;
    }

    /* access modifiers changed from: private */
    public static void b(Exception exc, String str, Object... objArr) {
        Log.e("VideoUploader", String.format(Locale.ROOT, str, objArr), exc);
    }
}
