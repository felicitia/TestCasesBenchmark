package androidx.work;

import android.support.annotation.NonNull;
import androidx.work.Data.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OverwritingInputMerger extends d {
    @NonNull
    public Data a(@NonNull List<Data> list) {
        a aVar = new a();
        HashMap hashMap = new HashMap();
        for (Data a : list) {
            hashMap.putAll(a.a());
        }
        aVar.a((Map<String, Object>) hashMap);
        return aVar.a();
    }
}
