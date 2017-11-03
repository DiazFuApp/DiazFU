package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPagosGrupalesFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tolCantidaPago;
    private static Spinner spinnerTipoPago, spinnerClientes;

    private static List<String> clientesList;
    private List<Clientes> clientes;
    public static Clientes _clienteSeleccionado;

    private static List<String> tiposPagosList;
    public static String _tipoPagoSeleccionado;

    /**
     * Implementaciones REST
     */
    private IntegrantesGruposRest integrantesGruposRest;
    private GruposRest gruposRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagos_grupales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tolCantidaPago = (TextInputLayout) view.findViewById(R.id.cantidad_pago_pago_grupal);

        spinnerClientes = (Spinner) view.findViewById(R.id.spinner_cliente_pago_grupal);
        spinnerClientes.setOnItemSelectedListener(this);
        spinnerTipoPago = (Spinner) view.findViewById(R.id.spinner_tipo_pago_pago_grupal);
        spinnerTipoPago.setOnItemSelectedListener(this);


        integrantesGruposRest = ApiUtils.getIntegrantesGruposRest();
        gruposRest = ApiUtils.getGruposRest();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.onPreRender();
    }

    private void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_PAGAR:
                this.listadoClientes();
                this.listadoTiposPagos();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void listadoClientes() {
        PrestamosGrupales prestamoGrupal = ((PrestamosGrupales) _MAIN_DECODE.getDecodeItem().getItemModel());

        integrantesGruposRest.getIntegrantesGrupo(prestamoGrupal.getIdGrupo())
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
                    public void onNext(List<IntegrantesGrupos> integrantesGrupos) {

                        clientesList = new ArrayList<>();
                        clientes = new ArrayList<>();

                        clientesList.add("Seleccione ...");

                        for (IntegrantesGrupos integrante :
                                integrantesGrupos) {
                            Clientes cliente = new Clientes();
                            cliente.setId(integrante.getId());
                            cliente.setNombre(integrante.getCliente());

                            clientes.add(cliente);
                            clientesList.add(cliente.getNombre());
                        }

                        onCargarSpinnerClientes();
                    }
                });
    }

    private void listadoTiposPagos() {
        tiposPagosList = new ArrayList<>();
        tiposPagosList.add("Seleccione ...");
        tiposPagosList.add("Efectivo");
        tiposPagosList.add("Déposito");
        tiposPagosList.add("Cheque");

        onCargarSpinnerTiposPagos();
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerTiposPagos() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, tiposPagosList);

        int itemSelection = onPreRenderSelectTiposPagos();

        spinnerTipoPago.setAdapter(adapter);
        spinnerTipoPago.setSelection(itemSelection);
    }

    private int onPreRenderSelectTiposPagos() {
        int item = 0;

        return item;
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerClientes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, clientesList);

        int itemSelection = onPreRenderSelectClientes();

        spinnerClientes.setAdapter(adapter);
        spinnerClientes.setSelection(itemSelection);
    }

    private int onPreRenderSelectClientes() {
        int item = 0;

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_grupo_prestamo_grupal:
                if (position > 0) {
                    Clientes grupo = clientes.get(position - 1);
                    _clienteSeleccionado = grupo;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public static boolean validarDatosEdicion() {
        boolean valido = false;


        return valido;
    }
}
