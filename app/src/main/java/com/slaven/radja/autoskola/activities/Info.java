package com.slaven.radja.autoskola.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;

/**
 * Created by Computer on 04/08/2014.
 */
public class Info extends BaseActivity {

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        rootView = findViewById(R.id.root_view);
        setBackground();

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        tvInfo.setText("\n Ova aplikacija je namijenjena testiranju znanja koje je potrebno kako bi se položio pismeni ispit" +
                " za autoškolu u Republici Hrvatskoj.\n\n"
                        + "U nju je implemenitrana baza podataka koja sadrži neke od podataka koji se pojavljuju na službenim testovima.\n\n"
                        + "Napomena:\n\n"
                        + "- aplikacija služi isključivo kao dodatni alat pri učenju, nikako kao i jedini izvor infromacija");

    }
}
