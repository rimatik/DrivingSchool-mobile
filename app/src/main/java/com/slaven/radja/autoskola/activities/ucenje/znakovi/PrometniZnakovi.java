package com.slaven.radja.autoskola.activities.ucenje.znakovi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.activities.BaseActivity;
import com.slaven.radja.autoskola.aplication.AutoskolaApp;
import com.slaven.radja.autoskola.helpers.DbHelper;

import java.util.List;

/**
 * Created by Computer on 09/08/2014.
 */
public class PrometniZnakovi extends FragmentActivity implements CommunicatorZnakovi{

    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prometni_znakovi);
        viewPager = (ViewPager) findViewById(R.id.pager);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentAdapter(fragmentManager));


    }

    @Override
    public void respond(int i) {
        FragmentManager manager = getFragmentManager();
        android.app.Fragment f2;
       // f2 = manager.findFragmentById(R.id.fragmentSviZnakovi);
    }



}
class FragmentAdapter extends FragmentPagerAdapter {


    public FragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = null;
        if(i == 0){
          fragment = new FragmentZnakovi();

        }
        if(i == 1){
           fragment = new ZnakoviOpasnosti();

        }
        if(i == 2){
            fragment = new ZnakoviIzricitihNaredbi();

        }
        if(i == 3){
            fragment = new ZnakoviObavijesti();

        }
        if(i == 4){
            fragment = new ZnakoviZaVodjenjePrometa();

        }
        if(i == 5){
            fragment = new DopunskePloceUzZnakove();

        }
        AutoskolaApp.setSelectedFragmentId(fragment.getId());
        AutoskolaApp.setFragment(fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){
            return "Svi znakovi";
        }
        if(position == 1){
            return "Znakovi opasnosti";
        }
        if(position == 2){
            return "Znakovi izričitih naredbi";
        }
        if(position == 3){
            return "Znakovi obavijesti";
        }
        if(position == 4){
            return "Znakovi za vođenje prometa";
        }
        if(position == 5){
            return "Dopunske ploče uz znakove";
        }
        return  null;


    }

}