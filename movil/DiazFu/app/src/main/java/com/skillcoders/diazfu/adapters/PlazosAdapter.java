package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.CommonUtils;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class PlazosAdapter extends RecyclerView.Adapter<PlazosAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Pagos> pagos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtPlazo;
        TextView txtMonto;
        TextView txtFechaPago;
        TextView txtEstatus;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_plazo_integrante);
            txtPlazo = (TextView) itemView.findViewById(R.id.item_plazo_plazo_integrante);
            txtMonto = (TextView) itemView.findViewById(R.id.item_monto_plazo_integrante);
            txtFechaPago = (TextView) itemView.findViewById(R.id.item_fecha_pago_plazo_integrante);
            txtEstatus = (TextView) itemView.findViewById(R.id.item_estatus_plazo_integrante);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Pagos getItemByPosition(int position) {
        return pagos.get(position);
    }

    public void addAll(List<Pagos> _data) {
        this.pagos.addAll(_data);
    }

    public void remove(int position) {
        this.pagos.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plazos_integrante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pagos item = pagos.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getCliente());
        holder.txtPlazo.setText(item.getPlazo());
        holder.txtMonto.setText(CommonUtils.showMeTheMoney(item.getMontoAPagar()));
        holder.txtFechaPago.setText(DateTimeUtils.getParseTimeToAndroid(item.getFechaProgramada()));
        holder.txtEstatus.setText(Constants.TITLE_STATUS_DIAZFU_WEB.get(item.getIdEstatus()));
    }

    @Override
    public int getItemCount() {
        return pagos == null ? 0 : pagos.size();
    }

    public void removeItem(int position) {
        this.pagos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
