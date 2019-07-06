package com.contextlogic.wish.ui.viewpager;

public interface BaseTabStripInterface {
    int getCurrentIndex();

    int getTabAreaOffset();

    int getTabAreaSize();

    void hideTabArea(boolean z);

    void setTabAreaOffset(int i);

    void showTabArea(boolean z);
}
