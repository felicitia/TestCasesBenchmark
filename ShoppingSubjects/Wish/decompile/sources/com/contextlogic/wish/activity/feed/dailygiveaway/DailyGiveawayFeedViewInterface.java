package com.contextlogic.wish.activity.feed.dailygiveaway;

import com.contextlogic.wish.api.model.WishCurrentDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;

public interface DailyGiveawayFeedViewInterface {
    void handleCurrentDailyGiveawayFailure();

    void handleCurrentDailyGiveawaySuccess(WishCurrentDailyGiveawayInfo wishCurrentDailyGiveawayInfo);

    void handleInfoDailyGiveawayFailure();

    void handleInfoDailyGiveawaySuccess(WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo);

    void handleUpcomingDailyGiveawayFailure();

    void handleUpcomingDailyGiveawaySuccess(WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo);
}
