package com.crittercism.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.crittercism.internal.bi;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;

public final class az<T extends bi> implements ay<T> {
    private final a a;
    private b<T> b;
    private int c;
    private final List<com.crittercism.internal.ay.a> d = new LinkedList();

    static class a {
        private Context a;
        private String b;
        private File c;

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public final File a() {
            if (this.c != null) {
                return this.c;
            }
            this.c = az.a(this.a, this.b);
            if (!this.c.isDirectory()) {
                this.c.mkdirs();
            }
            return this.c;
        }
    }

    public interface b<T extends bi> {
        T a(File file);

        void a(T t, OutputStream outputStream);
    }

    public az(Context context, String str, b<T> bVar, int i) {
        this.b = bVar;
        this.c = i;
        this.a = new a(context, str);
    }

    private boolean b(T t) {
        StringBuilder sb;
        File file = new File(this.a.a(), t.f());
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                this.b.a(t, bufferedOutputStream);
                try {
                    return true;
                } catch (IOException e) {
                    e = e;
                    file.delete();
                    sb = new StringBuilder("Unable to close ");
                    sb.append(file.getAbsolutePath());
                    cm.a(sb.toString(), (Throwable) e);
                    return false;
                }
            } catch (Exception e2) {
                file.delete();
                StringBuilder sb2 = new StringBuilder("Unable to write to ");
                sb2.append(file.getAbsolutePath());
                cm.a(sb2.toString(), (Throwable) e2);
                try {
                    return false;
                } catch (IOException e3) {
                    e = e3;
                    file.delete();
                    sb = new StringBuilder("Unable to close ");
                    sb.append(file.getAbsolutePath());
                    cm.a(sb.toString(), (Throwable) e);
                    return false;
                }
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e4) {
                    e = e4;
                    file.delete();
                    sb = new StringBuilder("Unable to close ");
                    sb.append(file.getAbsolutePath());
                    cm.a(sb.toString(), (Throwable) e);
                    return false;
                }
            }
        } catch (FileNotFoundException e5) {
            StringBuilder sb3 = new StringBuilder("Could not open output stream to : ");
            sb3.append(file);
            cm.c(sb3.toString(), e5);
            return false;
        }
    }

    private File[] e() {
        File[] f = f();
        Arrays.sort(f);
        return f;
    }

    private File[] f() {
        File[] listFiles = this.a.a().listFiles();
        return listFiles == null ? new File[0] : listFiles;
    }

    public final void a(@NonNull String str) {
        File file = new File(this.a.a(), str);
        if (file.exists()) {
            file.delete();
        }
    }

    public final JSONArray a() {
        List<bi> b2 = b();
        JSONArray jSONArray = new JSONArray();
        for (bi g : b2) {
            jSONArray.put(g.g());
        }
        return jSONArray;
    }

    public final List<T> b() {
        File[] e;
        ArrayList arrayList = new ArrayList();
        for (File file : e()) {
            try {
                arrayList.add(this.b.a(file));
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable unused) {
                a(file.getName());
            }
        }
        return arrayList;
    }

    public final List<T> d() {
        return b();
    }

    public final void a(List<T> list) {
        for (T f : list) {
            a(f.f());
        }
    }

    public final void a(com.crittercism.internal.ay.a aVar) {
        if (aVar != null) {
            synchronized (this.d) {
                this.d.add(aVar);
            }
        }
    }

    public static File a(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir());
        sb.append("/com.crittercism/");
        sb.append(str);
        return new File(sb.toString());
    }

    public final boolean a(T t) {
        if (f().length >= this.c) {
            File[] e = e();
            boolean z = true;
            int length = (this.c - e.length) + 1;
            int i = 0;
            int i2 = 0;
            while (i < length && i < e.length) {
                if (e[i].delete()) {
                    i2++;
                }
                i++;
            }
            if (i2 != length) {
                z = false;
            }
            if (!z) {
                return false;
            }
        }
        boolean b2 = b(t);
        synchronized (this.d) {
            for (com.crittercism.internal.ay.a a2 : this.d) {
                a2.a();
            }
        }
        return b2;
    }

    public final boolean c() {
        return f().length > 0;
    }
}
