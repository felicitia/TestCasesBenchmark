package com.wish.android.shaky;

class FeedbackItem {
    public final String description;
    public final int feedbackType;
    public final int icon;
    public final String title;

    FeedbackItem(String str, String str2, int i, int i2) {
        this.title = str;
        this.description = str2;
        this.icon = i;
        this.feedbackType = i2;
    }
}
