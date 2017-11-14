package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPagosIndividualesFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static TextInputLayout tilCantidaPago, tilMontoRestante, tilMorosidad, tilPlazoActual;
    public static TextView txtEstatus;
    private static Spinner spinnerTipoPago, spinnerClientes;

    private static List<String> clientesList;
    private List<Clientes> clientes;
    public static Clientes _clienteSeleccionado;

    private static List<String> tiposPagosList;
    public static String _tipoPagoSeleccionado;

    public static Pagos _pagoActual;
    public static PrestamosIndividuales _prestamoIndividualActual;

    /**
     * Implementaciones REST
     */
    private ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagos_individuales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        txtEstatus = (TextView) view.findViewById(R.id.txt_historial_plazos_pagos_individuales);

        tilCantidaPago = (TextInputLayout) view.findViewById(R.id.cantidad_pago_pago_individual);
        tilMontoRestante = (TextInputLayout) view.findViewById(R.id.monto_restante_pago_individual);
        tilMorosidad = (TextInputLayout) view.findViewById(R.id.morosidad_pago_individual);
        tilPlazoActual = (TextInputLayout) view.findViewById(R.id.plazo_actual_pago_individual);

        spinnerClientes = (Spinner) view.findViewById(R.id.spinner_cliente_pago_individual);
        spinnerClientes.setOnItemSelectedListener(this);
        spinnerTipoPago = (Spinner) view.findViewById(R.id.spinner_tipo_pago_pago_individual);
        spinnerTipoPago.setOnItemSelectedListener(this);

        _pagoActual = new Pagos();
        _prestamoIndividualActual = new PrestamosIndividuales();

        clientesRest = ApiUtils.getClientesRest();

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

        tilMontoRestante.getEditText().setKeyListener(null);
        tilMorosidad.getEditText().setKeyListener(null);
        tilPlazoActual.getEditText().setKeyListener(null);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_historial_plazos_pagos_individuales_container, new HistorialPagosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_PAGOS_HISTORIAL_LISTADO);
        mainFragment.commit();
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
        _prestamoIndividualActual = ((PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel());

        clientesRest.getCliente(_prestamoIndividualActual.getIdCliente())
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
                    public void onNext(Clientes cliente) {

                        clientesList = new ArrayList<>();
                        clientes = new ArrayList<>();

                        clientesList.add("Seleccione ...");

                        Clientes miCliente = new Clientes();
                        miCliente.setId(miCliente.getId());
                        miCliente.setNombre(miCliente.getNombre());

                        clientes.add(cliente);
                        clientesList.add(cliente.getNombre());

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
        int item = 1;

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_cliente_pago_individual:
                if (position > 0) {
                    Clientes cliente = clientes.get(position - 1);
                    _clienteSeleccionado = cliente;
                    HistorialPagosIndividualesFragment.listadoIntegrantes(_clienteSeleccionado.getId());
                }
                break;
            case R.id.spinner_tipo_pago_pago_individual:
                if (position > 0) {
                    _tipoPagoSeleccionado = spinnerTipoPago.getSelectedItem().toString();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String cantidad = tilCantidaPago.getEditText().getText().toString();

        boolean a = ValidationUtils.esNumeroValido(tilCantidaPago, cantidad);
        boolean b = ValidationUtils.esSpinnerValido(spinnerTipoPago);
        boolean c = ValidationUtils.esSpinnerValido(spinnerClientes);
        boolean d = (HistorialPagosIndividualesFragment._pagosActuales.size() > 0);

        if (a && b && c && d) {

            int plazos = HistorialPagosIndividualesFragment._pagosActuales.size();
            Pagos plazo = HistorialPagosIndividualesFragment._pagosActuales.get(plazos - 1);
            /*
            Double montoAPagar = (plazo.getMontoPagado() > 0)
                    ? plazo.getMontoAPagar() - plazo.getMontoPagado() : plazo.getMontoAPagar();*/

            if (Double.valueOf(cantidad).compareTo(0.0) >= 0) {

                Pagos pago = new Pagos();
                pago.setMontoAPagar(Double.valueOf(cantidad));
                pago.setIdCliente(_clienteSeleccionado.getId());
                pago.setTipoPago(_tipoPagoSeleccionado);
                pago.setIdEstatus(Constants.DIAZFU_WEB_PAGADO);
                pago.setPlazo(plazo.getPlazo());

                _pagoActual = pago;

                valido = true;
            } else {
                Toast.makeText(tilCantidaPago.getContext(),
                        "La cantidad debe ser mayor a $ " + 0, Toast.LENGTH_SHORT).show();
                valido = false;
            }
        }

        return valido;
    }
}
