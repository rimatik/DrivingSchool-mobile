package com.slaven.radja.autoskola.activities.ucenje;

import android.os.Bundle;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class KruzniTokRotor extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kruzni_tok_rotor);
        rootView = findViewById(R.id.root_view);
        setBackground();
    }
}
