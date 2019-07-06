package com.contextlogic.wish.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* renamed from: com.contextlogic.wish.api.model.$$AutoValue_BuyerGuaranteeInfo reason: invalid class name */
abstract class C$$AutoValue_BuyerGuaranteeInfo extends BuyerGuaranteeInfo {
    private final List<PageItemHolder> pageItems;
    private final String pageSubtitle;
    private final String pageTitle;
    private final String sectionBody;
    private final String sectionSubtitle;
    private final String sectionTitle;

    C$$AutoValue_BuyerGuaranteeInfo(String str, String str2, String str3, String str4, String str5, List<PageItemHolder> list) {
        if (str == null) {
            throw new NullPointerException("Null sectionTitle");
        }
        this.sectionTitle = str;
        if (str2 == null) {
            throw new NullPointerException("Null sectionSubtitle");
        }
        this.sectionSubtitle = str2;
        if (str3 == null) {
            throw new NullPointerException("Null sectionBody");
        }
        this.sectionBody = str3;
        if (str4 == null) {
            throw new NullPointerException("Null pageTitle");
        }
        this.pageTitle = str4;
        if (str5 == null) {
            throw new NullPointerException("Null pageSubtitle");
        }
        this.pageSubtitle = str5;
        if (list == null) {
            throw new NullPointerException("Null pageItems");
        }
        this.pageItems = list;
    }

    @SerializedName("section_title")
    public String getSectionTitle() {
        return this.sectionTitle;
    }

    @SerializedName("section_subtitle")
    public String getSectionSubtitle() {
        return this.sectionSubtitle;
    }

    @SerializedName("section_body")
    public String getSectionBody() {
        return this.sectionBody;
    }

    @SerializedName("page_title")
    public String getPageTitle() {
        return this.pageTitle;
    }

    @SerializedName("page_subtitle")
    public String getPageSubtitle() {
        return this.pageSubtitle;
    }

    @SerializedName("page_items")
    public List<PageItemHolder> getPageItems() {
        return this.pageItems;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BuyerGuaranteeInfo{sectionTitle=");
        sb.append(this.sectionTitle);
        sb.append(", sectionSubtitle=");
        sb.append(this.sectionSubtitle);
        sb.append(", sectionBody=");
        sb.append(this.sectionBody);
        sb.append(", pageTitle=");
        sb.append(this.pageTitle);
        sb.append(", pageSubtitle=");
        sb.append(this.pageSubtitle);
        sb.append(", pageItems=");
        sb.append(this.pageItems);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BuyerGuaranteeInfo)) {
            return false;
        }
        BuyerGuaranteeInfo buyerGuaranteeInfo = (BuyerGuaranteeInfo) obj;
        if (!this.sectionTitle.equals(buyerGuaranteeInfo.getSectionTitle()) || !this.sectionSubtitle.equals(buyerGuaranteeInfo.getSectionSubtitle()) || !this.sectionBody.equals(buyerGuaranteeInfo.getSectionBody()) || !this.pageTitle.equals(buyerGuaranteeInfo.getPageTitle()) || !this.pageSubtitle.equals(buyerGuaranteeInfo.getPageSubtitle()) || !this.pageItems.equals(buyerGuaranteeInfo.getPageItems())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((((this.sectionTitle.hashCode() ^ 1000003) * 1000003) ^ this.sectionSubtitle.hashCode()) * 1000003) ^ this.sectionBody.hashCode()) * 1000003) ^ this.pageTitle.hashCode()) * 1000003) ^ this.pageSubtitle.hashCode()) * 1000003) ^ this.pageItems.hashCode();
    }
}
