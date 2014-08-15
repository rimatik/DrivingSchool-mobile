package com.slaven.radja.autoskola.activities.ucenje;

import android.app.Activity;
import android.os.Bundle;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class ZaustavljanjeiParkiranje extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zaustavljanje_i_parkiranje);
        rootView = findViewById(R.id.root_view);
        setBackground();
    }

}
