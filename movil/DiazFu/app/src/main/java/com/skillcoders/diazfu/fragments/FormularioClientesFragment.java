package com.skillcoders.diazfu.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioClientesFragment extends Fragment implements View.OnClickListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilNombre, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilNombreEmpresa,
            tilPuestoEmpresa, tilDireccionEmpresa, tilHorarioEmpresa, tilAntiguedadEmpresa,
            tilTelefonoEmpresa, tilSueldoEmpresa, tilNombreJefe, tilTelefonoJefe;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static Clientes _clienteActual;

    /**
     * Implementaciones REST
     */
    private ClientesRest clientesRest;

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
                break;
            case Constants.ACCION_REGISTRAR:
                _clienteActual = new Clientes();
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

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && o && p) {
            Clientes data = new Clientes();
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

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && o && p) {
            Clientes data = new Clientes();
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
            valido = true;
        }

        return valido;
    }

    public static void setClientes(Clientes data) {
        _clienteActual.setNombre(data.getNombre());
        _clienteActual.setDireccion(data.getDireccion());
        _clienteActual.setTelefonoCasa(data.getTelefonoCasa());
        _clienteActual.setTelefonoCelular(data.getTelefonoCelular());
        _clienteActual.setCorreoElectronico(data.getCorreoElectronico());
        _clienteActual.setFechaNacimiento(data.getFechaNacimiento());
        _clienteActual.setURLFoto(data.getURLFoto());
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_fecha_nacimiento_cliente:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
