package com.arbp.richard.proyectomunicipios.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arbp.richard.proyectomunicipios.MainActivity;
import com.arbp.richard.proyectomunicipios.R;
import com.arbp.richard.proyectomunicipios.model.Departamento;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ListaDepartamentoAdapter extends RecyclerView.Adapter<ListaDepartamentoAdapter.ViewHolder> {

    private ArrayList<Departamento> dataset;
    private Context context;
    private OnDepartamentosSelectedListener onDepartamentosSelectedListener;

    public interface OnDepartamentosSelectedListener {
        void onDepartamentosSelected (Departamento departamento);
    }

    public ListaDepartamentoAdapter(MainActivity mainActivity, OnDepartamentosSelectedListener listener) {
        this.context = mainActivity;
        this.dataset = new ArrayList<>();
        this.onDepartamentosSelectedListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_departamento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Departamento d = dataset.get(position);
        holder.departamento.setText("Departamento: "+ d.getNombre());
        //holder.capital.setText("Capital: "+m.getCAPITAL());
        holder.cantidad.setText("provincias: "+ d.getNroProvincias());

        Glide.with(context)
                .load("http://webservice111.esy.es/departamentos/"+ d.getNombre()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        holder.setDeviceSelectedListener(d, onDepartamentosSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        View CardView;
        private ImageView fotoImageView;
        private TextView departamento;
        private TextView capital;
        private TextView cantidad;

        View cardView;

        public ViewHolder(View itemView){
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            fotoImageView = (ImageView)itemView.findViewById(R.id.fotoImageView);
            departamento = (TextView) itemView.findViewById(R.id.departamento);
            //capital = (TextView) itemView.findViewById(R.id.capital);
            cantidad = (TextView) itemView.findViewById(R.id.provincias);

        }

        public void setDeviceSelectedListener(final Departamento d, final OnDepartamentosSelectedListener onDepartamentsSelectedListemer) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDepartamentosSelectedListener.onDepartamentosSelected(d);
                }
            });
        }
    }
    public void add (Departamento departamento){
        dataset.add(departamento);
        notifyDataSetChanged();
    }
    public void setDataset(ArrayList<Departamento>departamentos){
        if (departamentos == null){
            dataset = new ArrayList<>();
            }else{
            dataset = departamentos;
        }
        notifyDataSetChanged();

    }
    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }
}