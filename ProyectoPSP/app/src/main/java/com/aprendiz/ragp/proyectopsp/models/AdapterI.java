package com.aprendiz.ragp.proyectopsp.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aprendiz.ragp.proyectopsp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterI extends RecyclerView.Adapter<AdapterI.Holder>{
    List<CProyecto> proyectoList =new ArrayList<>();
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void itemClick(int position);
    }

    public AdapterI(List<CProyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(proyectoList.get(position));
    }

    @Override
    public int getItemCount() {
        return proyectoList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtNombre = itemView.findViewById(R.id.txtProject);
        public Holder(View itemView, final OnItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(CProyecto cProyecto){
            txtNombre.setText(cProyecto.getNombre());

        }
    }
}
