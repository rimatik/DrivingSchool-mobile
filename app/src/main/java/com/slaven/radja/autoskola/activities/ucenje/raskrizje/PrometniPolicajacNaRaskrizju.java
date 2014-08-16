package com.slaven.radja.autoskola.activities.ucenje.raskrizje;

import android.os.Bundle;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 10/08/2014.
 */
public class PrometniPolicajacNaRaskrizju extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prometni_policajac_na_raskrizju);
        rootView = findViewById(R.id.root_view);
        setBackground();
    }
}
