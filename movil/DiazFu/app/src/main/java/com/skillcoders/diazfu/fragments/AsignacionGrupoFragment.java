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

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.AsignacionesAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
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

public class AsignacionGrupoFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static List<Clientes> clientesList;
    private static RecyclerView recyclerView;
    public static AsignacionesAdapter asignacionesAdapter;
    private static MainRegisterInterface activityInterface;

    public static List<IntegrantesGrupos> integrantesGrupos;

    /**
     * Implementaciones REST
     */
    private static IntegrantesGruposRest integrantesRest;
    private static ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        asignacionesAdapter = new AsignacionesAdapter();
        asignacionesAdapter.setOnClickListener(this);

        clientesRest = ApiUtils.getClientesRest();
        integrantesRest = ApiUtils.getIntegrantesGrupos();

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

        integrantesGrupos = new ArrayList<>();

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.listadoIntegrantes();
                break;
            default:
                break;
        }
    }

    private void listadoIntegrantes() {

        final Grupos grupos = (Grupos) _MAIN_DECODE.getDecodeItem().getItemModel();

        integrantesRest.getIntegrantesGrupo(grupos.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<IntegrantesGrupos>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<IntegrantesGrupos> integrantesGruposes) {

                        asignacionesAdapter = new AsignacionesAdapter();
                        clientesList = new ArrayList<>();

                        for (IntegrantesGrupos integrante :
                                integrantesGruposes) {

                            Clientes cliente = new Clientes();
                            cliente.setNombre(integrante.getCliente());
                            cliente.setId(integrante.getIdCliente());
                            cliente.setIdEstatus((grupos.getIdClienteResponsable().equals(cliente.getId())
                                    ? Constants.ESTATUS_RESPONSABLE : Constants.ESTATUS_NO_RESPONSABLE));

                            integrante.setIdEstatus(Constants.ACCION_VER);

                            FormularioGruposFragment._clienteResponsable = (grupos.getIdClienteResponsable().equals(cliente.getId())
                                    ? cliente.getId() : FormularioGruposFragment._clienteResponsable);

                            clientesList.add(cliente);
                            integrantesGrupos.add(integrante);
                        }

                        onPreRenderListadoIntegrantes();

                        if (integrantesGruposes.size() > 0)
                            AsignacionesGruposFragment.showMessageAsignacion(
                                    (null != grupos.getIdClienteResponsable()) ? View.GONE : View.GONE,
                                    (null != grupos.getIdClienteResponsable()) ? "" : "No existe responsable asignado");
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

        asignacionesAdapter.addAll(clientesList);
        recyclerView.setAdapter(asignacionesAdapter);

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
            case R.id.item_btn_responsable_asignacion_grupo:
                activityInterface.showQuestion("Autorizar", "¿Esta seguro que desea hacer responsable?");
                break;
            case R.id.item_btn_eliminar_asignacion_grupo:
                activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea elminar?");
                break;
        }
    }
}
