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

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.AsignacionesAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
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

public class FormularioGruposFragment extends Fragment implements Spinner.OnItemSelectedListener, View.OnClickListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilNombre;
    private static Spinner spinnerPromotor, spinnerClientes;
    private BootstrapButton btnAgregar;

    private static List<String> promotoresList;
    private List<Promotores> promotores;
    private static List<String> clientesList;
    private List<Clientes> clientes;

    public static Grupos _grupoActual;
    public static Promotores _promotorSeleccionado;
    public static Clientes _clienteSeleccionado;
    public static Integer _clienteResponsable;

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
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_grupo);

        spinnerPromotor = (Spinner) view.findViewById(R.id.spinner_promotor_grupo);
        spinnerPromotor.setOnItemSelectedListener(this);
        spinnerClientes = (Spinner) view.findViewById(R.id.spinner_cliente_grupo);
        spinnerClientes.setOnItemSelectedListener(this);

        btnAgregar = (BootstrapButton) view.findViewById(R.id.btn_agregar_cliente_grupo);
        btnAgregar.setOnClickListener(this);

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
        activityInterface.showProgressDialog();
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.obtenerGrupo();
                break;
            case Constants.ACCION_REGISTRAR:
                _grupoActual = new Grupos();
                activityInterface.stopProgressDialog();
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
        try {
            activityInterface = (MainRegisterInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
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
                        activityInterface.stopProgressDialog();
                    }

                    @Override
                    public void onNext(Grupos grupo) {

                        activityInterface.stopProgressDialog();
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
            case R.id.spinner_cliente_grupo:
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

        String nombre = tilNombre.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b) {

            if (null != _clienteResponsable) {
                Grupos data = new Grupos();
                data.setNombre(nombre);
                data.setIdPromotor(_promotorSeleccionado.getId());
                data.setIdClienteResponsable(_clienteResponsable);

                setGrupos(data);
                valido = true;
            } else {
                Toast.makeText(tilNombre.getContext(), "Es necesario tener un responsable del grupo."
                        , Toast.LENGTH_LONG).show();
            }

        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b) {

            if (null != _clienteResponsable) {
                Grupos data = new Grupos();
                data.setNombre(nombre);
                data.setIdPromotor(_promotorSeleccionado.getId());
                data.setIdClienteResponsable(_clienteResponsable);

                data.setIdEstatus(_grupoActual.getIdEstatus());

                setGrupos(data);
                valido = true;
            } else {
                Toast.makeText(tilNombre.getContext(), "Es necesario tener un responsable del grupo."
                        , Toast.LENGTH_LONG).show();
            }
        }

        return valido;
    }

    private boolean validarCliente() {
        boolean valido = false;

        boolean a = ValidationUtils.esSpinnerValido(spinnerClientes);

        if (a) {
            valido = true;
            List<Clientes> clientes = AsignacionGrupoFragment.clientesList;
            for (Clientes cliente : clientes) {
                if (cliente.getId().equals(_clienteSeleccionado.getId())) {
                    valido = false;
                    Toast.makeText(getActivity(), "El cliente ya existe en el grupo...", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }

        return valido;
    }

    public static void setGrupos(Grupos data) {
        _grupoActual.setNombre(data.getNombre());
        _grupoActual.setIdPromotor(data.getIdPromotor());
        _grupoActual.setIdClienteResponsable(data.getIdClienteResponsable());

        _grupoActual.setIdEstatus(data.getIdEstatus());
        _grupoActual.setIdUsuario(_SESSION_USER.getId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agregar_cliente_grupo:
                if (this.validarCliente()) this.integrarCliente();
                break;
        }
    }

    public void integrarCliente() {
        Clientes cliente = _clienteSeleccionado;
        cliente.setIdEstatus(Constants.ESTATUS_NO_RESPONSABLE);
        AsignacionGrupoFragment.adapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.add(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        IntegrantesGrupos integranteGrupo = new IntegrantesGrupos();
        integranteGrupo.setIdEstatus(Constants.ACCION_REGISTRAR);
        integranteGrupo.setIdCliente(cliente.getId());
        integranteGrupo.setCliente(cliente.getNombre());
        integranteGrupo.setIdUsuario(_SESSION_USER.getId());
        AsignacionGrupoFragment.integrantesGrupos.add(integranteGrupo);

        _clienteSeleccionado = new Clientes();
        spinnerClientes.setSelection(0);
    }
}
