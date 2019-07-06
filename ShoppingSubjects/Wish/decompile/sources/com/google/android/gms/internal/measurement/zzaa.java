package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzaa extends zzi<zzaa> {
    private ProductAction zzri;
    private final Map<String, List<Product>> zzrj = new HashMap();
    private final List<Promotion> zzrk = new ArrayList();
    private final List<Product> zzrl = new ArrayList();

    public final String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzrl.isEmpty()) {
            hashMap.put("products", this.zzrl);
        }
        if (!this.zzrk.isEmpty()) {
            hashMap.put("promotions", this.zzrk);
        }
        if (!this.zzrj.isEmpty()) {
            hashMap.put("impressions", this.zzrj);
        }
        hashMap.put("productAction", this.zzri);
        return zza((Object) hashMap);
    }

    public final ProductAction zzat() {
        return this.zzri;
    }

    public final List<Product> zzau() {
        return Collections.unmodifiableList(this.zzrl);
    }

    public final Map<String, List<Product>> zzav() {
        return this.zzrj;
    }

    public final List<Promotion> zzaw() {
        return Collections.unmodifiableList(this.zzrk);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzaa zzaa = (zzaa) zzi;
        zzaa.zzrl.addAll(this.zzrl);
        zzaa.zzrk.addAll(this.zzrk);
        for (Entry entry : this.zzrj.entrySet()) {
            String str = (String) entry.getKey();
            for (Product product : (List) entry.getValue()) {
                if (product != null) {
                    String str2 = str == null ? "" : str;
                    if (!zzaa.zzrj.containsKey(str2)) {
                        zzaa.zzrj.put(str2, new ArrayList());
                    }
                    ((List) zzaa.zzrj.get(str2)).add(product);
                }
            }
        }
        if (this.zzri != null) {
            zzaa.zzri = this.zzri;
        }
    }
}
