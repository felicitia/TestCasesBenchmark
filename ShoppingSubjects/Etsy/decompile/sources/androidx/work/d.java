package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;

/* compiled from: InputMerger */
public abstract class d {
    @NonNull
    public abstract Data a(@NonNull List<Data> list);

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static d a(String str) {
        try {
            return (d) Class.forName(str).newInstance();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble instantiating + ");
            sb.append(str);
            e.e("InputMerger", sb.toString(), e);
            return null;
        }
    }
}
