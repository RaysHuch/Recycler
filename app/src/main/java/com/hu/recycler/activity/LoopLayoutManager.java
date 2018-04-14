package com.hu.recycler.activity;

import android.content.Context;
import android.view.View;

public class LoopLayoutManager extends ViewPagerLayoutManager {
    public LoopLayoutManager(Context context) {
        super(context, VERTICAL, false);
        setInfinite(true);
    }

    @Override
    protected float setInterval() {
        return mDecoratedMeasurement;
    }

    @Override
    protected void setItemViewProperty(View itemView, float targetOffset) {

    }

    @Override
    protected float getDistanceRatio() {
        return 1f;
    }
}
