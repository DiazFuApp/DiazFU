package com.skillcoders.diazfu.adapters;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class IntegrantesCantidadesAdapter extends RecyclerView.Adapter<IntegrantesCantidadesAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<IntegrantesGrupos> integrantesGrupos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextInputLayout txtNombre;
        TextInputLayout txtCantidad;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextInputLayout) itemView.findViewById(R.id.item_txt_nombre_integrante_cantidad);
            txtCantidad = (TextInputLayout) itemView.findViewById(R.id.item_txt_cantidad_integrante_cantidad);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integrantes_cantidades, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final IntegrantesGrupos item = integrantesGrupos.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);

        holder.txtNombre.getEditText().setText(item.getCliente());
        holder.txtNombre.getEditText().setKeyListener(null);

        holder.txtCantidad.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null) ;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null) ;

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    integrantesGrupos.get(position).setCantidadOtorgada(Double.valueOf(editable.toString()));
                }
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
