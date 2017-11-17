package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PrestamosIndividualesAdapter;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jvier on 05/09/2017.
 */

public class BusquedaProximosPagosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static NavigationDrawerInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;
    private Spinner spinnerCliente;
    private static List<String> clientesList;
    private List<Clientes> clientes;

    public static Clientes _clienteSeleccionado;

    /**
     * Implementaciones REST
     */
    private static ClientesRest clientesRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proximos_pagos_busqueda, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        spinnerCliente = (Spinner) view.findViewById(R.id.spinner_cliente_proximo_pago);
        spinnerCliente.setOnItemSelectedListener(this);

        clientesRest = ApiUtils.getClientesRest();

        this.listadoClientes();

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
    }

    private void listadoClientes() {
        clientesRest.getClientes().enqueue(new Callback<List<Clientes>>() {
            @Override
            public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {

                if (response.isSuccessful()) {
                    _clienteSeleccionado = new Clientes();
                    clientesList = new ArrayList<>();
                    clientes = new ArrayList<>();

                    clientesList.add("Seleccione ...");

                    for (Clientes data :
                            response.body()) {
                        clientesList.add(data.getNombre());
                        clientes.add(data);
                    }

                    onCargarSpinnerClientes();
                }
            }

            @Override
            public void onFailure(Call<List<Clientes>> call, Throwable t) {

            }
        });

    }

    private void onCargarSpinnerClientes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, clientesList);

        spinnerCliente.setAdapter(adapter);
        spinnerCliente.setSelection(0);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {

    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_pagar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_PAGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_entregar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_ENTREGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_autorizar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_AUTORIZAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_ver_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_VER, MainRegisterActivity.class);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_cliente_proximo_pago:
                if (position > 0) {
                    Clientes cliente = clientes.get(position - 1);
                    _clienteSeleccionado = cliente;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static Pagos pagosBusqueda() {
        Pagos data = new Pagos();

        if (null != _clienteSeleccionado.getId()) data.setIdCliente(_clienteSeleccionado.getId());

        return data;
    }
}
