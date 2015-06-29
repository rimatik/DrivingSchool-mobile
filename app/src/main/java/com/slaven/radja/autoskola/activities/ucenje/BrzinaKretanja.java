package com.slaven.radja.autoskola.activities.ucenje;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;

/**
 * Created by Computer on 05/08/2014.
 */
public class BrzinaKretanja extends BaseActivity {

    TextView tvBrzina;
    String brzina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brzina_kretanja);
        rootView = findViewById(R.id.root_view);
        setBackground();
        Resources res = getResources();
        tvBrzina = (TextView) findViewById(R.id.tvBrzinaKretanja);
        brzina = res.getString(R.string.brzina_kretanja);
        tvBrzina.setText(brzina);
    }

}