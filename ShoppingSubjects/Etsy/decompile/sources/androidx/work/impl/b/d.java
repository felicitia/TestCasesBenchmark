package androidx.work.impl.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SystemIdInfo */
public class d {
    @NonNull
    public final String a;
    public final int b;

    public d(@NonNull String str, int i) {
        this.a = str;
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.b != dVar.b) {
            return false;
        }
        return this.a.equals(dVar.a);
    }

    public int hashCode() {
        return (31 * this.a.hashCode()) + this.b;
    }
}
