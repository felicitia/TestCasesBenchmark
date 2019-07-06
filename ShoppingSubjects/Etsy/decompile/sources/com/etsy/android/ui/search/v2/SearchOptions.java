package com.etsy.android.ui.search.v2;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.etsy.android.R;
import com.etsy.android.extensions.f;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.ui.search.SortOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.TypeCastException;
import kotlin.collections.o;
import kotlin.jvm.internal.p;

@SuppressLint({"ParcelCreator"})
/* compiled from: SearchOptions.kt */
public final class SearchOptions implements Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new Creator();
    public static final a Companion = new a(null);
    public static final int DEFAULT_HIGH_PRICE = 1000;
    public static final int DEFAULT_LOW_PRICE = 0;
    private boolean acceptsGiftCards;
    private List<? extends FacetCount> categoryFacets;
    private boolean customizable;
    private boolean freeShipping;
    private boolean giftWrap;
    private MarketPlace marketplace;
    private int maxPrice;
    private int minPrice;
    private boolean onSale;
    private boolean oneDayShipping;
    private String shipsToCountryCode;
    private String shipsToCountryName;
    private final Location shopLocation;
    private SortOrder sortOrder;
    private boolean threeDayShipping;

    public static class Creator implements android.os.Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Parcel parcel2 = parcel;
            p.b(parcel2, "in");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((FacetCount) parcel.readSerializable());
                readInt--;
            }
            SearchOptions searchOptions = new SearchOptions(arrayList, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, (Location) Location.CREATOR.createFromParcel(parcel2), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), (SortOrder) Enum.valueOf(SortOrder.class, parcel.readString()), (MarketPlace) Enum.valueOf(MarketPlace.class, parcel.readString()));
            return searchOptions;
        }

        public final Object[] newArray(int i) {
            return new SearchOptions[i];
        }
    }

    @SuppressLint({"ParcelCreator"})
    /* compiled from: SearchOptions.kt */
    public static final class Location implements Parcelable {
        public static final android.os.Parcelable.Creator CREATOR = new Creator();
        private String location;
        private LocationType type;

        public static class Creator implements android.os.Parcelable.Creator {
            public final Object createFromParcel(Parcel parcel) {
                p.b(parcel, "in");
                return new Location(parcel.readString(), (LocationType) Enum.valueOf(LocationType.class, parcel.readString()));
            }

            public final Object[] newArray(int i) {
                return new Location[i];
            }
        }

        /* compiled from: SearchOptions.kt */
        public enum LocationType {
            ANYWHERE,
            LOCAL,
            CUSTOM
        }

        public Location() {
            this(null, null, 3, null);
        }

        public static /* synthetic */ Location copy$default(Location location2, String str, LocationType locationType, int i, Object obj) {
            if ((i & 1) != 0) {
                str = location2.location;
            }
            if ((i & 2) != 0) {
                locationType = location2.type;
            }
            return location2.copy(str, locationType);
        }

        public final String component1() {
            return this.location;
        }

        public final LocationType component2() {
            return this.type;
        }

        public final Location copy(String str, LocationType locationType) {
            p.b(str, ResponseConstants.LOCATION);
            p.b(locationType, "type");
            return new Location(str, locationType);
        }

        public final int describeContents() {
            return 0;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.p.a((java.lang.Object) r2.type, (java.lang.Object) r3.type) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.etsy.android.ui.search.v2.SearchOptions.Location
                if (r0 == 0) goto L_0x001d
                com.etsy.android.ui.search.v2.SearchOptions$Location r3 = (com.etsy.android.ui.search.v2.SearchOptions.Location) r3
                java.lang.String r0 = r2.location
                java.lang.String r1 = r3.location
                boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
                if (r0 == 0) goto L_0x001d
                com.etsy.android.ui.search.v2.SearchOptions$Location$LocationType r0 = r2.type
                com.etsy.android.ui.search.v2.SearchOptions$Location$LocationType r3 = r3.type
                boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
                if (r3 == 0) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r3 = 0
                return r3
            L_0x001f:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.search.v2.SearchOptions.Location.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.location;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            LocationType locationType = this.type;
            if (locationType != null) {
                i = locationType.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Location(location=");
            sb.append(this.location);
            sb.append(", type=");
            sb.append(this.type);
            sb.append(")");
            return sb.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            p.b(parcel, "parcel");
            parcel.writeString(this.location);
            parcel.writeString(this.type.name());
        }

        public Location(String str, LocationType locationType) {
            p.b(str, ResponseConstants.LOCATION);
            p.b(locationType, "type");
            this.location = str;
            this.type = locationType;
        }

        public /* synthetic */ Location(String str, LocationType locationType, int i, o oVar) {
            if ((i & 1) != 0) {
                str = "";
            }
            if ((i & 2) != 0) {
                locationType = LocationType.ANYWHERE;
            }
            this(str, locationType);
        }

        public final String getLocation() {
            return this.location;
        }

        public final LocationType getType() {
            return this.type;
        }

        public final void setLocation(String str) {
            p.b(str, "<set-?>");
            this.location = str;
        }

        public final void setType(LocationType locationType) {
            p.b(locationType, "<set-?>");
            this.type = locationType;
        }

        public final String getLocation(Resources resources) {
            p.b(resources, "resources");
            if (this.type != LocationType.ANYWHERE) {
                return this.location;
            }
            String string = resources.getString(R.string.new_search_filter_shop_location_anywhere);
            p.a((Object) string, "resources.getString(R.st…r_shop_location_anywhere)");
            return string;
        }

        public final boolean isAnywhere() {
            return this.type == LocationType.ANYWHERE;
        }

        public final <T extends BaseModel> void apply(m<T> mVar) {
            p.b(mVar, "builder");
            if (!isAnywhere()) {
                mVar.a(ResponseConstants.LOCATION, this.location);
            }
        }

        public final void resetToDefault() {
            this.type = LocationType.ANYWHERE;
            this.location = "";
        }

        public final void set(LocationType locationType, String str) {
            p.b(locationType, "type");
            p.b(str, ResponseConstants.LOCATION);
            if (locationType == LocationType.ANYWHERE) {
                resetToDefault();
                return;
            }
            this.type = locationType;
            this.location = str;
        }
    }

    /* compiled from: SearchOptions.kt */
    public enum MarketPlace {
        MARKETPLACE_ALL_ITEMS,
        MARKETPLACE_HANDMADE,
        MARKETPLACE_VINTAGE;

        public final String getHttpParamValue() {
            switch (c.a[ordinal()]) {
                case 1:
                    return ResponseConstants.HANDMADE;
                case 2:
                    return "vintage";
                default:
                    return "";
            }
        }
    }

    /* compiled from: SearchOptions.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        public final SearchOptions a(c cVar, l lVar) {
            SearchOptions searchOptions;
            c cVar2 = cVar;
            p.b(cVar2, "route");
            String a = cVar2.a(SearchOptionsParams.LOCATION.toString());
            String a2 = cVar2.a(SearchOptionsParams.SHIP_TO.toString());
            String a3 = cVar2.a(SearchOptionsParams.MIN_PRICE.toString());
            if (TextUtils.isEmpty(a3)) {
                a3 = cVar2.a(SearchOptionsParams.MIN_PRICE_LEGACY.toString());
            }
            String a4 = cVar2.a(SearchOptionsParams.MAX_PRICE.toString());
            if (TextUtils.isEmpty(a4)) {
                a4 = cVar2.a(SearchOptionsParams.MAX_PRICE_LEGACY.toString());
            }
            String a5 = cVar2.a(SearchOptionsParams.MARKETPLACE.toString());
            String a6 = cVar2.a(SearchOptionsParams.FREE_SHIPPING.toString());
            String a7 = cVar2.a(SearchOptionsParams.MAX_PROCESSING_DAYS.toString());
            String a8 = cVar2.a(SearchOptionsParams.IS_DISCOUNTED.toString());
            String a9 = cVar2.a(SearchOptionsParams.CUSTOMIZABLE.toString());
            String a10 = cVar2.a(SearchOptionsParams.GIFT_WRAP.toString());
            SearchOptions searchOptions2 = r10;
            SearchOptions searchOptions3 = new SearchOptions(null, false, false, false, false, false, false, false, null, 0, 0, null, null, null, null, 32767, null);
            if (!TextUtils.isEmpty(a)) {
                searchOptions = searchOptions2;
                Location shopLocation = searchOptions.getShopLocation();
                LocationType locationType = LocationType.CUSTOM;
                p.a((Object) a, ResponseConstants.LOCATION);
                shopLocation.set(locationType, f.a(a, lVar));
            } else {
                searchOptions = searchOptions2;
            }
            if (!TextUtils.isEmpty(a2)) {
                Locale locale = Locale.getDefault();
                p.a((Object) locale, "Locale.getDefault()");
                Locale locale2 = new Locale(locale.getLanguage(), a2);
                p.a((Object) a2, "shipTo");
                Locale locale3 = Locale.ROOT;
                p.a((Object) locale3, "Locale.ROOT");
                if (a2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = a2.toUpperCase(locale3);
                p.a((Object) upperCase, "(this as java.lang.String).toUpperCase(locale)");
                String displayCountry = locale2.getDisplayCountry();
                p.a((Object) displayCountry, "shipToLocale.displayCountry");
                searchOptions.setShipsTo(upperCase, displayCountry);
            }
            if (!TextUtils.isEmpty(a3)) {
                searchOptions.setMinPrice(Integer.parseInt(a3));
            }
            if (!TextUtils.isEmpty(a4)) {
                searchOptions.setMaxPrice(Integer.parseInt(a4));
            }
            if (!TextUtils.isEmpty(a5)) {
                p.a((Object) a5, "marketPlace");
                searchOptions.setMarketplace(a5);
            }
            boolean z = true;
            if (!TextUtils.isEmpty(a6)) {
                searchOptions.setFreeShipping(p.a((Object) "1", (Object) a6) || Boolean.parseBoolean(a6));
            }
            CharSequence charSequence = a7;
            if (!TextUtils.isEmpty(charSequence)) {
                p.a((Object) a7, "maxProcessingDays");
                searchOptions.setOneDayShipping(kotlin.text.m.a(charSequence, (CharSequence) "1", false, 2, (Object) null));
                searchOptions.setThreeDayShipping(kotlin.text.m.a(charSequence, (CharSequence) "3", false, 2, (Object) null));
            }
            if (!TextUtils.isEmpty(a8)) {
                searchOptions.setOnSale(p.a((Object) "1", (Object) a8) || Boolean.parseBoolean(a8));
            }
            if (!TextUtils.isEmpty(a9)) {
                searchOptions.setCustomizable(p.a((Object) "1", (Object) a9) || Boolean.parseBoolean(a9));
            }
            if (!TextUtils.isEmpty(a10)) {
                if (!p.a((Object) "1", (Object) a10) && !Boolean.parseBoolean(a10)) {
                    z = false;
                }
                searchOptions.setGiftWrap(z);
            }
            if (searchOptions.hasDefaults()) {
                return null;
            }
            return searchOptions;
        }
    }

    public SearchOptions() {
        this(null, false, false, false, false, false, false, false, null, 0, 0, null, null, null, null, 32767, null);
    }

    public static /* synthetic */ SearchOptions copy$default(SearchOptions searchOptions, List list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Location location, int i, int i2, String str, String str2, SortOrder sortOrder2, MarketPlace marketPlace, int i3, Object obj) {
        SearchOptions searchOptions2 = searchOptions;
        int i4 = i3;
        return searchOptions2.copy((i4 & 1) != 0 ? searchOptions2.categoryFacets : list, (i4 & 2) != 0 ? searchOptions2.onSale : z, (i4 & 4) != 0 ? searchOptions2.freeShipping : z2, (i4 & 8) != 0 ? searchOptions2.oneDayShipping : z3, (i4 & 16) != 0 ? searchOptions2.threeDayShipping : z4, (i4 & 32) != 0 ? searchOptions2.acceptsGiftCards : z5, (i4 & 64) != 0 ? searchOptions2.customizable : z6, (i4 & 128) != 0 ? searchOptions2.giftWrap : z7, (i4 & 256) != 0 ? searchOptions2.shopLocation : location, (i4 & 512) != 0 ? searchOptions2.minPrice : i, (i4 & 1024) != 0 ? searchOptions2.maxPrice : i2, (i4 & 2048) != 0 ? searchOptions2.shipsToCountryCode : str, (i4 & 4096) != 0 ? searchOptions2.shipsToCountryName : str2, (i4 & 8192) != 0 ? searchOptions2.sortOrder : sortOrder2, (i4 & 16384) != 0 ? searchOptions2.marketplace : marketPlace);
    }

    public static /* synthetic */ void selectedCategoryFacet$annotations() {
    }

    public final List<FacetCount> component1() {
        return this.categoryFacets;
    }

    public final int component10() {
        return this.minPrice;
    }

    public final int component11() {
        return this.maxPrice;
    }

    public final String component12() {
        return this.shipsToCountryCode;
    }

    public final String component13() {
        return this.shipsToCountryName;
    }

    public final SortOrder component14() {
        return this.sortOrder;
    }

    public final MarketPlace component15() {
        return this.marketplace;
    }

    public final boolean component2() {
        return this.onSale;
    }

    public final boolean component3() {
        return this.freeShipping;
    }

    public final boolean component4() {
        return this.oneDayShipping;
    }

    public final boolean component5() {
        return this.threeDayShipping;
    }

    public final boolean component6() {
        return this.acceptsGiftCards;
    }

    public final boolean component7() {
        return this.customizable;
    }

    public final boolean component8() {
        return this.giftWrap;
    }

    public final Location component9() {
        return this.shopLocation;
    }

    public final SearchOptions copy(List<? extends FacetCount> list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Location location, int i, int i2, String str, String str2, SortOrder sortOrder2, MarketPlace marketPlace) {
        List<? extends FacetCount> list2 = list;
        p.b(list2, "categoryFacets");
        Location location2 = location;
        p.b(location2, "shopLocation");
        String str3 = str;
        p.b(str3, "shipsToCountryCode");
        String str4 = str2;
        p.b(str4, "shipsToCountryName");
        SortOrder sortOrder3 = sortOrder2;
        p.b(sortOrder3, "sortOrder");
        p.b(marketPlace, "marketplace");
        SearchOptions searchOptions = new SearchOptions(list2, z, z2, z3, z4, z5, z6, z7, location2, i, i2, str3, str4, sortOrder3, marketPlace);
        return searchOptions;
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SearchOptions) {
            SearchOptions searchOptions = (SearchOptions) obj;
            if (p.a((Object) this.categoryFacets, (Object) searchOptions.categoryFacets)) {
                if (this.onSale == searchOptions.onSale) {
                    if (this.freeShipping == searchOptions.freeShipping) {
                        if (this.oneDayShipping == searchOptions.oneDayShipping) {
                            if (this.threeDayShipping == searchOptions.threeDayShipping) {
                                if (this.acceptsGiftCards == searchOptions.acceptsGiftCards) {
                                    if (this.customizable == searchOptions.customizable) {
                                        if ((this.giftWrap == searchOptions.giftWrap) && p.a((Object) this.shopLocation, (Object) searchOptions.shopLocation)) {
                                            if (this.minPrice == searchOptions.minPrice) {
                                                return (this.maxPrice == searchOptions.maxPrice) && p.a((Object) this.shipsToCountryCode, (Object) searchOptions.shipsToCountryCode) && p.a((Object) this.shipsToCountryName, (Object) searchOptions.shipsToCountryName) && p.a((Object) this.sortOrder, (Object) searchOptions.sortOrder) && p.a((Object) this.marketplace, (Object) searchOptions.marketplace);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        List<? extends FacetCount> list = this.categoryFacets;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        int i2 = this.onSale;
        if (i2 != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        int i4 = this.freeShipping;
        if (i4 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        int i6 = this.oneDayShipping;
        if (i6 != 0) {
            i6 = 1;
        }
        int i7 = (i5 + i6) * 31;
        int i8 = this.threeDayShipping;
        if (i8 != 0) {
            i8 = 1;
        }
        int i9 = (i7 + i8) * 31;
        int i10 = this.acceptsGiftCards;
        if (i10 != 0) {
            i10 = 1;
        }
        int i11 = (i9 + i10) * 31;
        int i12 = this.customizable;
        if (i12 != 0) {
            i12 = 1;
        }
        int i13 = (i11 + i12) * 31;
        int i14 = this.giftWrap;
        if (i14 != 0) {
            i14 = 1;
        }
        int i15 = (i13 + i14) * 31;
        Location location = this.shopLocation;
        int hashCode2 = (((((i15 + (location != null ? location.hashCode() : 0)) * 31) + this.minPrice) * 31) + this.maxPrice) * 31;
        String str = this.shipsToCountryCode;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.shipsToCountryName;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        SortOrder sortOrder2 = this.sortOrder;
        int hashCode5 = (hashCode4 + (sortOrder2 != null ? sortOrder2.hashCode() : 0)) * 31;
        MarketPlace marketPlace = this.marketplace;
        if (marketPlace != null) {
            i = marketPlace.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SearchOptions(categoryFacets=");
        sb.append(this.categoryFacets);
        sb.append(", onSale=");
        sb.append(this.onSale);
        sb.append(", freeShipping=");
        sb.append(this.freeShipping);
        sb.append(", oneDayShipping=");
        sb.append(this.oneDayShipping);
        sb.append(", threeDayShipping=");
        sb.append(this.threeDayShipping);
        sb.append(", acceptsGiftCards=");
        sb.append(this.acceptsGiftCards);
        sb.append(", customizable=");
        sb.append(this.customizable);
        sb.append(", giftWrap=");
        sb.append(this.giftWrap);
        sb.append(", shopLocation=");
        sb.append(this.shopLocation);
        sb.append(", minPrice=");
        sb.append(this.minPrice);
        sb.append(", maxPrice=");
        sb.append(this.maxPrice);
        sb.append(", shipsToCountryCode=");
        sb.append(this.shipsToCountryCode);
        sb.append(", shipsToCountryName=");
        sb.append(this.shipsToCountryName);
        sb.append(", sortOrder=");
        sb.append(this.sortOrder);
        sb.append(", marketplace=");
        sb.append(this.marketplace);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        p.b(parcel, "parcel");
        List<? extends FacetCount> list = this.categoryFacets;
        parcel.writeInt(list.size());
        for (FacetCount writeSerializable : list) {
            parcel.writeSerializable(writeSerializable);
        }
        parcel.writeInt(this.onSale ? 1 : 0);
        parcel.writeInt(this.freeShipping ? 1 : 0);
        parcel.writeInt(this.oneDayShipping ? 1 : 0);
        parcel.writeInt(this.threeDayShipping ? 1 : 0);
        parcel.writeInt(this.acceptsGiftCards ? 1 : 0);
        parcel.writeInt(this.customizable ? 1 : 0);
        parcel.writeInt(this.giftWrap ? 1 : 0);
        this.shopLocation.writeToParcel(parcel, 0);
        parcel.writeInt(this.minPrice);
        parcel.writeInt(this.maxPrice);
        parcel.writeString(this.shipsToCountryCode);
        parcel.writeString(this.shipsToCountryName);
        parcel.writeString(this.sortOrder.name());
        parcel.writeString(this.marketplace.name());
    }

    public SearchOptions(List<? extends FacetCount> list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Location location, int i, int i2, String str, String str2, SortOrder sortOrder2, MarketPlace marketPlace) {
        List<? extends FacetCount> list2 = list;
        Location location2 = location;
        String str3 = str;
        String str4 = str2;
        SortOrder sortOrder3 = sortOrder2;
        MarketPlace marketPlace2 = marketPlace;
        p.b(list2, "categoryFacets");
        p.b(location2, "shopLocation");
        p.b(str3, "shipsToCountryCode");
        p.b(str4, "shipsToCountryName");
        p.b(sortOrder3, "sortOrder");
        p.b(marketPlace2, "marketplace");
        this.categoryFacets = list2;
        this.onSale = z;
        this.freeShipping = z2;
        this.oneDayShipping = z3;
        this.threeDayShipping = z4;
        this.acceptsGiftCards = z5;
        this.customizable = z6;
        this.giftWrap = z7;
        this.shopLocation = location2;
        this.minPrice = i;
        this.maxPrice = i2;
        this.shipsToCountryCode = str3;
        this.shipsToCountryName = str4;
        this.sortOrder = sortOrder3;
        this.marketplace = marketPlace2;
    }

    public /* synthetic */ SearchOptions(List list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Location location, int i, int i2, String str, String str2, SortOrder sortOrder2, MarketPlace marketPlace, int i3, o oVar) {
        String str3;
        String str4;
        SortOrder sortOrder3;
        int i4 = i3;
        List arrayList = (i4 & 1) != 0 ? new ArrayList() : list;
        boolean z8 = (i4 & 2) != 0 ? false : z;
        boolean z9 = (i4 & 4) != 0 ? false : z2;
        boolean z10 = (i4 & 8) != 0 ? false : z3;
        boolean z11 = (i4 & 16) != 0 ? false : z4;
        boolean z12 = (i4 & 32) != 0 ? false : z5;
        boolean z13 = (i4 & 64) != 0 ? false : z6;
        boolean z14 = (i4 & 128) != 0 ? false : z7;
        Location location2 = (i4 & 256) != 0 ? new Location(null, null, 3, null) : location;
        int i5 = (i4 & 512) != 0 ? 0 : i;
        int i6 = (i4 & 1024) != 0 ? 1000 : i2;
        if ((i4 & 2048) != 0) {
            Locale locale = Locale.getDefault();
            p.a((Object) locale, "Locale.getDefault()");
            String country = locale.getCountry();
            p.a((Object) country, "Locale.getDefault().country");
            str3 = country;
        } else {
            str3 = str;
        }
        if ((i4 & 4096) != 0) {
            Locale locale2 = Locale.getDefault();
            p.a((Object) locale2, "Locale.getDefault()");
            String displayCountry = locale2.getDisplayCountry();
            p.a((Object) displayCountry, "Locale.getDefault().displayCountry");
            str4 = displayCountry;
        } else {
            str4 = str2;
        }
        if ((i4 & 8192) != 0) {
            SortOrder sortOrder4 = SortOrder.DEFAULT;
            p.a((Object) sortOrder4, "SortOrder.DEFAULT");
            sortOrder3 = sortOrder4;
        } else {
            sortOrder3 = sortOrder2;
        }
        this(arrayList, z8, z9, z10, z11, z12, z13, z14, location2, i5, i6, str3, str4, sortOrder3, (i4 & 16384) != 0 ? MarketPlace.MARKETPLACE_ALL_ITEMS : marketPlace);
    }

    public final List<FacetCount> getCategoryFacets() {
        return this.categoryFacets;
    }

    public final void setCategoryFacets(List<? extends FacetCount> list) {
        p.b(list, "<set-?>");
        this.categoryFacets = list;
    }

    public final boolean getOnSale() {
        return this.onSale;
    }

    public final void setOnSale(boolean z) {
        this.onSale = z;
    }

    public final boolean getFreeShipping() {
        return this.freeShipping;
    }

    public final void setFreeShipping(boolean z) {
        this.freeShipping = z;
    }

    public final boolean getOneDayShipping() {
        return this.oneDayShipping;
    }

    public final void setOneDayShipping(boolean z) {
        this.oneDayShipping = z;
    }

    public final boolean getThreeDayShipping() {
        return this.threeDayShipping;
    }

    public final void setThreeDayShipping(boolean z) {
        this.threeDayShipping = z;
    }

    public final boolean getAcceptsGiftCards() {
        return this.acceptsGiftCards;
    }

    public final void setAcceptsGiftCards(boolean z) {
        this.acceptsGiftCards = z;
    }

    public final boolean getCustomizable() {
        return this.customizable;
    }

    public final void setCustomizable(boolean z) {
        this.customizable = z;
    }

    public final boolean getGiftWrap() {
        return this.giftWrap;
    }

    public final void setGiftWrap(boolean z) {
        this.giftWrap = z;
    }

    public final Location getShopLocation() {
        return this.shopLocation;
    }

    public final int getMinPrice() {
        return this.minPrice;
    }

    public final void setMinPrice(int i) {
        this.minPrice = i;
    }

    public final int getMaxPrice() {
        return this.maxPrice;
    }

    public final void setMaxPrice(int i) {
        this.maxPrice = i;
    }

    public final String getShipsToCountryCode() {
        return this.shipsToCountryCode;
    }

    public final void setShipsToCountryCode(String str) {
        p.b(str, "<set-?>");
        this.shipsToCountryCode = str;
    }

    public final String getShipsToCountryName() {
        return this.shipsToCountryName;
    }

    public final void setShipsToCountryName(String str) {
        p.b(str, "<set-?>");
        this.shipsToCountryName = str;
    }

    public final SortOrder getSortOrder() {
        return this.sortOrder;
    }

    public final void setSortOrder(SortOrder sortOrder2) {
        p.b(sortOrder2, "<set-?>");
        this.sortOrder = sortOrder2;
    }

    public final MarketPlace getMarketplace() {
        return this.marketplace;
    }

    public final void setMarketplace(MarketPlace marketPlace) {
        p.b(marketPlace, "<set-?>");
        this.marketplace = marketPlace;
    }

    public final void setShipsTo(String str, String str2) {
        p.b(str, ResponseConstants.CODE);
        p.b(str2, ResponseConstants.NAME);
        this.shipsToCountryCode = str;
        this.shipsToCountryName = str2;
    }

    public final void setMarketplace(String str) {
        p.b(str, "marketplace");
        int hashCode = str.hashCode();
        if (hashCode != 2094596) {
            if (hashCode == 462452390 && str.equals("vintage")) {
                this.marketplace = MarketPlace.MARKETPLACE_VINTAGE;
                return;
            }
        } else if (str.equals(ResponseConstants.HANDMADE)) {
            this.marketplace = MarketPlace.MARKETPLACE_HANDMADE;
            return;
        }
        this.marketplace = MarketPlace.MARKETPLACE_ALL_ITEMS;
    }

    public final FacetCount getSelectedCategoryFacet() {
        if (this.categoryFacets.isEmpty()) {
            return null;
        }
        return (FacetCount) o.g((List) this.categoryFacets);
    }

    public final boolean hasDefaults() {
        return !hasCategoryFacets() && !this.onSale && !this.freeShipping && !this.oneDayShipping && !this.threeDayShipping && !this.acceptsGiftCards && !this.customizable && !this.giftWrap && this.shopLocation.isAnywhere() && !hasShipsToCountry() && !hasMinPrice() && !hasMaxPrice() && !hasMarketplace() && this.sortOrder == SortOrder.DEFAULT;
    }

    public final boolean hasCategoryFacets() {
        return !this.categoryFacets.isEmpty();
    }

    public final boolean hasMinPrice() {
        return this.minPrice != 0;
    }

    public final boolean hasMaxPrice() {
        return this.maxPrice != 1000;
    }

    public final boolean hasShipsToCountry() {
        Locale locale = Locale.getDefault();
        p.a((Object) locale, "Locale.getDefault()");
        return !p.a((Object) locale.getCountry(), (Object) this.shipsToCountryCode);
    }

    public final boolean hasMarketplace() {
        return this.marketplace != MarketPlace.MARKETPLACE_ALL_ITEMS;
    }

    public final <T extends BaseModel> void apply(m<T> mVar) {
        p.b(mVar, "builder");
        if (this.onSale) {
            mVar.a("is_discounted");
        }
        if (this.freeShipping) {
            mVar.a("free_shipping");
        }
        applyProcessingDays(mVar);
        if (this.acceptsGiftCards) {
            mVar.a("accepts_gift_cards");
        }
        if (this.minPrice > 0) {
            mVar.a(ResponseConstants.MIN_PRICE, String.valueOf(this.minPrice));
        }
        if (this.maxPrice < 1000) {
            mVar.a(ResponseConstants.MAX_PRICE, String.valueOf(this.maxPrice));
        }
        CharSequence charSequence = this.shipsToCountryCode;
        if (!(charSequence == null || charSequence.length() == 0)) {
            mVar.a("ship_to", this.shipsToCountryCode);
        }
        if (this.marketplace != MarketPlace.MARKETPLACE_ALL_ITEMS) {
            mVar.a("marketplace", this.marketplace.getHttpParamValue());
        }
        if (hasCategoryFacets()) {
            String str = "category";
            FacetCount selectedCategoryFacet = getSelectedCategoryFacet();
            mVar.a(str, selectedCategoryFacet != null ? selectedCategoryFacet.getId() : null);
        }
        if (this.customizable) {
            mVar.a("customizable");
        }
        if (this.giftWrap) {
            mVar.a("gift_wrap");
        }
        this.shopLocation.apply(mVar);
        addSortOrderParams(mVar, this.sortOrder);
    }

    private final <T extends BaseModel> void applyProcessingDays(m<T> mVar) {
        if (this.oneDayShipping && this.threeDayShipping) {
            mVar.a("max_processing_days", "1,3");
        } else if (this.oneDayShipping) {
            mVar.a("max_processing_days", "1");
        } else if (this.threeDayShipping) {
            mVar.a("max_processing_days", "3");
        }
    }

    private final <T extends BaseModel> void addSortOrderParams(m<T> mVar, SortOrder sortOrder2) {
        String str = "down";
        String str2 = null;
        switch (d.a[sortOrder2.ordinal()]) {
            case 1:
                str2 = "score";
                break;
            case 2:
                str2 = "created";
                break;
            case 3:
                str2 = ResponseConstants.PRICE;
                break;
            case 4:
                str2 = ResponseConstants.PRICE;
                str = "up";
                break;
        }
        mVar.a("sort_on", str2);
        mVar.a("sort_order", str);
    }

    public final void resetToDefault() {
        this.categoryFacets = new ArrayList();
        this.onSale = false;
        this.acceptsGiftCards = false;
        this.freeShipping = false;
        this.oneDayShipping = false;
        this.threeDayShipping = false;
        this.customizable = false;
        this.giftWrap = false;
        this.shopLocation.resetToDefault();
        resetShipsToCountry();
        resetPriceLimit();
        this.marketplace = MarketPlace.MARKETPLACE_ALL_ITEMS;
        SortOrder sortOrder2 = SortOrder.DEFAULT;
        p.a((Object) sortOrder2, "SortOrder.DEFAULT");
        this.sortOrder = sortOrder2;
    }

    public final void resetShipsToCountry() {
        Locale locale = Locale.getDefault();
        p.a((Object) locale, "locale");
        String country = locale.getCountry();
        p.a((Object) country, "locale.country");
        this.shipsToCountryCode = country;
        String displayCountry = locale.getDisplayCountry();
        p.a((Object) displayCountry, "locale.displayCountry");
        this.shipsToCountryName = displayCountry;
    }

    public final void resetPriceLimit() {
        this.maxPrice = 1000;
        this.minPrice = 0;
    }

    public final void resetCategoryFacets() {
        this.categoryFacets = new ArrayList();
    }
}
