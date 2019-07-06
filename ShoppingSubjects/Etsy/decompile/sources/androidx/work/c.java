package androidx.work;

import android.net.Uri;
import android.support.annotation.NonNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: ContentUriTriggers */
public final class c implements Iterable<a> {
    private final Set<a> a = new HashSet();

    /* compiled from: ContentUriTriggers */
    public static final class a {
        @NonNull
        private final Uri a;
        private final boolean b;

        a(@NonNull Uri uri, boolean z) {
            this.a = uri;
            this.b = z;
        }

        @NonNull
        public Uri a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
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
            if (this.b != aVar.b || !this.a.equals(aVar.a)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (31 * this.a.hashCode()) + (this.b ? 1 : 0);
        }
    }

    public void a(@NonNull Uri uri, boolean z) {
        this.a.add(new a(uri, z));
    }

    @NonNull
    public Iterator<a> iterator() {
        return this.a.iterator();
    }

    public int a() {
        return this.a.size();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((c) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
