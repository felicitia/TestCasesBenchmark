package com.contextlogic.wish.api.model;

import com.google.gson.annotations.SerializedName;

/* renamed from: com.contextlogic.wish.api.model.$$AutoValue_PageItemHolder reason: invalid class name */
abstract class C$$AutoValue_PageItemHolder extends PageItemHolder {
    private final String action;
    private final String body;
    private final String imgUrl;
    private final String title;

    C$$AutoValue_PageItemHolder(String str, String str2, String str3, String str4) {
        if (str == null) {
            throw new NullPointerException("Null title");
        }
        this.title = str;
        if (str2 == null) {
            throw new NullPointerException("Null body");
        }
        this.body = str2;
        if (str3 == null) {
            throw new NullPointerException("Null imgUrl");
        }
        this.imgUrl = str3;
        this.action = str4;
    }

    @SerializedName("page_item_title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("page_item_body")
    public String getBody() {
        return this.body;
    }

    @SerializedName("page_item_img")
    public String getImgUrl() {
        return this.imgUrl;
    }

    @SerializedName("page_item_action")
    public String getAction() {
        return this.action;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PageItemHolder{title=");
        sb.append(this.title);
        sb.append(", body=");
        sb.append(this.body);
        sb.append(", imgUrl=");
        sb.append(this.imgUrl);
        sb.append(", action=");
        sb.append(this.action);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PageItemHolder)) {
            return false;
        }
        PageItemHolder pageItemHolder = (PageItemHolder) obj;
        if (!this.title.equals(pageItemHolder.getTitle()) || !this.body.equals(pageItemHolder.getBody()) || !this.imgUrl.equals(pageItemHolder.getImgUrl()) || (this.action != null ? !this.action.equals(pageItemHolder.getAction()) : pageItemHolder.getAction() != null)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((this.title.hashCode() ^ 1000003) * 1000003) ^ this.body.hashCode()) * 1000003) ^ this.imgUrl.hashCode()) * 1000003) ^ (this.action == null ? 0 : this.action.hashCode());
    }
}
