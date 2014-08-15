package com.slaven.radja.autoskola.activities;

import android.os.Bundle;

import com.slaven.radja.autoskola.R;

/**
 * Created by Computer on 04/08/2014.
 */
public class Info extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        rootView = findViewById(R.id.root_view);
        setBackground();
    }
}
