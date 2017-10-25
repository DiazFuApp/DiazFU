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
import android.widget.Toast;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.AsignacionesAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
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

public class FormularioPrestamosGrupalesFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilMotivoPrstamo, tilCantidadSolicitada, tilGarantia, tilObservaciones;
    private static Spinner spinnerGrupos;

    private static List<String> gruposList;
    private List<Grupos> grupos;

    public static PrestamosGrupales _prestamoGrupalActual;
    public static Grupos _grupoSeleccionado;

    /**
     * Implementaciones REST
     */
    private PrestamosGrupalesRest prestamosGrupalesRest;
    private GruposRest gruposRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_grupales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilMotivoPrstamo = (TextInputLayout) view.findViewById(R.id.motivo_prestamos_prestamo_grupal);
        tilCantidadSolicitada = (TextInputLayout) view.findViewById(R.id.cantidad_solicitada_prestamo_grupal);
        tilGarantia = (TextInputLayout) view.findViewById(R.id.garantia_prestamo_grupal);
        tilObservaciones = (TextInputLayout) view.findViewById(R.id.observaciones_prestamo_grupal);

        spinnerGrupos = (Spinner) view.findViewById(R.id.spinner_grupo_prestamo_grupal);
        spinnerGrupos.setOnItemSelectedListener(this);

        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();
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
        this.listadoGrupos();
    }

    private void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.obtenerGrupo();
                break;
            case Constants.ACCION_REGISTRAR:
                _prestamoGrupalActual = new PrestamosGrupales();
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
        PrestamosGrupales prestamoGrupal = ((PrestamosGrupales) _MAIN_DECODE.getDecodeItem().getItemModel());

        prestamosGrupalesRest.getPrestamoGrupal(Long.valueOf(prestamoGrupal.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrestamosGrupales>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PrestamosGrupales prestamoGrupal) {

                        _prestamoGrupalActual = prestamoGrupal;

                        tilMotivoPrstamo.getEditText().setText(prestamoGrupal.getMotivo());
                        tilCantidadSolicitada.getEditText().setText(prestamoGrupal.getCantidadOtorgada().toString());
                        tilGarantia.getEditText().setText(prestamoGrupal.getGarantia());
                        tilObservaciones.getEditText().setText(prestamoGrupal.getObservaciones());
                    }
                });
    }

    private void listadoGrupos() {
        gruposRest.getGrupos().enqueue(new Callback<List<Grupos>>() {
            @Override
            public void onResponse(Call<List<Grupos>> call, Response<List<Grupos>> response) {

                if (response.isSuccessful()) {
                    gruposList = new ArrayList<>();
                    grupos = new ArrayList<>();

                    gruposList.add("Seleccione ...");

                    for (Grupos grupo :
                            response.body()) {
                        gruposList.add(grupo.getNombre());
                        grupos.add(grupo);
                    }

                    onCargarSpinnerPromotores();
                }
            }

            @Override
            public void onFailure(Call<List<Grupos>> call, Throwable t) {

            }
        });
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerPromotores() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, gruposList);

        int itemSelection = onPreRenderSelectPromotor();

        spinnerGrupos.setAdapter(adapter);
        spinnerGrupos.setSelection(itemSelection);
    }

    private int onPreRenderSelectPromotor() {
        int item = 0;

        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_EDITAR) {
            PrestamosGrupales prestamoGrupal = _prestamoGrupalActual;
            for (Grupos grupo : grupos) {
                item++;
                if (grupo.getId().equals(prestamoGrupal.getIdGrupo())) {
                    break;
                }
            }
        }

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_grupo_prestamo_grupal:
                if (position > 0) {
                    Grupos grupo = grupos.get(position - 1);
                    _grupoSeleccionado = grupo;
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
        boolean e = ValidationUtils.esSpinnerValido(spinnerGrupos);

        if (a && b && c && d && e) {
            PrestamosGrupales data = new PrestamosGrupales();
            data.setMotivo(motivo);
            data.setCantidadSolicitada(Double.valueOf(cantidad));
            data.setGarantia(garantia);
            data.setObservaciones(observaciones);
            data.setIdGrupo(_grupoSeleccionado.getId());

            setPrestamosGrupales(data);
            valido = true;

        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;


        return valido;
    }

    public static void setPrestamosGrupales(PrestamosGrupales data) {
        _prestamoGrupalActual.setMotivo(data.getMotivo());
        _prestamoGrupalActual.setCantidadSolicitada(data.getCantidadSolicitada());
        _prestamoGrupalActual.setGarantia(data.getGarantia());
        _prestamoGrupalActual.setObservaciones(data.getObservaciones());

        _prestamoGrupalActual.setIdEstatus(data.getIdEstatus());
        _prestamoGrupalActual.setIdUsuario(data.getIdUsuario());
    }
}
