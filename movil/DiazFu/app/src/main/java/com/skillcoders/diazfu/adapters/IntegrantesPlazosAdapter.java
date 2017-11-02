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
import com.skillcoders.diazfu.helpers.DecodeItemHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class IntegrantesPlazosAdapter extends RecyclerView.Adapter<IntegrantesPlazosAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<IntegrantesGrupos> integrantesGrupos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnInspeccionar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_integrante_plazo_entrega);
            btnInspeccionar = (Button) itemView.findViewById(R.id.item_btn_inspeccionar_integrante_plazo_entrega);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public IntegrantesGrupos getItemByPosition(int position) {
        return integrantesGrupos.get(position);
    }

    public void addAll(List<IntegrantesGrupos> _data) {
        this.integrantesGrupos.addAll(_data);
    }

    public void remove(int position) {
        this.integrantesGrupos.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integrantes_plazos_entrega, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final IntegrantesGrupos item = integrantesGrupos.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getCliente());

        holder.btnInspeccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                IntegrantesPlazosPrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return integrantesGrupos == null ? 0 : integrantesGrupos.size();
    }

    public void removeItem(int position) {
        this.integrantesGrupos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
