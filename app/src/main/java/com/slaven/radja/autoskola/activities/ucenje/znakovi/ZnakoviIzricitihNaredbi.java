package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;

import com.slaven.radja.autoskola.DatabaseConstants;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;
import com.slaven.radja.autoskola.adapters.SignsAdapter;
import com.slaven.radja.autoskola.helpers.DbHelper;
import com.slaven.radja.autoskola.models.Znak;

import java.util.List;

/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviIzricitihNaredbi extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.znakovi_izricitih_naredbi, null, false);
    }

    }

