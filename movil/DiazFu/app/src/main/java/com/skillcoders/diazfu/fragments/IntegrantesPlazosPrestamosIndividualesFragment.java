package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skillcoders.diazfu.IntegrantesPlazosActivity;
import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.ClientesPlazosAdapter;
import com.skillcoders.diazfu.adapters.IntegrantesPlazosAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
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

public class IntegrantesPlazosPrestamosIndividualesFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static List<Clientes> clientesList;
    private static RecyclerView recyclerView;
    public static ClientesPlazosAdapter adapter;
    private static MainRegisterInterface activityInterface;
    /**
     * Implementaciones REST
     */
    private static ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        adapter = new ClientesPlazosAdapter();
        adapter.setOnClickListener(this);

        clientesRest = ApiUtils.getClientesRest();

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
        this.onPreRender();
    }

    private void onPreRender() {

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
            case Constants.ACCION_VER:
            case Constants.ACCION_ENTREGAR:
                this.listadoIntegrantes();
                break;
            default:
                break;
        }
    }

    private void listadoIntegrantes() {

        final PrestamosIndividuales prestamoIndividual = (PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel();

        clientesRest.getCliente(prestamoIndividual.getIdCliente())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Clientes>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Clientes clientes) {

                        adapter = new ClientesPlazosAdapter();
                        clientesList = new ArrayList<>();
                        clientes.setId(prestamoIndividual.getId());
                        clientesList.add(clientes);

                        onPreRenderListadoIntegrantes();
                    }
                });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    public static void onPreRenderListadoIntegrantes() {

        Collections.sort(clientesList, new Comparator<Clientes>() {
            @Override
            public int compare(Clientes o1, Clientes o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        adapter.addAll(clientesList);
        recyclerView.setAdapter(adapter);

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
            activityInterface = (MainRegisterActivity) getActivity();
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
            case R.id.item_btn_inspeccionar_cliente_plazo_entrega:
                activityInterface.openExternalActivity(Constants.ACCION_VER, IntegrantesPlazosActivity.class);
                break;
        }
    }
}
