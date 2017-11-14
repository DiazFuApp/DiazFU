package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.fragments.IntegrantesPlazosPrestamosGrupalesFragment;
import com.skillcoders.diazfu.fragments.IntegrantesPlazosPrestamosIndividualesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class ClientesPlazosAdapter extends RecyclerView.Adapter<ClientesPlazosAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Clientes> clientes = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnInspeccionar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_cliente_plazo_entrega);
            btnInspeccionar = (Button) itemView.findViewById(R.id.item_btn_inspeccionar_cliente_plazo_entrega);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Clientes getItemByPosition(int position) {
        return clientes.get(position);
    }

    public void addAll(List<Clientes> _data) {
        this.clientes.addAll(_data);
    }

    public void remove(int position) {
        this.clientes.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clientes_plazos_entrega, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Clientes item = clientes.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getNombre());

        holder.btnInspeccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                IntegrantesPlazosPrestamosIndividualesFragment.onListenerAction(decodeItem);
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
