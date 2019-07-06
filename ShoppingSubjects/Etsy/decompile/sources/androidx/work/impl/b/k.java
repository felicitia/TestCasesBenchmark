package androidx.work.impl.b;

import android.support.annotation.NonNull;
import androidx.work.Data;
import androidx.work.State;
import androidx.work.impl.b.j.a;
import java.util.List;

/* compiled from: WorkSpecDao */
public interface k {
    int a(State state, String... strArr);

    List<String> a();

    List<j> a(int i);

    void a(j jVar);

    void a(String str);

    void a(String str, long j);

    void a(String str, Data data);

    int b();

    int b(@NonNull String str, long j);

    j b(String str);

    List<a> c(String str);

    int d(String str);

    int e(String str);

    State f(String str);

    List<Data> g(String str);

    List<String> h(@NonNull String str);

    List<String> i(@NonNull String str);
}
