package com.skillcoders.diazfu.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.skillcoders.diazfu.DocumentosActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.RedesSocialesRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioClientesFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private Button btnDocumentos;

    private static TextInputLayout tilNombre, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilNombreEmpresa,
            tilPuestoEmpresa, tilDireccionEmpresa, tilHorarioEmpresa, tilAntiguedadEmpresa,
            tilTelefonoEmpresa, tilSueldoEmpresa, tilNombreJefe, tilTelefonoJefe;

    private static Spinner spinnerPromotor;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    private static List<String> promotoresList;
    private List<Promotores> promotores;

    public static Clientes _clienteActual;
    public static Promotores _promotorSeleccionado;
    public static List<RedesSociales> _redesSocialesActuales;

    /**
     * Implementaciones REST
     */
    private ClientesRest clientesRest;
    private PromotoresRest promotoresRest;
    private RedesSocialesRest redesSocialesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_cliente);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_cliente);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_cliente);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_cliente);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_cliente);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_cliente);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_cliente);
        tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_cliente);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_cliente);
        tilNombreEmpresa = (TextInputLayout) view.findViewById(R.id.nombre_empresa_cliente);
        tilPuestoEmpresa = (TextInputLayout) view.findViewById(R.id.puesto_empresa_cliente);
        tilDireccionEmpresa = (TextInputLayout) view.findViewById(R.id.direccion_empresa__cliente);
        tilHorarioEmpresa = (TextInputLayout) view.findViewById(R.id.horario_laboral_cliente);
        tilAntiguedadEmpresa = (TextInputLayout) view.findViewById(R.id.antiguedad_empresa_cliente);
        tilTelefonoEmpresa = (TextInputLayout) view.findViewById(R.id.telefono_empresa_cliente);
        tilSueldoEmpresa = (TextInputLayout) view.findViewById(R.id.salario_laboral_cliente);
        tilNombreJefe = (TextInputLayout) view.findViewById(R.id.nombre_jefe_cliente);
        tilTelefonoJefe = (TextInputLayout) view.findViewById(R.id.telefono_jefe_cliente);

        btnDocumentos = (Button) view.findViewById(R.id.btn_documentos_cliente);
        btnDocumentos.setOnClickListener(this);

        spinnerPromotor = (Spinner) view.findViewById(R.id.spinner_promotor_cliente);
        spinnerPromotor.setOnItemSelectedListener(this);

        tilFechaNacimiento.getEditText().setOnClickListener(this);

        /**Crea el picker calendar**/
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTxtDate();
            }
        };

        clientesRest = ApiUtils.getClientesRest();
        promotoresRest = ApiUtils.getPromotoresRest();
        redesSocialesRest = ApiUtils.getRedesSocialesRest();

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
                this.obtenerCliente();
                btnDocumentos.setVisibility(View.VISIBLE);
                break;
            case Constants.ACCION_REGISTRAR:
                this.listadoPromotores();
                _clienteActual = new Clientes();
                _redesSocialesActuales = new ArrayList<>();
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK, Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER, Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM, Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE, ""));
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
            Clientes cliente = _clienteActual;
            for (Promotores promotor : promotores) {
                item++;
                if (promotor.getId().equals(cliente.getIdPromotor())) {
                    break;
                }
            }
        }

        return item;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_promotor_cliente:
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

    private void updateTxtDate() {
        String myFormat = Constants.MASK_DATE_ANDROID_DMY;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ROOT);
        tilFechaNacimiento.getEditText().setText(sdf.format(myCalendar.getTime()));
    }

    private void obtenerCliente() {
        Clientes cliente = ((Clientes) _MAIN_DECODE.getDecodeItem().getItemModel());

        clientesRest.getCliente(Long.valueOf(cliente.getId()))
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

                        _clienteActual = cliente;

                        //Asigna la fecha seleccionada a la instancia calendar.
                        Calendar calendar = DateTimeUtils.getParseTimeFromSQL(cliente.getFechaNacimiento());
                        myCalendar.setTime(calendar.getTime());
                        updateTxtDate();

                        tilNombre.getEditText().setText(cliente.getNombre());
                        tilDireccion.getEditText().setText(cliente.getDireccion());
                        tilTelefonoCasa.getEditText().setText(cliente.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(cliente.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(cliente.getCorreoElectronico());
                        tilNombreEmpresa.getEditText().setText(cliente.getNombreEmpresa());
                        tilPuestoEmpresa.getEditText().setText(cliente.getPuestoEmpresa());
                        tilDireccionEmpresa.getEditText().setText(cliente.getDireccionEmpresa());
                        tilHorarioEmpresa.getEditText().setText(cliente.getHorarioEmpresa());
                        tilAntiguedadEmpresa.getEditText().setText(cliente.getAntiguedad());
                        tilTelefonoEmpresa.getEditText().setText(cliente.getTelefonoEmpresa());
                        tilSueldoEmpresa.getEditText().setText(cliente.getSueldoMensual());
                        tilNombreJefe.getEditText().setText(cliente.getNombreJefe());
                        tilTelefonoJefe.getEditText().setText(cliente.getTelefonoJefe());

                        listadoPromotores();
                        obtenerRedesSociales();
                    }
                });
    }

    private void obtenerRedesSociales() {
        RedesSociales redSocial = new RedesSociales();
        redSocial.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE);
        redSocial.setIdActor(_clienteActual.getId());

        redesSocialesRest.getRedesSociales(redSocial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RedesSociales>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<RedesSociales> data) {

                        _redesSocialesActuales = new ArrayList<>();

                        for (RedesSociales redSocial :
                                data) {

                            switch (redSocial.getIdTipoRedSocial()) {
                                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK:
                                    tilFacebook.getEditText().setText(redSocial.getURL());
                                    break;
                                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER:
                                    tilTwitter.getEditText().setText(redSocial.getURL());
                                    break;
                                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM:
                                    tilInstagram.getEditText().setText(redSocial.getURL());
                                    break;
                            }

                            _redesSocialesActuales.add(redSocial);
                        }
                    }
                });
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();
        String nombreEmpresa = tilNombreEmpresa.getEditText().getText().toString();
        String puestoEmpresa = tilPuestoEmpresa.getEditText().getText().toString();
        String direccionEmpresa = tilDireccionEmpresa.getEditText().getText().toString();
        String horarioEmpresa = tilHorarioEmpresa.getEditText().getText().toString();
        String antiguedadEmpresa = tilAntiguedadEmpresa.getEditText().getText().toString();
        String telefonoEmpresa = tilTelefonoEmpresa.getEditText().getText().toString();
        String sueldoEmpresa = tilSueldoEmpresa.getEditText().getText().toString();
        String nombreJefe = tilNombreJefe.getEditText().getText().toString();
        String telefonoJefe = tilTelefonoJefe.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean c = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean d = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean e = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean f = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);
        boolean g = ValidationUtils.esTextoValido(tilNombreEmpresa, fechaNacimiento);
        boolean h = ValidationUtils.esTextoValido(tilPuestoEmpresa, fechaNacimiento);
        boolean i = ValidationUtils.esTextoValido(tilDireccionEmpresa, fechaNacimiento);
        boolean j = ValidationUtils.esTextoValido(tilHorarioEmpresa, fechaNacimiento);
        boolean k = ValidationUtils.esTextoValido(tilAntiguedadEmpresa, fechaNacimiento);
        boolean l = ValidationUtils.esTextoValido(tilTelefonoEmpresa, fechaNacimiento);
        boolean m = ValidationUtils.esTextoValido(tilSueldoEmpresa, fechaNacimiento);
        boolean o = ValidationUtils.esTextoValido(tilNombreJefe, fechaNacimiento);
        boolean p = ValidationUtils.esTextoValido(tilTelefonoJefe, fechaNacimiento);
        boolean q = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && o && p && q) {
            Clientes data = new Clientes();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setCorreoElectronico(correoElectronico);
            data.setNombreEmpresa(nombreEmpresa);
            data.setPuestoEmpresa(puestoEmpresa);
            data.setDireccionEmpresa(direccionEmpresa);
            data.setHorarioEmpresa(horarioEmpresa);
            data.setAntiguedad(antiguedadEmpresa);
            data.setTelefonoEmpresa(telefonoEmpresa);
            data.setSueldoMensual(sueldoEmpresa);
            data.setNombreJefe(nombreJefe);
            data.setTelefonoJefe(telefonoJefe);

            setClientes(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();
        String nombreEmpresa = tilNombreEmpresa.getEditText().getText().toString();
        String puestoEmpresa = tilPuestoEmpresa.getEditText().getText().toString();
        String direccionEmpresa = tilDireccionEmpresa.getEditText().getText().toString();
        String horarioEmpresa = tilHorarioEmpresa.getEditText().getText().toString();
        String antiguedadEmpresa = tilAntiguedadEmpresa.getEditText().getText().toString();
        String telefonoEmpresa = tilTelefonoEmpresa.getEditText().getText().toString();
        String sueldoEmpresa = tilSueldoEmpresa.getEditText().getText().toString();
        String nombreJefe = tilNombreJefe.getEditText().getText().toString();
        String telefonoJefe = tilTelefonoJefe.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean c = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean d = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean e = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean f = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);
        boolean g = ValidationUtils.esTextoValido(tilNombreEmpresa, fechaNacimiento);
        boolean h = ValidationUtils.esTextoValido(tilPuestoEmpresa, fechaNacimiento);
        boolean i = ValidationUtils.esTextoValido(tilDireccionEmpresa, fechaNacimiento);
        boolean j = ValidationUtils.esTextoValido(tilHorarioEmpresa, fechaNacimiento);
        boolean k = ValidationUtils.esTextoValido(tilAntiguedadEmpresa, fechaNacimiento);
        boolean l = ValidationUtils.esTextoValido(tilTelefonoEmpresa, fechaNacimiento);
        boolean m = ValidationUtils.esTextoValido(tilSueldoEmpresa, fechaNacimiento);
        boolean o = ValidationUtils.esTextoValido(tilNombreJefe, fechaNacimiento);
        boolean p = ValidationUtils.esTextoValido(tilTelefonoJefe, fechaNacimiento);
        boolean q = ValidationUtils.esSpinnerValido(spinnerPromotor);

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && o && p && q) {
            Clientes data = new Clientes();
            data.setIdPromotor(_promotorSeleccionado.getId());
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setCorreoElectronico(correoElectronico);
            data.setNombreEmpresa(nombreEmpresa);
            data.setPuestoEmpresa(puestoEmpresa);
            data.setDireccionEmpresa(direccionEmpresa);
            data.setHorarioEmpresa(horarioEmpresa);
            data.setAntiguedad(antiguedadEmpresa);
            data.setTelefonoEmpresa(telefonoEmpresa);
            data.setSueldoMensual(sueldoEmpresa);
            data.setNombreJefe(nombreJefe);
            data.setTelefonoJefe(telefonoJefe);

            data.setIdUsuario(_clienteActual.getIdUsuario());
            data.setIdEstatus(_clienteActual.getIdEstatus());

            setClientes(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static void setClientes(Clientes data) {
        _clienteActual.setIdPromotor(data.getIdPromotor());
        _clienteActual.setNombre(data.getNombre());
        _clienteActual.setDireccion(data.getDireccion());
        _clienteActual.setTelefonoCasa(data.getTelefonoCasa());
        _clienteActual.setTelefonoCelular(data.getTelefonoCelular());
        _clienteActual.setCorreoElectronico(data.getCorreoElectronico());
        _clienteActual.setFechaNacimiento(data.getFechaNacimiento());
        _clienteActual.setNombreEmpresa(data.getNombreEmpresa());
        _clienteActual.setPuestoEmpresa(data.getPuestoEmpresa());
        _clienteActual.setDireccionEmpresa(data.getDireccionEmpresa());
        _clienteActual.setHorarioEmpresa(data.getHorarioEmpresa());
        _clienteActual.setAntiguedad(data.getAntiguedad());
        _clienteActual.setTelefonoEmpresa(data.getTelefonoEmpresa());
        _clienteActual.setSueldoMensual(data.getSueldoMensual());
        _clienteActual.setNombreJefe(data.getNombreJefe());
        _clienteActual.setTelefonoJefe(data.getTelefonoJefe());

        _clienteActual.setIdEstatus(data.getIdEstatus());
        _clienteActual.setIdUsuario(data.getIdUsuario());
    }

    public static void setRedesSociales() {
        for (RedesSociales data :
                _redesSocialesActuales) {
            switch (data.getIdTipoRedSocial()) {
                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK:
                    data.setURL(tilFacebook.getEditText().getText().toString());
                    break;
                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER:
                    data.setURL(tilTwitter.getEditText().getText().toString());
                    break;
                case Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM:
                    data.setURL(tilInstagram.getEditText().getText().toString());
                    break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_fecha_nacimiento_cliente:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_documentos_cliente:
                DecodeExtraHelper extra = new DecodeExtraHelper();

                extra.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(v.getId())));
                extra.setTituloFormulario(_clienteActual.getNombre());
                extra.setAccionFragmento(Constants.ACCION_VER);
                extra.setFragmentTag(Constants.ITEM_FRAGMENT.get(v.getId()));

                Documentos documento = new Documentos();
                documento.setIdActor(_clienteActual.getId());
                documento.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE);

                Intent intent = new Intent(getActivity(), DocumentosActivity.class);
                intent.putExtra(Constants.KEY_MAIN_DECODE, extra);
                intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
                intent.putExtra(Constants.KEY_MAIN_DOCUMENTOS, documento);
                startActivity(intent);
                break;
        }
    }
}
