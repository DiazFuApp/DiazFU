package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.fragments.ActividadesFragment;
import com.skillcoders.diazfu.fragments.ClientesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Actividades> actividades = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnEditar;
        Button btnVer;
        Button btnEliminar;
        Button btnFinalizar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_actividad);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_actividad);
            btnVer = (Button) itemView.findViewById(R.id.item_btn_ver_actividad);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_actividad);
            btnFinalizar = (Button) itemView.findViewById(R.id.item_btn_finalizar_actividad);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Actividades getItemByPosition(int position) {
        return actividades.get(position);
    }

    public void addAll(List<Actividades> _data) {
        this.actividades.addAll(_data);
    }

    public void remove(int position) {
        this.actividades.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividades, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Actividades item = actividades.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);


        holder.txtNombre.setText(item.getTitulo());

        switch (item.getIdEstatus()) {
            case Constants.DIAZFU_WEB_PENDIENTE:
                holder.btnEliminar.setVisibility(View.VISIBLE);
                holder.btnEditar.setVisibility(View.VISIBLE);
                holder.btnVer.setVisibility(View.VISIBLE);
                holder.btnFinalizar.setVisibility(View.VISIBLE);
                break;
            case Constants.DIAZFU_WEB_FINALIZADO:
                holder.btnEliminar.setVisibility(View.INVISIBLE);
                holder.btnEditar.setVisibility(View.INVISIBLE);
                holder.btnVer.setVisibility(View.VISIBLE);
                holder.btnFinalizar.setVisibility(View.INVISIBLE);
                break;
        }

        holder.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ActividadesFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ActividadesFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ActividadesFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ActividadesFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return actividades == null ? 0 : actividades.size();
    }

    public void removeItem(int position) {
        this.actividades.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
