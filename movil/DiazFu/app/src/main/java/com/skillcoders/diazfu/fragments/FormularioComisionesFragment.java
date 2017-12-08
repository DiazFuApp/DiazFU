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
import com.skillcoders.diazfu.data.model.Comisiones;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ComisionesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.services.SharedPreferencesService;
import com.skillcoders.diazfu.utils.CommonUtils;
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

public class FormularioComisionesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilDescripcion, tilComision;

    private static Spinner spinnerPromotor;

    private static List<String> promotoresList;
    private List<Promotores> promotores;

    public static Comisiones _comisionActual;
    public static Promotores _promotorSeleccionado;

    /**
     * Implementaciones REST
     */
    private ComisionesRest comisionesRest;
    private PromotoresRest promotoresRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comisiones_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());

        tilDescripcion = (TextInputLayout) view.findViewById(R.id.descripcion_comision);
        tilComision = (TextInputLayout) view.findViewById(R.id.comision_comision);

        spinnerPromotor = (Spinner) view.findViewById(R.id.spinner_promotor_comision);
        spinnerPromotor.setOnItemSelectedListener(this);

        comisionesRest = ApiUtils.getComisionesRest();
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
            case Constants.ACCION_PAGAR:
                this.obtenerComision();
                break;
            case Constants.ACCION_REGISTRAR:
                _comisionActual = new Comisiones();
                this.listadoPromotores();
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

    private void obtenerComision() {
        Comisiones comisiones = ((Comisiones) _MAIN_DECODE.getDecodeItem().getItemModel());

        comisionesRest.getComision(Long.valueOf(comisiones.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comisiones>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Comisiones data) {

                        _comisionActual = data;

                        tilDescripcion.getEditText().setText(data.getDescripcion());
                        tilComision.getEditText().setText(data.getComision().toString());

                        listadoPromotores();

                        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_PAGAR) {
                            onPreRenderUI();
                        }
                    }
                });
    }

    private void onPreRenderUI() {
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
                        case Constants.ACCION_PAGAR:

                            for (Promotores promotor :
                                    response.body()) {
                                if (promotor.getId().equals(_comisionActual.getIdPromotor())) {
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

        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_PAGAR) {
            Comisiones data = _comisionActual;
            for (Promotores promotor : promotores) {
                item++;
                if (promotor.getId().equals(data.getIdPromotor())) {
                    break;
                }
            }
        }

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_promotor_comision:
                if (position > 0) {
                    Promotores promotor = promotores.get(position - 1);
                    _promotorSeleccionado = promotor;
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String descripcion = tilDescripcion.getEditText().getText().toString();
        String comision = tilComision.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilDescripcion, descripcion);
        boolean b = ValidationUtils.esNumeroValido(tilComision, comision);
        boolean d = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b && d) {
            Comisiones data = new Comisiones();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setComision(Double.valueOf(comision));
            data.setDescripcion(descripcion);

            setComision(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String descripcion = tilDescripcion.getEditText().getText().toString();
        String comision = tilComision.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilDescripcion, descripcion);
        boolean b = ValidationUtils.esNumeroValido(tilComision, comision);
        boolean d = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b && d) {
            Comisiones data = new Comisiones();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setComision(Double.valueOf(comision));
            data.setDescripcion(descripcion);

            data.setIdEstatus(Constants.DIAZFU_WEB_FINALIZADO);

            setComision(data);
            valido = true;
        }

        return valido;
    }

    public static void setComision(Comisiones data) {
        _comisionActual.setIdPromotor(data.getIdPromotor());
        _comisionActual.setDescripcion(data.getDescripcion());
        _comisionActual.setComision(data.getComision());

        _comisionActual.setIdEstatus(data.getIdEstatus());
        _comisionActual.setIdUsuario(_SESSION_USER.getId());
    }
}
