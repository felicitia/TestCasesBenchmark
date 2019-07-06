package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class WishFilterOption extends BaseModel implements Parcelable {
    public static final Creator<WishFilterOption> CREATOR = new Creator<WishFilterOption>() {
        public WishFilterOption createFromParcel(Parcel parcel) {
            return new WishFilterOption(parcel);
        }

        public WishFilterOption[] newArray(int i) {
            return new WishFilterOption[i];
        }
    };
    private ArrayList<WishFilterOption> mChildFilters;
    private String mFilterId;
    private boolean mIsExclusive = false;
    private boolean mIsParentChild = false;
    private String mName;
    private WishFilterOption mParentFilter;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) {
    }

    public WishFilterOption() {
    }

    public WishFilterOption(WishFilter wishFilter) {
        this.mName = wishFilter.getName();
        this.mFilterId = wishFilter.getFilterId();
        if (wishFilter.getChildFilterGroups() != null && wishFilter.getChildFilterGroups().size() > 0) {
            Iterator it = wishFilter.getChildFilterGroups().iterator();
            while (it.hasNext()) {
                WishFilterGroup wishFilterGroup = (WishFilterGroup) it.next();
                if (wishFilterGroup.isSubCategory()) {
                    this.mIsExclusive = wishFilterGroup.isExclusive();
                    this.mChildFilters = new ArrayList<>();
                    WishFilterOption wishFilterOption = new WishFilterOption();
                    wishFilterOption.mName = this.mName;
                    wishFilterOption.mFilterId = this.mFilterId;
                    wishFilterOption.mIsParentChild = true;
                    wishFilterOption.setParentFilter(this);
                    this.mChildFilters.add(wishFilterOption);
                    Iterator it2 = wishFilterGroup.getFilters().iterator();
                    while (it2.hasNext()) {
                        WishFilterOption wishFilterOption2 = new WishFilterOption((WishFilter) it2.next());
                        wishFilterOption2.setParentFilter(this);
                        this.mChildFilters.add(wishFilterOption2);
                    }
                    return;
                }
            }
        }
    }

    public WishFilterOption(WishFilterGroup wishFilterGroup) {
        this.mName = wishFilterGroup.getName();
        this.mChildFilters = new ArrayList<>();
        this.mIsExclusive = wishFilterGroup.isExclusive();
        Iterator it = wishFilterGroup.getFilters().iterator();
        while (it.hasNext()) {
            WishFilterOption wishFilterOption = new WishFilterOption((WishFilter) it.next());
            wishFilterOption.setParentFilter(this);
            this.mChildFilters.add(wishFilterOption);
        }
    }

    protected WishFilterOption(Parcel parcel) {
        boolean z = false;
        this.mIsExclusive = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsParentChild = z;
        this.mChildFilters = parcel.createTypedArrayList(CREATOR);
        this.mFilterId = parcel.readString();
        this.mName = parcel.readString();
        this.mParentFilter = (WishFilterOption) parcel.readParcelable(WishFilterOption.class.getClassLoader());
    }

    public String getName() {
        return this.mName;
    }

    public String getFilterId() {
        return this.mFilterId;
    }

    public void setParentFilter(WishFilterOption wishFilterOption) {
        this.mParentFilter = wishFilterOption;
    }

    public WishFilterOption getParentFilter() {
        return this.mParentFilter;
    }

    public ArrayList<WishFilterOption> getChildFilters() {
        return this.mChildFilters;
    }

    public boolean isExclusive() {
        return this.mIsExclusive;
    }

    public boolean isParentChild() {
        return this.mIsParentChild;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsExclusive ? (byte) 1 : 0);
        parcel.writeByte(this.mIsParentChild ? (byte) 1 : 0);
        parcel.writeTypedList(this.mChildFilters);
        parcel.writeString(this.mFilterId);
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mParentFilter, 0);
    }
}
