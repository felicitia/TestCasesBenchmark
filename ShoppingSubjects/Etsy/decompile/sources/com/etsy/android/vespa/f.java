package com.etsy.android.vespa;

import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;

/* compiled from: IServerDrivenActionDelegate */
public interface f {
    void performAction(PositionList positionList, ServerDrivenAction serverDrivenAction);

    void performActionWithToast(PositionList positionList, ServerDrivenAction serverDrivenAction, int i);
}
