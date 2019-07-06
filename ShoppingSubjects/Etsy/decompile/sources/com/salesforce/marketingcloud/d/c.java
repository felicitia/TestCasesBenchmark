package com.salesforce.marketingcloud.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import com.salesforce.marketingcloud.j;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Locale;

public interface c {

    public static class a implements c {
        private static final String a = j.a(c.class);
        private final SharedPreferences b;
        private final com.salesforce.marketingcloud.e.a c;

        a(@NonNull Context context, @NonNull com.salesforce.marketingcloud.e.a aVar, @NonNull String str) {
            this.b = context.getSharedPreferences(b(str), 0);
            this.c = aVar;
        }

        static String b(String str) {
            return String.format(Locale.ENGLISH, "mcsdk_custprefs_%s", new Object[]{str});
        }

        private void c(@Size(min = 1) @NonNull String str, @NonNull String str2) {
            this.b.edit().putString(str, this.c.a(str2)).apply();
        }

        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
        @android.support.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String d(@android.support.annotation.Size(min = 1) @android.support.annotation.NonNull java.lang.String r7, java.lang.String r8) {
            /*
                r6 = this;
                android.content.SharedPreferences r0 = r6.b
                r1 = 0
                java.lang.String r0 = r0.getString(r7, r1)
                if (r0 == 0) goto L_0x001e
                com.salesforce.marketingcloud.e.a r2 = r6.c     // Catch:{ Exception -> 0x0010 }
                java.lang.String r0 = r2.b(r0)     // Catch:{ Exception -> 0x0010 }
                goto L_0x001f
            L_0x0010:
                r0 = move-exception
                java.lang.String r2 = a
                java.lang.String r3 = "Failed to encrypt %s"
                r4 = 1
                java.lang.Object[] r4 = new java.lang.Object[r4]
                r5 = 0
                r4[r5] = r7
                com.salesforce.marketingcloud.j.b(r2, r0, r3, r4)
            L_0x001e:
                r0 = r1
            L_0x001f:
                if (r0 != 0) goto L_0x0022
                return r8
            L_0x0022:
                r8 = r0
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.d.c.a.d(java.lang.String, java.lang.String):java.lang.String");
        }

        public final void a() {
            this.b.edit().clear().apply();
        }

        public final void a(@Size(min = 1) @NonNull String str) {
            this.b.edit().remove(str).apply();
        }

        public final void a(String str, @NonNull String str2) {
            try {
                c(str, str2);
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                j.d(a, String.format(Locale.ENGLISH, "Value for key %s not stored.", new Object[]{str}), e);
            }
        }

        @Nullable
        public final String b(String str, String str2) {
            return d(str, str2);
        }
    }

    void a();

    void a(String str);

    void a(String str, @NonNull String str2);

    @Nullable
    String b(String str, String str2);
}
