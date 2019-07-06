package com.salesforce.marketingcloud.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.e.a;
import com.salesforce.marketingcloud.j;
import java.io.File;
import java.util.Locale;

public abstract class b extends d {
    private static final String d = j.a(b.class);
    protected final String a;
    protected final Context b;
    protected final a c;

    b(@NonNull Context context, @NonNull a aVar, @NonNull String str, @NonNull String str2) {
        super(str, str2);
        this.b = context;
        this.c = aVar;
        this.a = str;
        if (!h() && g() && f()) {
            try {
                i();
            } catch (Exception e) {
                j.c(d, e, "Unable to migrate data to BU specific storage", new Object[0]);
            }
        }
    }

    static String a(String str) {
        return String.format(Locale.ENGLISH, "mcsdk_%s", new Object[]{str});
    }

    private boolean f() {
        return a(this.b.getSharedPreferences("ETSharedPrefs", 0));
    }

    private boolean g() {
        File databasePath = this.b.getDatabasePath("storagedb.db");
        return databasePath != null && databasePath.exists();
    }

    private boolean h() {
        File databasePath = this.b.getDatabasePath(com.salesforce.marketingcloud.d.a.j.a(this.a));
        return databasePath != null && databasePath.exists();
    }

    private void i() {
        File databasePath = this.b.getDatabasePath("storagedb.db");
        if (databasePath != null && databasePath.exists()) {
            try {
                if (!databasePath.renameTo(new File(databasePath.getParent(), com.salesforce.marketingcloud.d.a.j.a(this.a)))) {
                    j.e(d, "Unable to rename storagedb.db to BU specific naming scheme", new Object[0]);
                }
            } catch (Exception e) {
                j.c(d, e, "Unable to rename storagedb.db to BU specific naming scheme", new Object[0]);
            }
        }
        File file = new File(this.b.getApplicationInfo().dataDir, "shared_prefs/");
        if (file != null && file.exists()) {
            File file2 = new File(file, "ETCustomerPrefs.xml");
            if (file2 != null && file2.exists()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c.a.b(this.a));
                    sb.append(".xml");
                    if (!file2.renameTo(new File(file, sb.toString()))) {
                        j.e(d, "Unable to rename ETCustomerPrefs.xml to BU specific naming scheme", new Object[0]);
                    }
                } catch (Exception e2) {
                    j.c(d, e2, "Unable to rename ETCustomerPrefs.xml to BU specific naming scheme", new Object[0]);
                }
            }
            File file3 = new File(file, "ETSharedPrefs.xml");
            if (file3 != null && file3.exists()) {
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(a(this.a));
                    sb2.append(".xml");
                    if (!file3.renameTo(new File(file, sb2.toString()))) {
                        j.e(d, "Unable to rename ETSharedPrefs.xml to BU specific naming scheme", new Object[0]);
                    }
                } catch (Exception e3) {
                    j.c(d, e3, "Unable to rename ETSharedPrefs.xml to BU specific naming scheme", new Object[0]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(SharedPreferences sharedPreferences);
}
