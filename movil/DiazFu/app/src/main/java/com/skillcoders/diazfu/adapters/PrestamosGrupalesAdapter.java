package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.fragments.PrestamosGrupalesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvier on 04/09/2017.
 */

public class PrestamosGrupalesAdapter extends RecyclerView.Adapter<PrestamosGrupalesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<PrestamosGrupales> prestamosGrupales = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        Button btnPagar;
        Button btnEntregar;
        Button btnAutorizar;
        Button btnVer;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.item_nombre_prestamo_grupal);
            btnPagar = (Button) itemView.findViewById(R.id.item_btn_pago_prestamo_grupal);
            btnEntregar = (Button) itemView.findViewById(R.id.item_btn_entregar_prestamo_grupal);
            btnAutorizar = (Button) itemView.findViewById(R.id.item_btn_autorizar_prestamo_grupal);
            btnVer = (Button) itemView.findViewById(R.id.item_btn_ver_prestamo_grupal);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public PrestamosGrupales getItemByPosition(int position) {
        return prestamosGrupales.get(position);
    }

    public void addAll(List<PrestamosGrupales> _prestamosGrupales) {
        this.prestamosGrupales.addAll(_prestamosGrupales);
    }

    public void remove(int position) {
        this.prestamosGrupales.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prestamos_grupales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PrestamosGrupales item = prestamosGrupales.get(position);
        /**Llena el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.setText(item.getGrupo());

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

        holder.btnEntregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnEntregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnAutorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeItem.setIdView(v.getId());
                PrestamosGrupalesFragment.onListenerAction(decodeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prestamosGrupales == null ? 0 : prestamosGrupales.size();
    }


}

