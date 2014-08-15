package com.slaven.radja.autoskola.activities;

import android.app.Activity;
import android.view.View;

import com.slaven.radja.autoskola.Constants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.PreferencesHelper;

/**
 * Created by fgrbac on 15/08/14.
 */
public class BaseActivity extends Activity {

    protected View rootView;

    protected void setBackground() {
        if (PreferencesHelper.getBoolean(this, Constants.KEY_BLACK_BACKGROUND, false)) {
            rootView.setBackgroundResource(R.drawable.background_autoskola_black);
        } else {
            rootView.setBackgroundResource(R.drawable.background_autoskola_p1);
        }
    }
}
