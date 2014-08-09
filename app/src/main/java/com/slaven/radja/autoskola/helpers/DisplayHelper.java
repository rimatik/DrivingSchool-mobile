package com.slaven.radja.autoskola.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by fgrbac on 09/08/14.
 */
public class DisplayHelper {

    public static int getDpToPx(Context context, int dp) {

        Resources r = context.getResources();
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());

    }
}
