package com.facebook.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.f;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/* compiled from: NativeAppCallAttachmentStore */
public final class u {
    private static final String a = "com.facebook.internal.u";
    private static File b;

    /* compiled from: NativeAppCallAttachmentStore */
    public static final class a {
        /* access modifiers changed from: private */
        public final UUID a;
        private final String b;
        /* access modifiers changed from: private */
        public final String c;
        /* access modifiers changed from: private */
        public Bitmap d;
        /* access modifiers changed from: private */
        public Uri e;
        /* access modifiers changed from: private */
        public boolean f;
        /* access modifiers changed from: private */
        public boolean g;

        private a(UUID uuid, Bitmap bitmap, Uri uri) {
            String str;
            this.a = uuid;
            this.d = bitmap;
            this.e = uri;
            boolean z = true;
            if (uri != null) {
                String scheme = uri.getScheme();
                if (ResponseConstants.CONTENT.equalsIgnoreCase(scheme)) {
                    this.f = true;
                    if (uri.getAuthority() == null || uri.getAuthority().startsWith(ResponseConstants.MEDIA)) {
                        z = false;
                    }
                    this.g = z;
                } else if (ResponseConstants.FILE.equalsIgnoreCase(uri.getScheme())) {
                    this.g = true;
                } else if (!z.b(uri)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported scheme for media Uri : ");
                    sb.append(scheme);
                    throw new FacebookException(sb.toString());
                }
            } else if (bitmap != null) {
                this.g = true;
            } else {
                throw new FacebookException("Cannot share media without a bitmap or Uri set");
            }
            this.c = !this.g ? null : UUID.randomUUID().toString();
            if (!this.g) {
                str = this.e.toString();
            } else {
                str = FacebookContentProvider.getAttachmentUrl(f.j(), uuid, this.c);
            }
            this.b = str;
        }

        public String a() {
            return this.b;
        }

        public Uri b() {
            return this.e;
        }
    }

    private u() {
    }

    public static a a(UUID uuid, Bitmap bitmap) {
        aa.a((Object) uuid, "callId");
        aa.a((Object) bitmap, "attachmentBitmap");
        return new a(uuid, bitmap, null);
    }

    public static a a(UUID uuid, Uri uri) {
        aa.a((Object) uuid, "callId");
        aa.a((Object) uri, "attachmentUri");
        return new a(uuid, null, uri);
    }

    private static void a(Bitmap bitmap, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            z.a((Closeable) fileOutputStream);
        }
    }

    private static void a(Uri uri, boolean z, File file) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (!z) {
            try {
                inputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                z.a((Closeable) fileOutputStream);
                throw th;
            }
        } else {
            inputStream = f.f().getContentResolver().openInputStream(uri);
        }
        z.a(inputStream, (OutputStream) fileOutputStream);
        z.a((Closeable) fileOutputStream);
    }

    public static void a(Collection<a> collection) {
        if (collection != null && collection.size() != 0) {
            if (b == null) {
                c();
            }
            b();
            ArrayList<File> arrayList = new ArrayList<>();
            try {
                for (a aVar : collection) {
                    if (aVar.g) {
                        File a2 = a(aVar.a, aVar.c, true);
                        arrayList.add(a2);
                        if (aVar.d != null) {
                            a(aVar.d, a2);
                        } else if (aVar.e != null) {
                            a(aVar.e, aVar.f, a2);
                        }
                    }
                }
            } catch (IOException e) {
                String str = a;
                StringBuilder sb = new StringBuilder();
                sb.append("Got unexpected exception:");
                sb.append(e);
                Log.e(str, sb.toString());
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception unused) {
                    }
                }
                throw new FacebookException((Throwable) e);
            }
        }
    }

    public static void a(UUID uuid) {
        File a2 = a(uuid, false);
        if (a2 != null) {
            z.a(a2);
        }
    }

    public static File a(UUID uuid, String str) throws FileNotFoundException {
        if (z.a(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return a(uuid, str, false);
        } catch (IOException unused) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File a() {
        File file;
        synchronized (u.class) {
            if (b == null) {
                b = new File(f.f().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = b;
        }
        return file;
    }

    static File b() {
        File a2 = a();
        a2.mkdirs();
        return a2;
    }

    static File a(UUID uuid, boolean z) {
        if (b == null) {
            return null;
        }
        File file = new File(b, uuid.toString());
        if (z && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    static File a(UUID uuid, String str, boolean z) throws IOException {
        File a2 = a(uuid, z);
        if (a2 == null) {
            return null;
        }
        try {
            return new File(a2, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static void c() {
        z.a(a());
    }
}
