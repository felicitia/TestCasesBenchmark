package android.arch.persistence.room.b;

import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.messaging.CartRefreshDelegate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: TableInfo */
public class b {
    public final String a;
    public final Map<String, a> b;
    public final Set<C0004b> c;
    @Nullable
    public final Set<d> d;

    /* compiled from: TableInfo */
    public static class a {
        public final String a;
        public final String b;
        public final int c;
        public final boolean d;
        public final int e;

        public a(String str, String str2, boolean z, int i) {
            this.a = str;
            this.b = str2;
            this.d = z;
            this.e = i;
            this.c = a(str2);
        }

        private static int a(@Nullable String str) {
            if (str == null) {
                return 5;
            }
            String upperCase = str.toUpperCase(Locale.US);
            if (upperCase.contains("INT")) {
                return 3;
            }
            if (upperCase.contains("CHAR") || upperCase.contains("CLOB") || upperCase.contains("TEXT")) {
                return 2;
            }
            if (upperCase.contains("BLOB")) {
                return 5;
            }
            return (upperCase.contains("REAL") || upperCase.contains("FLOA") || upperCase.contains("DOUB")) ? 4 : 1;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (VERSION.SDK_INT >= 20) {
                if (this.e != aVar.e) {
                    return false;
                }
            } else if (a() != aVar.a()) {
                return false;
            }
            if (!this.a.equals(aVar.a) || this.d != aVar.d) {
                return false;
            }
            if (this.c != aVar.c) {
                z = false;
            }
            return z;
        }

        public boolean a() {
            return this.e > 0;
        }

