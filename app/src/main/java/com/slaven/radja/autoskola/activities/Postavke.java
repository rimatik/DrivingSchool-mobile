package com.slaven.radja.autoskola.activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.slaven.radja.autoskola.Constants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.helpers.PreferencesHelper;

/**
 * Created by Computer on 04/08/2014.
 */
public class Postavke extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postavke);
        rootView = findViewById(R.id.root_view);
        setBackground();
        CheckBox checkBox =  (CheckBox) findViewById(R.id.cbBlack);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    rootView.setBackgroundResource(R.drawable.background_autoskola_black);
                    PreferencesHelper.putBoolean(Postavke.this, Constants.KEY_BLACK_BACKGROUND, true);
                } else {
                    rootView.setBackgroundResource(R.drawable.background_autoskola_p1);
                    PreferencesHelper.putBoolean(Postavke.this, Constants.KEY_BLACK_BACKGROUND, false);
                }
            }
        });
        checkBox.setChecked(PreferencesHelper.getBoolean(this, Constants.KEY_BLACK_BACKGROUND, false));
    }


}
