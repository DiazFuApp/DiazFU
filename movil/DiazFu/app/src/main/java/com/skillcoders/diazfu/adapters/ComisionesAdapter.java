package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Comisiones;
import com.skillcoders.diazfu.fragments.ActividadesFragment;
import com.skillcoders.diazfu.fragments.ComisionesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.CommonUtils;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class ComisionesAdapter extends RecyclerView.Adapter<ComisionesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Comisiones> comisiones = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtDescripcion;
        TextView txtEstatus;
        TextView txtMonto;
        Button btnPagar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_comision);
            txtDescripcion = (TextView) itemView.findViewById(R.id.item_descripcion_comision);
            txtEstatus = (TextView) itemView.findViewById(R.id.item_estatus_comision);
            txtMonto = (TextView) itemView.findViewById(R.id.item_monto_comision);
            btnPagar = (Button) itemView.findViewById(R.id.item_btn_pagar_comision);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Comisiones getItemByPosition(int position) {
        return comisiones.get(position);
    }

    public void addAll(List<Comisiones> _data) {
        this.comisiones.addAll(_data);
    }

    public void remove(int position) {
        this.comisiones.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comisiones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Comisiones item = comisiones.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);


        holder.txtNombre.setText(item.getPromotor());
        holder.txtDescripcion.setText(item.getDescripcion());
        holder.txtEstatus.setText(Constants.TITLE_STATUS_DIAZFU_WEB.get(item.getIdEstatus()));
        holder.txtMonto.setText(CommonUtils.showMeTheMoney(item.getComision()));

        switch (item.getIdEstatus()) {
            case Constants.DIAZFU_WEB_PENDIENTE:
                holder.btnPagar.setVisibility(View.VISIBLE);
                break;
            case Constants.DIAZFU_WEB_FINALIZADO:
                holder.btnPagar.setVisibility(View.GONE);
                break;
        }

        holder.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                ComisionesFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comisiones == null ? 0 : comisiones.size();
    }

    public void removeItem(int position) {
        this.comisiones.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
