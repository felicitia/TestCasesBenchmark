package com.contextlogic.wish.ui.bottomnavigation;

public class BottomNavigationHelper {
    protected BottomNavigationInterface mBottomNavigationInterface;

    public BottomNavigationHelper(BottomNavigationInterface bottomNavigationInterface) {
        this.mBottomNavigationInterface = bottomNavigationInterface;
    }

    public void popUpBottomNavigation(boolean z) {
        this.mBottomNavigationInterface.popInBottomNavigation(z);
    }

    public void popDownBottomNavigation(boolean z) {
        this.mBottomNavigationInterface.popOutBottomNavigation(z);
    }

    public void handleScrollChanged(int i) {
        if (Math.abs(i) <= 20) {
            return;
        }
        if (i > 0) {
            popDownBottomNavigation(true);
        } else if (i < 0) {
            popUpBottomNavigation(true);
        }
    }
}
