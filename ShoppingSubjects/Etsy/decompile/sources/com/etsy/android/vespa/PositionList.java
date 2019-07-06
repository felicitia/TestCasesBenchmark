package com.etsy.android.vespa;

import org.parceler.Parcel;

@Parcel
public class PositionList {
    protected int mChildPosition = -1;
    protected int mParentPosition = -1;

    public PositionList withParentPosition(int i) {
        setParentPosition(i);
        return this;
    }

    public PositionList withChildPosition(int i) {
        setChildPosition(i);
        return this;
    }

    public int getParentPosition() {
        if (this.mParentPosition == -1) {
            return this.mChildPosition;
        }
        return this.mParentPosition;
    }

    public int getChildPosition() {
        if (this.mParentPosition == -1) {
            return -1;
        }
        return this.mChildPosition;
    }

    public void setChildPosition(int i) {
        this.mChildPosition = i;
    }

    public void setParentPosition(int i) {
        this.mParentPosition = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PositionList:[");
        sb.append(getParentPosition());
        sb.append(",");
        sb.append(getChildPosition());
        sb.append("]");
        return sb.toString();
    }
}
