package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.measurement.zzcl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HitBuilders {

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }
    }

    public static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> map = new HashMap();
        private ProductAction zzri;
        private Map<String, List<Product>> zzrj = new HashMap();
        private List<Promotion> zzrk = new ArrayList();
        private List<Product> zzrl = new ArrayList();

        protected HitBuilder() {
        }

        public Map<String, String> build() {
            HashMap hashMap = new HashMap(this.map);
            if (this.zzri != null) {
                hashMap.putAll(this.zzri.build());
            }
            int i = 1;
            for (Promotion zzn : this.zzrk) {
                hashMap.putAll(zzn.zzn(zzd.zzh(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzn2 : this.zzrl) {
                hashMap.putAll(zzn2.zzn(zzd.zzf(i2)));
                i2++;
            }
            int i3 = 1;
            for (Entry entry : this.zzrj.entrySet()) {
                List<Product> list = (List) entry.getValue();
                String zzk = zzd.zzk(i3);
                int i4 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzk);
                    String valueOf2 = String.valueOf(zzd.zzj(i4));
                    hashMap.putAll(product.zzn(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    String valueOf3 = String.valueOf(zzk);
                    String valueOf4 = String.valueOf("nm");
                    hashMap.put(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), (String) entry.getKey());
                }
                i3++;
            }
            return hashMap;
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.map.put(str, str2);
                return this;
            }
            zzcl.zzab("HitBuilder.set() called with a null paramName.");
            return this;
        }

        public T setNewSession() {
            set("&sc", "start");
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }
    }
}
