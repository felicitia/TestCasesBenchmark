package com.etsy.etsyapi.api.shop.bespoke;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.etsyapi.models.EtsyId;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_SellerDashboardGetSpec reason: invalid class name */
abstract class C$$AutoValue_SellerDashboardGetSpec extends SellerDashboardGetSpec {
    private final Boolean includeShopManager;
    private final EtsyId shop_id;
    private final String statsChannel;
    private final String statsRange;

    /* renamed from: com.etsy.etsyapi.api.shop.bespoke.$$AutoValue_SellerDashboardGetSpec$a */
    /* compiled from: $$AutoValue_SellerDashboardGetSpec */
    static final class a extends com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec.a {
        private EtsyId a;
        private String b;
        private String c;
        private Boolean d;

        a() {
        }

        a(SellerDashboardGetSpec sellerDashboardGetSpec) {
            this.a = sellerDashboardGetSpec.shop_id();
            this.b = sellerDashboardGetSpec.statsRange();
            this.c = sellerDashboardGetSpec.statsChannel();
            this.d = sellerDashboardGetSpec.includeShopManager();
        }

        public com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec.a a(EtsyId etsyId) {
            this.a = etsyId;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec.a a(@Nullable String str) {
            this.b = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec.a b(@Nullable String str) {
            this.c = str;
            return this;
        }

        public com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec.a a(@Nullable Boolean bool) {
            this.d = bool;
            return this;
        }

        public SellerDashboardGetSpec a() {
            String str = "";
            if (this.a == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" shop_id");
                str = sb.toString();
            }
            if (str.isEmpty()) {
                return new AutoValue_SellerDashboardGetSpec(this.a, this.b, this.c, this.d);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(str);
            throw new IllegalStateException(sb2.toString());
        }
    }

    C$$AutoValue_SellerDashboardGetSpec(EtsyId etsyId, @Nullable String str, @Nullable String str2, @Nullable Boolean bool) {
        if (etsyId == null) {
            throw new NullPointerException("Null shop_id");
        }
        this.shop_id = etsyId;
        this.statsRange = str;
        this.statsChannel = str2;
        this.includeShopManager = bool;
    }

    @NonNull
    public EtsyId shop_id() {
        return this.shop_id;
    }

    @Nullable
    public String statsRange() {
        return this.statsRange;
    }

    @Nullable
    public String statsChannel() {
        return this.statsChannel;
    }

    @Nullable
    public Boolean includeShopManager() {
        return this.includeShopManager;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SellerDashboardGetSpec{shop_id=");
        sb.append(this.shop_id);
        sb.append(", ");
        sb.append("statsRange=");
        sb.append(this.statsRange);
        sb.append(", ");
        sb.append("statsChannel=");
        sb.append(this.statsChannel);
        sb.append(", ");
        sb.append("includeShopManager=");
        sb.append(this.includeShopManager);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SellerDashboardGetSpec)) {
            return false;
        }
        SellerDashboardGetSpec sellerDashboardGetSpec = (SellerDashboardGetSpec) obj;
        if (!this.shop_id.equals(sellerDashboardGetSpec.shop_id()) || (this.statsRange != null ? !this.statsRange.equals(sellerDashboardGetSpec.statsRange()) : sellerDashboardGetSpec.statsRange() != null) || (this.statsChannel != null ? !this.statsChannel.equals(sellerDashboardGetSpec.statsChannel()) : sellerDashboardGetSpec.statsChannel() != null) || (this.includeShopManager != null ? !this.includeShopManager.equals(sellerDashboardGetSpec.includeShopManager()) : sellerDashboardGetSpec.includeShopManager() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.shop_id.hashCode() ^ 1000003) * 1000003) ^ (this.statsRange == null ? 0 : this.statsRange.hashCode())) * 1000003) ^ (this.statsChannel == null ? 0 : this.statsChannel.hashCode())) * 1000003;
        if (this.includeShopManager != null) {
            i = this.includeShopManager.hashCode();
        }
        return hashCode ^ i;
    }
}
