package com.contextlogic.wish.dialog;

public abstract class BaseChoice {
    private int mChoiceId;

    public BaseChoice(int i) {
        this.mChoiceId = i;
    }

    public int getChoiceId() {
        return this.mChoiceId;
    }
}
