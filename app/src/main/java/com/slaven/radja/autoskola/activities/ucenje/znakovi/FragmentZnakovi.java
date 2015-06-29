package com.slaven.radja.autoskola.activities.ucenje.znakovi;



import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.aplication.AutoskolaApp;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by slaven.radja on 10.10.2014..
 */
public class FragmentZnakovi extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener {

    String[] menutitles;
    TypedArray menuIcons;
    CommunicatorZnakovi comm;
    int i;
    FragmentTransaction transaction;
    CustomAdapter adapter;
    private List<RowItem> rowItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_znak, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        comm = (CommunicatorZnakovi) getActivity();
        menutitles = getResources().getStringArray(R.array.titlesZnakoviAll);
        menuIcons = getResources().obtainTypedArray(R.array.icons);
        AutoskolaApp.setLength(menutitles.length);
        rowItems = new ArrayList<RowItem>();

        for (int i = 0; i < menutitles.length; i++) {
            RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
                    i, -1));

            rowItems.add(items);
        }

        adapter = new CustomAdapter(getActivity(), rowItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

            AutoskolaApp.setPosition(position);
            i = AutoskolaApp.getSelectedFragmentId();
            Fragment allSigns = new FragmentZnakoviPrikaz();
            transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frContainer,allSigns,"Svi znakovi prikaz").commit();
    }



}
