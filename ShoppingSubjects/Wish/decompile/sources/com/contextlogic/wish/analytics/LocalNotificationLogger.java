package com.contextlogic.wish.analytics;

import com.contextlogic.wish.api.service.standalone.LogLocalNotificationService;

public class LocalNotificationLogger {

    public enum ActionType {
        SCHEDULED,
        IMPRESSION,
        CLICKED
    }

    public static void logAction(String str, ActionType actionType) {
        String str2;
        switch (actionType) {
            case SCHEDULED:
                str2 = "scheduled";
                break;
            case IMPRESSION:
                str2 = "impression";
                break;
            case CLICKED:
                str2 = "clicked";
                break;
            default:
                str2 = null;
                break;
        }
        new LogLocalNotificationService().requestService(str, str2, null, null);
    }
}
