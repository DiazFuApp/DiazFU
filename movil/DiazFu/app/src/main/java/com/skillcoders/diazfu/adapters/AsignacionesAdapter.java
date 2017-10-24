package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.fragments.AsignacionGrupoFragment;
import com.skillcoders.diazfu.fragments.ClientesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class AsignacionesAdapter extends RecyclerView.Adapter<AsignacionesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Clientes> clientes = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtResponsable;
        Button btnResponsable;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_asignacion_grupo);
            txtResponsable = (TextView) itemView.findViewById(R.id.item_responsable_asignacion_grupo);
            btnResponsable = (Button) itemView.findViewById(R.id.item_btn_responsable_asignacion_grupo);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_asignacion_grupo);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Clientes getItemByPosition(int position) {
        return clientes.get(position);
    }

    public void addAll(List<Clientes> _administradores) {
        this.clientes.addAll(_administradores);
    }

    public void remove(int position) {
        this.clientes.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignaciones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Clientes item = clientes.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        String responsable = "";

        switch (item.getIdEstatus()) {
            case Constants.ESTATUS_RESPONSABLE:
                responsable = Constants.ESTATUS_RESPONSABLE_STR;
                holder.btnResponsable.setVisibility(View.GONE);
                break;
            default:
                holder.btnResponsable.setVisibility(View.VISIBLE);
                break;
        }

        holder.txtNombre.setText(item.getNombre());
        holder.txtResponsable.setText(responsable);
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                AsignacionGrupoFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnResponsable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                AsignacionGrupoFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes == null ? 0 : clientes.size();
    }

    public void removeItem(int position) {
        this.clientes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
