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
import android.widget.Toast;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.services.SharedPreferencesService;
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

public class FormularioAutorizacionPrestamosGrupalesFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilCantidadOtorgada;
    private static Spinner spinnerPlazos;

    private static List<String> plazosList;
    private List<Integer> plazos;

    public static PrestamosGrupales _prestamoGrupalActual;
    public static Pagos _pagosActual;
    public static List<Pagos> _pagosProgramadosActual;
    public static int _plazosSeleccionado;

    private View view;

    /**
     * Implementaciones REST
     */
    private PrestamosGrupalesRest prestamosGrupalesRest;
    private PagosRest pagosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_autorizaciones_prestamos_grupales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());

        tilCantidadOtorgada = (TextInputLayout) view.findViewById(R.id.cantidad_otorgar_autorizacion);

        spinnerPlazos = (Spinner) view.findViewById(R.id.spinner_plazos_autorizacion);
        spinnerPlazos.setOnItemSelectedListener(this);

        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();
        pagosRest = ApiUtils.getPagosRest();

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
            case Constants.ACCION_ENTREGAR:
                this.obtenerGrupo();
                //RegistroPrestamosGrupalesFragment.frameAutorizacion.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                break;
            case Constants.ACCION_AUTORIZAR:
                _pagosActual = new Pagos();
                _pagosProgramadosActual = new ArrayList<>();
                this.obtenerGrupo();
                //RegistroPrestamosGrupalesFragment.frameAutorizacion.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                break;
            case Constants.ACCION_REGISTRAR:
                _prestamoGrupalActual = new PrestamosGrupales();
                //RegistroPrestamosGrupalesFragment.frameAutorizacion.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
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

                        tilCantidadOtorgada.getEditText().setText(prestamoGrupal.getCantidadOtorgada().toString());

                        onPreRenderUI();
                        listadoPlazos();
                    }
                });
    }

    private void onPreRenderUI() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_AUTORIZAR:

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction mainFragment = fragmentManager.beginTransaction();
                mainFragment.replace(R.id.fragment_listado_integrantes_cantidades_prestamos_grupales_container, new IntegrantesCantidadesPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_INTEGRANTES_CANTIDADES);
                mainFragment.commit();

                break;
            default:
                tilCantidadOtorgada.getEditText().setKeyListener(null);
                break;
        }
    }

    private void listadoPlazos() {
        plazosList = new ArrayList<>();
        plazos = new ArrayList<>();

        plazosList.add("Seleccione ...");
        plazosList.add("8 Semanas");
        plazosList.add("12 Semanas");
        plazosList.add("16 Semanas");

        plazos.add(8);
        plazos.add(12);
        plazos.add(16);

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_VER:
            case Constants.ACCION_ENTREGAR:
                subListadoPlazos();
            case Constants.ACCION_AUTORIZAR:
                onCargarSpinnerPlazos();
                break;
        }
    }

    private void subListadoPlazos() {
        Pagos pago = new Pagos();
        pago.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_GRUPAL);
        pago.setIdPrestamo(_prestamoGrupalActual.getId());

        pagosRest.getPago(pago).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Pagos>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Pagos> pagos) {
                        _plazosSeleccionado = pagos.size();

                        plazosList = new ArrayList<>();
                        plazos = new ArrayList<>();

                        plazosList.add("Seleccione ...");
                        plazosList.add(_plazosSeleccionado + " Semanas");
                        plazos.add(_plazosSeleccionado);

                        onCargarSpinnerPlazos();
                    }
                });
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerPlazos() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, plazosList);

        int itemSelection = onPreRenderSelectPlazos();

        spinnerPlazos.setAdapter(adapter);
        spinnerPlazos.setSelection(itemSelection);
    }

    private int onPreRenderSelectPlazos() {
        int item = 0;

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
            case Constants.ACCION_VER:
            case Constants.ACCION_ENTREGAR:
                for (int plazo :
                        plazos) {
                    item++;
                    if (plazo == _plazosSeleccionado) {
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
            case R.id.spinner_plazos_autorizacion:
                if (position > 0) {
                    int plazo = plazos.get(position - 1);
                    _plazosSeleccionado = plazo;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean validarDatosAutorizacion() {
        boolean valido = false;

        String cantidadOtorgada = tilCantidadOtorgada.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilCantidadOtorgada, cantidadOtorgada);
        boolean b = ValidationUtils.esSpinnerValido(spinnerPlazos);

        if (a && b) {
            Pagos data = new Pagos();
            data.setIdPrestamo(_prestamoGrupalActual.getId());
            data.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_GRUPAL);
            data.setMontoAPagar(Double.valueOf(cantidadOtorgada) / _plazosSeleccionado);
            data.setPlazo(String.valueOf(_plazosSeleccionado));
            data.setFechaPago(null);
            data.setMorosidad(null);
            data.setDescripcion(null);

            FormularioPrestamosGrupalesFragment._prestamoGrupalActual.setCantidadOtorgada(Double.valueOf(cantidadOtorgada));

            //setPagos(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosAutorizacionIntegrantes() {
        boolean valido = false;

        List<IntegrantesGrupos> integrantes = IntegrantesCantidadesPrestamosGrupalesFragment.integrantesGruposList;
        _pagosProgramadosActual = new ArrayList<>();
        Double cantidadOtorgadaGrupal = new Double(0.0);

        for (IntegrantesGrupos integrante :
                integrantes) {
            try {
                String cantidadOtorgada = integrante.getCantidadOtorgada().toString();

                if (cantidadOtorgada.isEmpty()) {
                    Toast.makeText(spinnerPlazos.getContext(), "La cantidad otorgada para el integrante " + integrante.getCliente() + " debe ser mayor a $ 0.0", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (Double.valueOf(cantidadOtorgada) <= 0) {
                    Toast.makeText(spinnerPlazos.getContext(), "La cantidad otorgada para el integrante " + integrante.getCliente() + " debe ser mayor a $ 0.0", Toast.LENGTH_SHORT).show();
                    return false;
                }

                boolean a = ValidationUtils.esSpinnerValido(spinnerPlazos);

                if (a) {
                    Pagos data = new Pagos();
                    data.setIdPrestamo(_prestamoGrupalActual.getId());
                    data.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_GRUPAL);
                    data.setMontoAPagar(Double.valueOf(cantidadOtorgada) / _plazosSeleccionado);
                    data.setPlazo(String.valueOf(_plazosSeleccionado));
                    data.setFechaPago(null);
                    data.setMorosidad(null);
                    data.setDescripcion(null);
                    data.setIdCliente(integrante.getIdCliente());
                    data.setIdUsuario(_SESSION_USER.getId());

                    cantidadOtorgadaGrupal = cantidadOtorgadaGrupal + Double.valueOf(cantidadOtorgada);
                    FormularioPrestamosGrupalesFragment._prestamoGrupalActual.setCantidadOtorgada(cantidadOtorgadaGrupal);

                    _pagosProgramadosActual.add(data);
                    valido = true;
                }
            } catch (NullPointerException e) {
                Toast.makeText(spinnerPlazos.getContext(), "La cantidad otorgada para el integrante " + integrante.getCliente() + " debe ser mayor a $ 0.0", Toast.LENGTH_SHORT).show();
                return false;
            }

        }

        return valido;
    }
}
