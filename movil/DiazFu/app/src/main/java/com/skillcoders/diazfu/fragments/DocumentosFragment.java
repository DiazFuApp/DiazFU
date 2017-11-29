package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.DocumentosAdapter;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.DocumentosRest;
import com.skillcoders.diazfu.fragments.interfaces.DocumentosInterface;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by saurett on 24/02/2017.
 */

public class DocumentosFragment extends Fragment implements View.OnClickListener {

    private static Documentos _MAIN_DOCUMENTOS;
    private static List<Documentos> documentosList;
    private static RecyclerView recyclerView;
    private static DocumentosAdapter documentosAdapter;
    private static DocumentosInterface activityInterface;
    public static LinearLayout linearLayout;


    /**
     * Implementaciones REST
     */
    private static DocumentosRest documentosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_documentos, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        _MAIN_DOCUMENTOS = (Documentos) getActivity().getIntent().getSerializableExtra(Constants.KEY_MAIN_DOCUMENTOS);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_documentos);
        documentosAdapter = new DocumentosAdapter();
        documentosAdapter.setOnClickListener(this);

        documentosRest = ApiUtils.getDocumentosRest();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.listadoDocumentos();
    }


    public static void listadoDocumentos() {

        Documentos documento = _MAIN_DOCUMENTOS;
        documento.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE);

        documentosRest.getDocumentos(documento).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Documentos>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Documentos> data) {

                        documentosAdapter = new DocumentosAdapter();
                        documentosList = new ArrayList<>();
                        documentosList.addAll(data);
                        onPreRender();

                        ListadoDocumentosFragment.btnAgegar.setText("Actualizar");
                        if (documentosList.size() == 0) {
                            linearLayout.setVisibility(View.VISIBLE);
                            ListadoDocumentosFragment.btnAgegar.setText("Agregar");
                        }
                    }
                });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private static void onPreRender() {

        Collections.sort(documentosList, new Comparator<Documentos>() {
            @Override
            public int compare(Documentos o1, Documentos o2) {
                return (o1.getId().compareTo(o2.getId()));
            }
        });

        documentosAdapter.addAll(documentosList);
        recyclerView.setAdapter(documentosAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (DocumentosInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_editar_cliente:
                activityInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_cliente:
                activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea elminar?");
                break;
        }
    }
}
