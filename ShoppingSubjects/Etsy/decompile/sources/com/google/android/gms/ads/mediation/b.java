package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;

public interface b {

    public static class a {
        private int a;

        public final Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putInt(ResponseConstants.CAPABILITIES, this.a);
            return bundle;
        }

        public final a a(int i) {
            this.a = 1;
            return this;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
