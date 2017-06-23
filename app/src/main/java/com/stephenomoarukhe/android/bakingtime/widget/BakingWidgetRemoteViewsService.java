package com.stephenomoarukhe.android.bakingtime.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Omoarukhe on 06/21/2017.
 */

public class BakingWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetRemoteViewsFactory(this.getApplicationContext());
    }
}
