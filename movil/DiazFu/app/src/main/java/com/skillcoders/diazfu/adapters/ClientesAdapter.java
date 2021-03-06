package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.fragments.ClientesFragment;
import com.skillcoders.diazfu.fragments.GruposFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Clientes> clientes = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnAutorizar;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_cliente);
            btnAutorizar = (Button) itemView.findViewById(R.id.item_btn_autorizar_cliente);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_cliente);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_cliente);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clientes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Clientes item = clientes.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        switch (item.getIdEstatus()) {
            case Constants.DIAZFU_WEB_AUTORIZADO:
                holder.btnAutorizar.setVisibility(View.GONE);
                break;
            case Constants.DIAZFU_WEB_SIN_AUTORIZACION:
                holder.btnAutorizar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        holder.btnAutorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                ClientesFragment.onListenerAction(decodeItem);
            }
        });

        holder.txtNombre.setText(item.getNombre());
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ClientesFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ClientesFragment.onListenerAction(decodeItem);
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
