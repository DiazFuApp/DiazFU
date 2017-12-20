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
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.services.SharedPreferencesService;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPrestamosIndividualesFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilMotivoPrstamo, tilCantidadSolicitada, tilGarantia, tilObservaciones;
    private static Spinner spinnerClientes;

    private static List<String> clientesList;
    private List<Clientes> clientes;

    public static PrestamosIndividuales _prestamoIndividualActual;
    public static Clientes _clienteSeleccionado;

    /**
     * Implementaciones REST
     */
    private PrestamosIndividualesRest prestamosIndividualesRest;
    private ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_individuales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());

        tilMotivoPrstamo = (TextInputLayout) view.findViewById(R.id.motivo_prestamos_prestamo_individual);
        tilCantidadSolicitada = (TextInputLayout) view.findViewById(R.id.cantidad_solicitada_prestamo_individual);
        tilGarantia = (TextInputLayout) view.findViewById(R.id.garantia_prestamo_individual);
        tilObservaciones = (TextInputLayout) view.findViewById(R.id.observaciones_prestamo_individual);

        spinnerClientes = (Spinner) view.findViewById(R.id.spinner_cliente_prestamo_individual);
        spinnerClientes.setOnItemSelectedListener(this);

        prestamosIndividualesRest = ApiUtils.getPrestamosIndividualesRest();
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
            case Constants.ACCION_EDITAR:
            case Constants.ACCION_VER:
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_ENTREGAR:
                this.obtenerGrupo();
                break;
            case Constants.ACCION_REGISTRAR:
                _prestamoIndividualActual = new PrestamosIndividuales();
                this.listadoClientes();
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

    private void obtenerGrupo() {
        PrestamosIndividuales prestamoIndividual = ((PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel());

        prestamosIndividualesRest.getPrestamosIndividuales(Long.valueOf(prestamoIndividual.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrestamosIndividuales>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PrestamosIndividuales prestamosIndividuales) {

                        _prestamoIndividualActual = prestamosIndividuales;

                        tilMotivoPrstamo.getEditText().setText(prestamosIndividuales.getMotivo());
                        tilCantidadSolicitada.getEditText().setText(prestamosIndividuales.getCantidadSolicitada().toString());
                        tilGarantia.getEditText().setText(prestamosIndividuales.getGarantia());
                        tilObservaciones.getEditText().setText(prestamosIndividuales.getObservaciones());

                        onPreRenderUI();

                        listadoClientes();
                    }
                });
    }

    private void onPreRenderUI() {
        tilMotivoPrstamo.getEditText().setKeyListener(null);
        tilCantidadSolicitada.getEditText().setKeyListener(null);
        tilGarantia.getEditText().setKeyListener(null);
        tilObservaciones.getEditText().setKeyListener(null);
    }

    private void listadoClientes() {
        clientesRest.getClientes().enqueue(new Callback<List<Clientes>>() {
            @Override
            public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {

                if (response.isSuccessful()) {
                    clientesList = new ArrayList<>();
                    clientes = new ArrayList<>();

                    clientesList.add("Seleccione ...");

                    switch (_MAIN_DECODE.getAccionFragmento()) {
                        case Constants.ACCION_EDITAR:
                        case Constants.ACCION_VER:
                        case Constants.ACCION_AUTORIZAR:
                        case Constants.ACCION_ENTREGAR:
                            for (Clientes cliente :
                                    response.body()) {
                                if (cliente.getId().equals(((PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel()).getIdCliente())) {
                                    clientesList.add(cliente.getNombre());
                                    clientes.add(cliente);
                                }
                            }
                            break;
                        case Constants.ACCION_REGISTRAR:
                            for (Clientes cliente :
                                    response.body()) {
                                if (!cliente.getIdEstatus().equals(Constants.ACCION_AUTORIZAR))
                                    continue;
                                clientesList.add(cliente.getNombre());
                                clientes.add(cliente);
                            }
                            break;
                    }

                    onCargarSpinnerGrupos();
                }
            }

            @Override
            public void onFailure(Call<List<Clientes>> call, Throwable t) {

            }
        });
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerGrupos() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, clientesList);

        int itemSelection = onPreRenderSelectGrupo();

        spinnerClientes.setAdapter(adapter);
        spinnerClientes.setSelection(itemSelection);
    }

    private int onPreRenderSelectGrupo() {
        int item = 0;

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
            case Constants.ACCION_VER:
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_ENTREGAR:
                PrestamosIndividuales prestamoIndividual = _prestamoIndividualActual;
                for (Clientes cliente : clientes) {
                    item++;
                    if (cliente.getId().equals(prestamoIndividual.getIdCliente())) {
                        break;
                    }
                }
                break;
        }

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_cliente_prestamo_individual:
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

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String motivo = tilMotivoPrstamo.getEditText().getText().toString();
        String cantidad = tilCantidadSolicitada.getEditText().getText().toString();
        String garantia = tilGarantia.getEditText().getText().toString();
        String observaciones = tilObservaciones.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilMotivoPrstamo, motivo);
        boolean b = ValidationUtils.esTextoValido(tilCantidadSolicitada, cantidad);
        boolean c = ValidationUtils.esTextoValido(tilGarantia, garantia);
        boolean d = ValidationUtils.esTextoValido(tilObservaciones, observaciones);
        boolean e = ValidationUtils.esSpinnerValido(spinnerClientes);

        if (a && b && c && d && e) {
            PrestamosIndividuales data = new PrestamosIndividuales();
            data.setMotivo(motivo);
            data.setCantidadSolicitada(Double.valueOf(cantidad));
            data.setGarantia(garantia);
            data.setObservaciones(observaciones);
            data.setIdCliente(_clienteSeleccionado.getId());

            data.setAnticipo(0.0);
            data.setCantidadOtorgada(0.0);
            data.setInteres(7.0);
            data.setFechaEntrega("1988-10-04");

            setPrestamoIndividual(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;


        return valido;
    }

    public static void setPrestamoIndividual(PrestamosIndividuales data) {
        _prestamoIndividualActual.setIdCliente(data.getIdCliente());
        _prestamoIndividualActual.setMotivo(data.getMotivo());
        _prestamoIndividualActual.setCantidadSolicitada(data.getCantidadSolicitada());
        _prestamoIndividualActual.setGarantia(data.getGarantia());
        _prestamoIndividualActual.setObservaciones(data.getObservaciones());
        _prestamoIndividualActual.setAnticipo(data.getAnticipo());
        _prestamoIndividualActual.setCantidadOtorgada(data.getCantidadOtorgada());
        _prestamoIndividualActual.setInteres(data.getInteres());
        _prestamoIndividualActual.setFechaEntrega(data.getFechaEntrega());

        _prestamoIndividualActual.setIdEstatus(data.getIdEstatus());
        _prestamoIndividualActual.setIdUsuario(_SESSION_USER.getId());
    }
}
