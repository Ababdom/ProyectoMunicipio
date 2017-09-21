package com.arbp.richard.proyectomunicipios.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arbp.richard.proyectomunicipios.ActivityMunicipios;
import com.arbp.richard.proyectomunicipios.R;
import com.arbp.richard.proyectomunicipios.model.Municipio;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ListaMunicipiosAdapter extends RecyclerView.Adapter<ListaMunicipiosAdapter.ViewHolder>{

    private ArrayList<Municipio>dataset;
    private Context context;
    private OnMunicipioItemClickListener onMunicipioItemClickListener;

    public ListaMunicipiosAdapter(ActivityMunicipios mainActivity, ActivityMunicipios activity){
        this.context = mainActivity;
        dataset = new ArrayList<>();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_municipios, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Municipio m = dataset.get(position);
        holder.provincia.setText("Provincia: "+m.getPROVINCIA());
        holder.capital.setText("Capital: "+m.getCAPITAL());
        holder.municipio.setText("Municipio: "+m.getMUNICIPIO());

        Glide.with(context)
                //.load("http://webservice111.esy.es/departamentos/"+ m.getDEPARTAMEN()+".png")
                .load("http://i68.tinypic.com/24f0g80.png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void adicionarListaMunicipio(ArrayList<Municipio> lista){
        dataset.addAll(lista);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView provincia;
        private TextView capital;
        private TextView municipio;
        View cardView;

        public ViewHolder(View itemview) {
            super(itemview);

            cardView = itemview.findViewById(R.id.cardV);
            fotoImageView= (ImageView)itemview.findViewById(R.id.fotoDepartamentoView);
            provincia = (TextView)itemview.findViewById(R.id.provincia);
            capital=(TextView)itemview.findViewById(R.id.capital);
            municipio=(TextView)itemview.findViewById(R.id.municipio);

        }
    }
}