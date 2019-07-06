package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: MethodCallsLogger */
public class g {
    private Map<String, Integer> a = new HashMap();

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean a(String str, int i) {
        Integer num = (Integer) this.a.get(str);
        boolean z = false;
        int intValue = num != null ? num.intValue() : 0;
        if ((intValue & i) != 0) {
            z = true;
        }
        this.a.put(str, Integer.valueOf(i | intValue));
        return !z;
    }
}
