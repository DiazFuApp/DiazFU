package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.fragments.PromotoresFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class PromotoresAdapter extends RecyclerView.Adapter<PromotoresAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Promotores> promotores = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_promotor);
            btnEditar = (Button) itemView.findViewById(R.id.item_btn_editar_promotor);
            btnEliminar = (Button) itemView.findViewById(R.id.item_btn_eliminar_promotor);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Promotores getItemByPosition(int position) {
        return promotores.get(position);
    }

    public void addAll(List<Promotores> _promotores) {
        this.promotores.addAll(_promotores);
    }

    public void remove(int position) {
        this.promotores.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotores, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Promotores item = promotores.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getNombre());
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PromotoresFragment.onListenerAction(decodeItem);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PromotoresFragment.onListenerAction(decodeItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return promotores == null ? 0 : promotores.size();
    }


}
