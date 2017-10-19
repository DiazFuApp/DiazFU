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
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
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

public class FormularioGruposFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilNombre;
    private static Spinner spinnerPromotor, spinnerClientes;

    private static List<String> promotoresList;
    private List<Promotores> promotores;
    private static List<String> clientesList;
    private List<Clientes> clientes;

    public static Grupos _grupoActual;
    public static Promotores _promotorSeleccionado;

    /**
     * Implementaciones REST
     */
    private GruposRest gruposRest;
    private PromotoresRest promotoresRest;
    private ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grupos_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_grupo);

        spinnerPromotor = (Spinner) view.findViewById(R.id.spinner_promotor_grupo);
        spinnerPromotor.setOnItemSelectedListener(this);
        spinnerClientes = (Spinner) view.findViewById(R.id.spinner_cliente_grupo);
        spinnerClientes.setOnItemSelectedListener(this);

        promotoresRest = ApiUtils.getPromotoresRest();
        clientesRest = ApiUtils.getClientesRest();
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
        this.listadoPromotores();
        this.listadoClientes();
    }

    private void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.obtenerGrupo();
                break;
            case Constants.ACCION_REGISTRAR:
                _grupoActual = new Grupos();
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
        Grupos grupo = ((Grupos) _MAIN_DECODE.getDecodeItem().getItemModel());

        gruposRest.getGrupo(Long.valueOf(grupo.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Grupos>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Grupos grupo) {

                        _grupoActual = grupo;

                        tilNombre.getEditText().setText(grupo.getNombre());
                    }
                });
    }

    private void listadoPromotores() {
        promotoresRest.getPromotores().enqueue(new Callback<List<Promotores>>() {
            @Override
            public void onResponse(Call<List<Promotores>> call, Response<List<Promotores>> response) {

                if (response.isSuccessful()) {
                    promotoresList = new ArrayList<>();
                    promotores = new ArrayList<>();

                    promotoresList.add("Seleccione ...");

                    for (Promotores promotor :
                            response.body()) {
                        promotoresList.add(promotor.getNombre());
                        promotores.add(promotor);
                    }

                    onCargarSpinnerPromotores();
                }
            }

            @Override
            public void onFailure(Call<List<Promotores>> call, Throwable t) {

            }
        });
    }

    private void listadoClientes() {
        clientesRest.getClientes().enqueue(new Callback<List<Clientes>>() {
            @Override
            public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {

                if (response.isSuccessful()) {
                    clientesList = new ArrayList<>();
                    clientes = new ArrayList<>();

                    clientesList.add("Seleccione ...");

                    for (Clientes cliente :
                            response.body()) {
                        clientesList.add(cliente.getNombre());
                        clientes.add(cliente);
                    }

                    onCargarSpinnerClientes();
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
    private void onCargarSpinnerPromotores() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, promotoresList);

        int itemSelection = onPreRenderSelectPromotor();

        spinnerPromotor.setAdapter(adapter);
        spinnerPromotor.setSelection(itemSelection);
    }

    private int onPreRenderSelectPromotor() {
        int item = 0;

        if (_MAIN_DECODE.getAccionFragmento() == Constants.ACCION_EDITAR) {
            Grupos grupo = _grupoActual;
            for (Promotores promotor : promotores) {
                item++;
                if (promotor.getId().equals(grupo.getId())) {
                    break;
                }
            }
        }

        return item;
    }

    /**
     * Asigna los valores al spinner
     **/
    private void onCargarSpinnerClientes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, clientesList);

        int itemSelection = onPreRenderSelectCliente();

        spinnerClientes.setAdapter(adapter);
        spinnerClientes.setSelection(itemSelection);
    }

    private int onPreRenderSelectCliente() {
        int item = 0;

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_promotor_grupo:
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

        String nombre = tilNombre.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b) {
            Grupos data = new Grupos();
            data.setNombre(nombre);
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setIdClienteResponsable(1);

            setGrupos(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b) {
            Grupos data = new Grupos();
            data.setNombre(nombre);
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setIdClienteResponsable(1);

            data.setIdEstatus(_grupoActual.getIdEstatus());
            data.setIdUsuario(_grupoActual.getIdUsuario());

            setGrupos(data);
            valido = true;
        }

        return valido;
    }


    public static void setGrupos(Grupos data) {
        _grupoActual.setNombre(data.getNombre());
        _grupoActual.setIdPromotor(data.getIdPromotor());
        _grupoActual.setIdClienteResponsable(data.getIdClienteResponsable());

        _grupoActual.setIdEstatus(data.getIdEstatus());
        _grupoActual.setIdUsuario(data.getIdUsuario());
    }
}
