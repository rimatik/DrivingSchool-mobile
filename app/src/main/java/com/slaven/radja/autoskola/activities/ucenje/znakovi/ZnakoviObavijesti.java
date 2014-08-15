package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.app.Activity;
import android.os.Bundle;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviObavijesti extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.znakovi);
        rootView = findViewById(R.id.root_view);
        setBackground();
    }

}
