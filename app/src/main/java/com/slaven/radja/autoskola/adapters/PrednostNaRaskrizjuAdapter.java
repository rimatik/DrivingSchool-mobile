package com.slaven.radja.autoskola.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slaven.radja.autoskola.R;
import com.slaven.radja.autoskola.models.Semafor;

import java.util.List;

/**
 * Created by Computer on 18/08/2014.
 */
public class PrednostNaRaskrizjuAdapter extends BaseAdapter {

    private Context context;
    private List<Semafor> semafori;

    public PrednostNaRaskrizjuAdapter(Context context, List<Semafor> semafori) {
        this.semafori = semafori;
        this.context = context;
    }

    @Override
    public int getCount() {
        return semafori.size();
    }

    @Override
    public Object getItem(int position) {
        return semafori.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Semafor currentSemafor = (Semafor) getItem(position);
        ViewHolder holderSemafor;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_trafficlights, viewGroup, false);
            holderSemafor = new ViewHolder(view);
            view.setTag(holderSemafor);
        } else {
            holderSemafor = (ViewHolder) view.getTag();
        }

        holderSemafor.trafficLightDescription.setText(currentSemafor.getName());

        if (currentSemafor.isHasImage()) {
            holderSemafor.trafficLightImage.setImageResource(currentSemafor.getId_img());
            holderSemafor.trafficLightImage.setVisibility(View.VISIBLE);
        } else {
            holderSemafor.trafficLightImage.setVisibility(View.GONE);

        }
        return view;
    }

    @Override
    public boolean isEnabled(int pos){return false;}


    static class ViewHolder {
        private ImageView trafficLightImage;
        private TextView trafficLightDescription;


        public ViewHolder(View view) {
            trafficLightImage = (ImageView) view.findViewById(R.id.traffic_image);
            trafficLightDescription = (TextView) view.findViewById(R.id.traffic_description);

        }
    }

}
