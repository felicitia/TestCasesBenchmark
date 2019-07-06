package android.arch.lifecycle;

import java.util.HashMap;

/* compiled from: ViewModelStore */
public class l {
    private final HashMap<String, k> a = new HashMap<>();

    public final void a() {
        for (k onCleared : this.a.values()) {
            onCleared.onCleared();
        }
        this.a.clear();
    }
}
