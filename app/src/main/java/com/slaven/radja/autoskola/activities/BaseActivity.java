package com.slaven.radja.autoskola.activities;

import android.app.Activity;
import android.view.View;

import com.slaven.radja.autoskola.Constants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.PreferencesHelper;

/**
 * Created by Computer on 15/08/14.
 */
public class BaseActivity extends Activity {

    protected View rootView;

    protected void setBackground() {
        if (PreferencesHelper.getBoolean(this, Constants.KEY_BLACK_BACKGROUND, false)) {
            rootView.setBackgroundColor(0X11AFE3);
        } else {
            rootView.setBackgroundColor(0X000000);
        }
    }
}
