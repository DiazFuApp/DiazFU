package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.fragments.PrestamosIndividualesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class PrestamosIndividualesAdapter extends RecyclerView.Adapter<PrestamosIndividualesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<PrestamosIndividuales> prestamosIndividuales = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnPagar;
        Button btnEntregar;
        Button btnAutorizar;
        Button btnVer;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_prestamo_individual);
            btnPagar = (Button) itemView.findViewById(R.id.item_btn_pagar_prestamo_individual);
            btnEntregar = (Button) itemView.findViewById(R.id.item_btn_entregar_prestamo_individual);
            btnAutorizar = (Button) itemView.findViewById(R.id.item_btn_autorizar_prestamo_individual);
            btnVer = (Button) itemView.findViewById(R.id.item_btn_ver_prestamo_individual);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public PrestamosIndividuales getItemByPosition(int position) {
        return prestamosIndividuales.get(position);
    }

    public void addAll(List<PrestamosIndividuales> _data) {
        this.prestamosIndividuales.addAll(_data);
    }

    public void remove(int position) {
        this.prestamosIndividuales.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestamos_individuales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PrestamosIndividuales item = prestamosIndividuales.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getCliente());

        switch (item.getIdEstatus()) {
            case Constants.DIAZFU_WEB_SIN_AUTORIZACION:
                holder.btnVer.setVisibility(View.GONE);
                holder.btnAutorizar.setVisibility(View.VISIBLE);
                holder.btnEntregar.setVisibility(View.GONE);
                holder.btnPagar.setVisibility(View.GONE);
                break;
            case Constants.DIAZFU_WEB_AUTORIZADO:
                holder.btnVer.setVisibility(View.GONE);
                holder.btnAutorizar.setVisibility(View.GONE);
                holder.btnEntregar.setVisibility(View.VISIBLE);
                holder.btnPagar.setVisibility(View.GONE);
                break;
            case Constants.DIAZFU_WEB_ENTREGADO:
                holder.btnVer.setVisibility(View.VISIBLE);
                holder.btnAutorizar.setVisibility(View.GONE);
                holder.btnEntregar.setVisibility(View.GONE);
                holder.btnPagar.setVisibility(View.VISIBLE);
                break;
            default:
                holder.btnVer.setVisibility(View.VISIBLE);
                holder.btnAutorizar.setVisibility(View.GONE);
                holder.btnEntregar.setVisibility(View.GONE);
                holder.btnPagar.setVisibility(View.GONE);
                break;
        }

        holder.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosIndividualesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnEntregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosIndividualesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnAutorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosIndividualesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosIndividualesFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prestamosIndividuales == null ? 0 : prestamosIndividuales.size();
    }


}

