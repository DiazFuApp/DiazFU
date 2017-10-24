package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.fragments.GruposFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Grupos> grupos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtEstatus;
        Button btnAutorizar;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_grupo);
            txtEstatus = (TextView) itemView.findViewById(R.id.item_estatus_grupo);
            btnAutorizar = (Button) itemView.findViewById(R.id.item_btn_autorizan_grupo);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_grupo);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_grupo);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Grupos getItemByPosition(int position) {
        return grupos.get(position);
    }

    public void addAll(List<Grupos> _grupos) {
        this.grupos.addAll(_grupos);
    }

    public void remove(int position) {
        this.grupos.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grupos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Grupos item = grupos.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        String estatus = "";

        switch (item.getIdEstatus()) {
            case Constants.ESTATUS_AUTORIZADO:
                estatus = Constants.ESTATUS_AUTORIZADO_STR;
                holder.btnAutorizar.setVisibility(View.GONE);
                break;
            case Constants.ESTATUS_NO_AUTORIZADO:
                holder.btnAutorizar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        holder.txtNombre.setText(item.getNombre());
        holder.txtEstatus.setText(estatus);

        holder.btnAutorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                GruposFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                GruposFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                GruposFragment.onListenerAction(decodeItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return grupos == null ? 0 : grupos.size();
    }


}

