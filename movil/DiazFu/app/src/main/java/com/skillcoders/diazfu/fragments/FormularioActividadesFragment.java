package com.skillcoders.diazfu.fragments;


import android.app.DatePickerDialog;
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

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ActividadesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Calendar;
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

public class FormularioActividadesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilTitulo, tilDescripcion;

    private static Spinner spinnerPromotor, spinnerPrioridad;

    private static List<String> promotoresList;
    private static List<String> prioridadesList;
    private List<Promotores> promotores;

    public static Actividades _actividadActual;
    public static Promotores _promotorSeleccionado;
    public static String _prioridadSeleccionada;

    /**
     * Implementaciones REST
     */
    private ActividadesRest actividadesRest;
    private PromotoresRest promotoresRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actividades_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        tilTitulo = (TextInputLayout) view.findViewById(R.id.titulo_actividad);
        tilDescripcion = (TextInputLayout) view.findViewById(R.id.descripcion_actividad);

        spinnerPromotor = (Spinner) view.findViewById(R.id.spinner_promotor_actividad);
        spinnerPromotor.setOnItemSelectedListener(this);

        spinnerPrioridad = (Spinner) view.findViewById(R.id.spinner_prioridad_actividad);
        spinnerPrioridad.setOnItemSelectedListener(this);

        actividadesRest = ApiUtils.getActividadesRest();
        promotoresRest = ApiUtils.getPromotoresRest();

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
                this.obtenerActividad();
                break;
            case Constants.ACCION_REGISTRAR:
                _actividadActual = new Actividades();
                this.listadoPromotores();
                this.listadoPrioridades();
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

    private void obtenerActividad() {
        Actividades actividad = ((Actividades) _MAIN_DECODE.getDecodeItem().getItemModel());

        actividadesRest.getCliente(Long.valueOf(actividad.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Actividades>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Actividades data) {

                        _actividadActual = data;

                        tilTitulo.getEditText().setText(data.getTitulo());
                        tilDescripcion.getEditText().setText(data.getDescripcion());

                        prioridadesList = new ArrayList<>();
                        prioridadesList.add("Seleccione ...");
                        prioridadesList.add(data.getPrioridad());

                        listadoPromotores();

                        onCargarSpinnerPrioridades();

                        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_VER) {
                            onPreRenderUI();
                        }
                    }
                });
    }

    private void onPreRenderUI() {
        tilTitulo.getEditText().setKeyListener(null);
        tilDescripcion.getEditText().setKeyListener(null);
    }

    private void listadoPromotores() {
        promotoresRest.getPromotores().enqueue(new Callback<List<Promotores>>() {
            @Override
            public void onResponse(Call<List<Promotores>> call, Response<List<Promotores>> response) {

                if (response.isSuccessful()) {
                    promotoresList = new ArrayList<>();
                    promotores = new ArrayList<>();

                    promotoresList.add("Seleccione ...");

                    switch (_MAIN_DECODE.getAccionFragmento()) {
                        case Constants.ACCION_REGISTRAR:
                            for (Promotores promotor :
                                    response.body()) {
                                promotoresList.add(promotor.getNombre());
                                promotores.add(promotor);
                            }
                            break;
                        case Constants.ACCION_EDITAR:
                        case Constants.ACCION_VER:

                            for (Promotores promotor :
                                    response.body()) {
                                if (promotor.getId().equals(_actividadActual.getIdPromotor())) {
                                    promotoresList.add(promotor.getNombre());
                                    promotores.add(promotor);
                                }
                            }
                            break;
                    }

                    onCargarSpinnerPromotores();
                }
            }

            @Override
            public void onFailure(Call<List<Promotores>> call, Throwable t) {

            }
        });
    }

    private void listadoPrioridades() {
        prioridadesList = new ArrayList<>();
        prioridadesList.add("Seleccione ...");
        prioridadesList.add("Baja");
        prioridadesList.add("Media");
        prioridadesList.add("Alta");
        prioridadesList.add("Urgente");

        onCargarSpinnerPrioridades();
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerPromotores() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, promotoresList);

        int itemSelection = onPreRenderSelectPromotor();

        spinnerPromotor.setAdapter(adapter);
        spinnerPromotor.setSelection(itemSelection);
    }

    private int onPreRenderSelectPromotor() {
        int item = 0;

        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_EDITAR
                || _MAIN_DECODE.getAccionFragmento() == Constants.ACCION_VER) {
            Actividades actividad = _actividadActual;
            for (Promotores promotor : promotores) {
                item++;
                if (promotor.getId().equals(actividad.getIdPromotor())) {
                    break;
                }
            }
        }

        return item;
    }

    private void onCargarSpinnerPrioridades() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, prioridadesList);

        int itemSelection = onPreRenderSelectPrioridades();

        spinnerPrioridad.setAdapter(adapter);
        spinnerPrioridad.setSelection(itemSelection);
    }

    private int onPreRenderSelectPrioridades() {
        int item = 0;

        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_EDITAR
                || _MAIN_DECODE.getAccionFragmento() == Constants.ACCION_VER) {
            Actividades actividad = _actividadActual;
            for (String prioridad : prioridadesList) {
                if (prioridad.equals(actividad.getPrioridad())) {
                    break;
                }
                item++;
            }
        }

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_promotor_actividad:
                if (position > 0) {
                    Promotores promotor = promotores.get(position - 1);
                    _promotorSeleccionado = promotor;
                }
                break;
            case R.id.spinner_prioridad_actividad:
                if (position > 0) {
                    _prioridadSeleccionada = spinnerPrioridad.getSelectedItem().toString();
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String titulo = tilTitulo.getEditText().getText().toString();
        String descripcion = tilDescripcion.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilTitulo, titulo);
        boolean b = ValidationUtils.esTextoValido(tilDescripcion, descripcion);
        boolean d = ValidationUtils.esSpinnerValido(spinnerPromotor);
        boolean e = ValidationUtils.esSpinnerValido(spinnerPrioridad);

        if (a && b && d && e) {
            Actividades data = new Actividades();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setIdPrioridad(Constants.TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.get(_prioridadSeleccionada));
            data.setTitulo(titulo);
            data.setDescripcion(descripcion);

            setActividad(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {

        boolean valido = false;

        String titulo = tilTitulo.getEditText().getText().toString();
        String descripcion = tilDescripcion.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilTitulo, titulo);
        boolean b = ValidationUtils.esTextoValido(tilDescripcion, descripcion);

        if (a && b) {
            Actividades data = new Actividades();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setIdPrioridad(Constants.TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.get(_prioridadSeleccionada));
            data.setTitulo(titulo);
            data.setDescripcion(descripcion);

            data.setIdUsuario(_actividadActual.getIdUsuario());
            data.setIdEstatus(_actividadActual.getIdEstatus());

            setActividad(data);
            valido = true;
        }

        return valido;
    }

    public static void setActividad(Actividades data) {
        _actividadActual.setIdPromotor(data.getIdPromotor());
        _actividadActual.setIdPrioridad(data.getIdPrioridad());
        _actividadActual.setTitulo(data.getTitulo());
        _actividadActual.setDescripcion(data.getDescripcion());

        _actividadActual.setIdEstatus(data.getIdEstatus());
        _actividadActual.setIdUsuario(data.getIdUsuario());
    }
}
