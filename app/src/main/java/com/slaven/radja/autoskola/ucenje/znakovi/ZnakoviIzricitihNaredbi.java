package com.slaven.radja.autoskola.ucenje.znakovi;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


import com.slaven.radja.autoskola.R;

/**
 * Created by Computer on 10/08/2014.
 */
public class ZnakoviIzricitihNaredbi extends Activity implements
        AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.znakovi_izricitih_naredbi);

        mSwitcher = (ImageSwitcher) findViewById(R.id.imsZnakovi);
        mSwitcher.setFactory(this);
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        Gallery g = (Gallery) findViewById(R.id.gaZnakovi);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView parent, View v, int position, long id) {
        mSwitcher.setImageResource(mImageIds[position]);
    }

    public void onNothingSelected(AdapterView parent) {
    }

    public View makeView() {
        ImageView i = new ImageView(this);
        i.setBackgroundColor(0xFF000000);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        return i;
    }

    private ImageSwitcher mSwitcher;

    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.drawable.background_autoskola_p1);

            return i;
        }

        private Context mContext;

    }

    private Integer[] mThumbIds = {
            R.drawable.raskrizje_sa_sporednom_cestom_pod_pravim_kutom_thumb, R.drawable.obilijezen_pjesacki_prijelaz_thumb,
            R.drawable.raskrizje_iste_vaznosti_thumb, R.drawable.spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane_thumb,
         };

    private Integer[] mImageIds = {
            R.drawable.raskrizje_sa_sporednom_cestom_pod_pravim_kutom_img, R.drawable.obilijezen_pjesacki_prijelaz_img, R.drawable.raskrizje_iste_vaznosti_img,
            R.drawable.spajanje_sporedne_ceste_pod_pravim_kutom_s_lijeve_strane_img};

}