        public int hashCode() {
            return (31 * ((((this.a.hashCode() * 31) + this.c) * 31) + (this.d ? 1231 : 1237))) + this.e;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Column{name='");
            sb.append(this.a);
            sb.append('\'');
            sb.append(", type='");
            sb.append(this.b);
            sb.append('\'');
            sb.append(", affinity='");
            sb.append(this.c);
            sb.append('\'');
            sb.append(", notNull=");
            sb.append(this.d);
            sb.append(", primaryKeyPosition=");
            sb.append(this.e);
            sb.append('}');
            return sb.toString();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    /* renamed from: android.arch.persistence.room.b.b$b reason: collision with other inner class name */
    /* compiled from: TableInfo */
    public static class C0004b {
        @NonNull
        public final String a;
        @NonNull
        public final String b;
        @NonNull
        public final String c;
        @NonNull
        public final List<String> d;
        @NonNull
        public final List<String> e;

        public C0004b(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<String> list, @NonNull List<String> list2) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = Collections.unmodifiableList(list);
            this.e = Collections.unmodifiableList(list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0004b bVar = (C0004b) obj;
            if (this.a.equals(bVar.a) && this.b.equals(bVar.b) && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                return this.e.equals(bVar.e);
            }
            return false;
        }

        public int hashCode() {
            return (31 * ((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode())) + this.e.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ForeignKey{referenceTable='");
            sb.append(this.a);
            sb.append('\'');
            sb.append(", onDelete='");
            sb.append(this.b);
            sb.append('\'');
            sb.append(", onUpdate='");
            sb.append(this.c);
            sb.append('\'');
            sb.append(", columnNames=");
            sb.append(this.d);
            sb.append(", referenceColumnNames=");
            sb.append(this.e);
            sb.append('}');
            return sb.toString();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    /* compiled from: TableInfo */
    static class c implements Comparable<c> {
        final int a;
        final int b;
        final String c;
        final String d;

        c(int i, int i2, String str, String str2) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = str2;
        }

        /* renamed from: a */
        public int compareTo(@NonNull c cVar) {
            int i = this.a - cVar.a;
            return i == 0 ? this.b - cVar.b : i;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    /* compiled from: TableInfo */
    public static class d {
        public final String a;
        public final boolean b;
        public final List<String> c;

        public d(String str, boolean z, List<String> list) {
            this.a = str;
            this.b = z;
            this.c = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.b != dVar.b || !this.c.equals(dVar.c)) {
                return false;
            }
            if (this.a.startsWith("index_")) {
                return dVar.a.startsWith("index_");
            }
            return this.a.equals(dVar.a);
        }

        public int hashCode() {
            int i;
            if (this.a.startsWith("index_")) {
                i = "index_".hashCode();
            } else {
                i = this.a.hashCode();
            }
            return (31 * ((i * 31) + (this.b ? 1 : 0))) + this.c.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Index{name='");
            sb.append(this.a);
            sb.append('\'');
            sb.append(", unique=");
            sb.append(this.b);
            sb.append(", columns=");
            sb.append(this.c);
            sb.append('}');
            return sb.toString();
        }
    }

    public b(String str, Map<String, a> map, Set<C0004b> set, Set<d> set2) {
        Set<d> set3;
        this.a = str;
        this.b = Collections.unmodifiableMap(map);
        this.c = Collections.unmodifiableSet(set);
        if (set2 == null) {
            set3 = null;
        } else {
            set3 = Collections.unmodifiableSet(set2);
        }
        this.d = set3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a == null ? bVar.a != null : !this.a.equals(bVar.a)) {
            return false;
        }
        if (this.b == null ? bVar.b != null : !this.b.equals(bVar.b)) {
            return false;
        }
        if (this.c == null ? bVar.c != null : !this.c.equals(bVar.c)) {
            return false;
        }
        if (this.d == null || bVar.d == null) {
            return true;
        }
        return this.d.equals(bVar.d);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * (((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0));
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TableInfo{name='");
        sb.append(this.a);
        sb.append('\'');
        sb.append(", columns=");
        sb.append(this.b);
        sb.append(", foreignKeys=");
        sb.append(this.c);
        sb.append(", indices=");
        sb.append(this.d);
        sb.append('}');
        return sb.toString();
    }

    public static b a(android.arch.persistence.db.a aVar, String str) {
        return new b(str, c(aVar, str), b(aVar, str), d(aVar, str));
    }

    private static Set<C0004b> b(android.arch.persistence.db.a aVar, String str) {
        int i;
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA foreign_key_list(`");
        sb.append(str);
        sb.append("`)");
        Cursor b2 = aVar.b(sb.toString());
        try {
            int columnIndex = b2.getColumnIndex("id");
            int columnIndex2 = b2.getColumnIndex("seq");
            int columnIndex3 = b2.getColumnIndex("table");
            int columnIndex4 = b2.getColumnIndex("on_delete");
            int columnIndex5 = b2.getColumnIndex("on_update");
            List<c> a2 = a(b2);
            int count = b2.getCount();
            int i2 = 0;
            while (i2 < count) {
                b2.moveToPosition(i2);
                if (b2.getInt(columnIndex2) != 0) {
                    i = columnIndex;
                } else {
                    int i3 = b2.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (c cVar : a2) {
                        int i4 = columnIndex;
                        if (cVar.a == i3) {
                            arrayList.add(cVar.c);
                            arrayList2.add(cVar.d);
                        }
                        columnIndex = i4;
                    }
                    i = columnIndex;
                    ArrayList arrayList3 = arrayList2;
                    C0004b bVar = new C0004b(b2.getString(columnIndex3), b2.getString(columnIndex4), b2.getString(columnIndex5), arrayList, arrayList3);
                    hashSet.add(bVar);
                }
                i2++;
                columnIndex = i;
            }
            b2.close();
            return hashSet;
        } catch (Throwable th) {
            Throwable th2 = th;
            b2.close();
            throw th2;
        }
    }

    private static List<c> a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex(ResponseConstants.FROM);
        int columnIndex4 = cursor.getColumnIndex(ResponseConstants.TO);
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            arrayList.add(new c(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Map<String, a> c(android.arch.persistence.db.a aVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA table_info(`");
        sb.append(str);
        sb.append("`)");
        Cursor b2 = aVar.b(sb.toString());
        HashMap hashMap = new HashMap();
        try {
            if (b2.getColumnCount() > 0) {
                int columnIndex = b2.getColumnIndex(ResponseConstants.NAME);
                int columnIndex2 = b2.getColumnIndex("type");
                int columnIndex3 = b2.getColumnIndex("notnull");
                int columnIndex4 = b2.getColumnIndex("pk");
                while (b2.moveToNext()) {
                    String string = b2.getString(columnIndex);
                    hashMap.put(string, new a(string, b2.getString(columnIndex2), b2.getInt(columnIndex3) != 0, b2.getInt(columnIndex4)));
                }
            }
            return hashMap;
        } finally {
            b2.close();
        }
    }

    @Nullable
    private static Set<d> d(android.arch.persistence.db.a aVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA index_list(`");
        sb.append(str);
        sb.append("`)");
        Cursor b2 = aVar.b(sb.toString());
        try {
            int columnIndex = b2.getColumnIndex(ResponseConstants.NAME);
            int columnIndex2 = b2.getColumnIndex(CartRefreshDelegate.ARG_ORIGIN);
            int columnIndex3 = b2.getColumnIndex("unique");
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    HashSet hashSet = new HashSet();
                    while (b2.moveToNext()) {
                        if ("c".equals(b2.getString(columnIndex2))) {
                            String string = b2.getString(columnIndex);
                            boolean z = true;
                            if (b2.getInt(columnIndex3) != 1) {
                                z = false;
                            }
                            d a2 = a(aVar, string, z);
                            if (a2 == null) {
                                b2.close();
                                return null;
                            }
                            hashSet.add(a2);
                        }
                    }
                    b2.close();
                    return hashSet;
                }
            }
            return null;
        } finally {
            b2.close();
        }
    }

    @Nullable
    private static d a(android.arch.persistence.db.a aVar, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("PRAGMA index_xinfo(`");
        sb.append(str);
        sb.append("`)");
        Cursor b2 = aVar.b(sb.toString());
        try {
            int columnIndex = b2.getColumnIndex("seqno");
            int columnIndex2 = b2.getColumnIndex("cid");
            int columnIndex3 = b2.getColumnIndex(ResponseConstants.NAME);
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    TreeMap treeMap = new TreeMap();
                    while (b2.moveToNext()) {
                        if (b2.getInt(columnIndex2) >= 0) {
                            int i = b2.getInt(columnIndex);
                            treeMap.put(Integer.valueOf(i), b2.getString(columnIndex3));
                        }
                    }
                    ArrayList arrayList = new ArrayList(treeMap.size());
                    arrayList.addAll(treeMap.values());
                    d dVar = new d(str, z, arrayList);
                    b2.close();
                    return dVar;
                }
            }
            return null;
        } finally {
            b2.close();
        }
    }
}
