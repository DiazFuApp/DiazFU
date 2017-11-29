package com.skillcoders.diazfu.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.fragments.ClientesFragment;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 14/01/2016.
 */
public class DocumentosAdapter extends RecyclerView.Adapter<DocumentosAdapter.ViewHolder> {

    View.OnClickListener onClickListener;
    List<Documentos> documentos = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        BootstrapThumbnail btnEditar;

        public ViewHolder(View itemView) {
            super(itemView);
            
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Documentos getItemByPosition(int position) {
        return documentos.get(position);
    }

    public void addAll(List<Documentos> _data) {
        this.documentos.addAll(_data);
    }

    public void remove(int position) {
        this.documentos.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_documentos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Documentos item = documentos.get(position);
        /**Llenar el objeto que sera enviado al fragmento**/
        final DecodeItemHelper decodeItem = new DecodeItemHelper();

        decodeItem.setItemModel(item);
        decodeItem.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return documentos == null ? 0 : documentos.size();
    }

    public void removeItem(int position) {
        this.documentos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

}
