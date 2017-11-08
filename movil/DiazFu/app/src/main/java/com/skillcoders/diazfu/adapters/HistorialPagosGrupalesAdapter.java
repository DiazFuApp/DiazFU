package com.skillcoders.diazfu.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.fragments.IntegrantesPlazosPrestamosGrupalesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.CommonUtils;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class HistorialPagosGrupalesAdapter extends RecyclerView.Adapter<HistorialPagosGrupalesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Pagos> pagos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPlazos;
        TextView txtMonto;
        TextView txtFechaProgramada;
        TextView txtEstatus;
        Button btnDetalle;

        public ViewHolder(View itemView) {
            super(itemView);

            txtPlazos = (TextView) itemView.findViewById(R.id.item_plazo_historial_pago_grupal);
            txtMonto = (TextView) itemView.findViewById(R.id.item_monto_historial_pago_grupal);
            txtFechaProgramada = (TextView) itemView.findViewById(R.id.item_fecha_programada_historial_pago_grupal);
            txtEstatus = (TextView) itemView.findViewById(R.id.item_estatus_historial_pago_grupal);
            btnDetalle = (Button) itemView.findViewById(R.id.item_btn_detalle_historial_plazo_pago_grupal);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial_pagos_grupales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pagos item = pagos.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        Calendar itemActualDate = DateTimeUtils.getParseTimeFromSQL(DateTimeUtils.getActualTime());
        Calendar itemDate = DateTimeUtils.getParseTimeFromSQL(item.getFechaProgramada());

        holder.txtFechaProgramada.setTextColor(ContextCompat.getColor(holder.txtFechaProgramada.getContext(), R.color.bootstrap_brand_secondary_text));

        switch (item.getIdEstatus()) {
            case Constants.DIAZFU_WEB_PENDIENTE:
                if ((itemActualDate.compareTo(itemDate) > 0) && (itemActualDate.get(Calendar.YEAR) == itemDate.get(Calendar.YEAR))) {
                    holder.txtFechaProgramada.setTextColor(ContextCompat.getColor(holder.txtFechaProgramada.getContext(), R.color.bootstrap_brand_danger));
                }
                break;
        }

        holder.txtPlazos.setText(item.getPlazo());
        holder.txtMonto.setText(CommonUtils.showMeTheMoney(item.getMontoAPagar()));
        holder.txtFechaProgramada.setText(DateTimeUtils.getParseTimeToAndroid(item.getFechaProgramada()));
        holder.txtEstatus.setText(Constants.TITLE_STATUS_DIAZFU_WEB.get(item.getIdEstatus()));

        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decodeItem.setIdView(view.getId());
                //IntegrantesPlazosPrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });

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
