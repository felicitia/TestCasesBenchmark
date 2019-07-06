package androidx.work.impl;

import android.net.Uri;
import androidx.work.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Extras {
    private Data mInputData;
    private RuntimeExtras mRuntimeExtras;
    private Set<String> mTags;

    public static class RuntimeExtras {
        public String[] triggeredContentAuthorities;
        public Uri[] triggeredContentUris;
    }

    public Extras(Data data, List<String> list, RuntimeExtras runtimeExtras) {
        this.mInputData = data;
        this.mTags = new HashSet(list);
        this.mRuntimeExtras = runtimeExtras;
    }

    public Data getInputData() {
        return this.mInputData;
    }
}
