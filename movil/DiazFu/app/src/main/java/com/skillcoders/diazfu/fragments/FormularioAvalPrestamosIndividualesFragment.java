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
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.RedesSocialesRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPrestamosRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioAvalPrestamosIndividualesFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilDireccionReferencia, tilTelefonoCasa,
            tilTelefonoCelular, tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento,
            tilCURP, tilClaveElector, tilParentesco, tilNombreEmpresa, tilPuestoEmpresa, tilDireccionEmpresa,
            tilAntiguedadEmpresa, tilTelefonoEmpresa, tilNombreJefe;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static PrestamosIndividuales _prestamosIndividualesActual;
    public static ReferenciasPrestamos _avalActual;
    public static List<RedesSociales> _redesSocialesActuales;

    /**
     * Implementaciones REST
     */
    private ReferenciasPrestamosRest referenciasPrestamosRest;
    private RedesSocialesRest redesSocialesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_individuales_aval_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_aval_prestamo_individual);
        tilRFC = (TextInputLayout) view.findViewById(R.id.rfc_aval_prestamo_individual);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_aval_prestamo_individual);
        tilDireccionReferencia = (TextInputLayout) view.findViewById(R.id.referencia_direccion_aval_prestamo_individual);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_aval_prestamo_individual);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_aval_prestamo_individual);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_aval_prestamo_individual);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_referencia);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_referencia);
        tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_referencia);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_aval_prestamo_individual);
        tilCURP = (TextInputLayout) view.findViewById(R.id.curp_aval_prestamo_individual);
        tilClaveElector = (TextInputLayout) view.findViewById(R.id.clave_elector_aval_prestamo_individual);
        tilParentesco = (TextInputLayout) view.findViewById(R.id.parentesco_aval_prestamo_individual);
        tilNombreEmpresa = (TextInputLayout) view.findViewById(R.id.nombre_empresa_aval_prestamo_individual);
        tilPuestoEmpresa = (TextInputLayout) view.findViewById(R.id.puesto_empresa_aval_prestamo_individual);
        tilDireccionEmpresa = (TextInputLayout) view.findViewById(R.id.direccion_empresa__aval_prestamo_individual);
        tilAntiguedadEmpresa = (TextInputLayout) view.findViewById(R.id.antiguedad_empresa_aval_prestamo_individual);
        tilTelefonoEmpresa = (TextInputLayout) view.findViewById(R.id.telefono_empresa_aval_prestamo_individual);
        tilNombreJefe = (TextInputLayout) view.findViewById(R.id.nombre_jefe_aval_prestamo_individual);

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

        referenciasPrestamosRest = ApiUtils.getReferenciasPrestamosRest();
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
            case Constants.ACCION_VER:
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_ENTREGAR:
                this.obtenerAval();
                break;
            case Constants.ACCION_REGISTRAR:
                _avalActual = new ReferenciasPrestamos();
                _redesSocialesActuales = new ArrayList<>();
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO, ""));
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

    private void obtenerAval() {
        _prestamosIndividualesActual = ((PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel());

        ReferenciasPrestamos referenciasPrestamos = new ReferenciasPrestamos();
        referenciasPrestamos.setIdPrestamo(_prestamosIndividualesActual.getId());
        referenciasPrestamos.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_INDIVIDUAL);

        referenciasPrestamosRest.getReferenciaPrestamos(referenciasPrestamos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ReferenciasPrestamos>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ReferenciasPrestamos> referenciasPrestamos) {
                        _avalActual = referenciasPrestamos.get(0);

                        //Asigna la fecha seleccionada a la instancia calendar.
                        Calendar calendar = DateTimeUtils.getParseTimeFromSQL(_avalActual.getFechaNacimiento());
                        myCalendar.setTime(calendar.getTime());
                        updateTxtDate();

                        tilNombre.getEditText().setText(_avalActual.getNombre());
                        tilRFC.getEditText().setText(_avalActual.getRFC());
                        tilDireccion.getEditText().setText(_avalActual.getDireccion());
                        tilDireccionReferencia.getEditText().setText(_avalActual.getReferenciaDireccion());
                        tilTelefonoCasa.getEditText().setText(_avalActual.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(_avalActual.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(_avalActual.getCorreoElectronico());
                        tilCURP.getEditText().setText(_avalActual.getCURP());
                        tilClaveElector.getEditText().setText(_avalActual.getClaveElector());
                        tilParentesco.getEditText().setText(_avalActual.getParentesco());
                        tilNombreEmpresa.getEditText().setText(_avalActual.getEmpresa());
                        tilPuestoEmpresa.getEditText().setText(_avalActual.getPuestoEmpresa());
                        tilDireccionEmpresa.getEditText().setText(_avalActual.getDireccionEmpresa());
                        tilAntiguedadEmpresa.getEditText().setText(_avalActual.getDireccionEmpresa());
                        tilTelefonoEmpresa.getEditText().setText(_avalActual.getTelefonoEmpresa());
                        tilNombreJefe.getEditText().setText(_avalActual.getNombreJefe());

                        obtenerRedesSociales();
                    }
                });
    }

    private void obtenerRedesSociales() {
        RedesSociales redSocial = new RedesSociales();
        redSocial.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO);
        redSocial.setIdActor(_avalActual.getId());

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
        String rfc = tilRFC.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();
        String referenciaDireccion = tilDireccionReferencia.getEditText().getText().toString();
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String curp = tilCURP.getEditText().getText().toString();
        String claveElector = tilClaveElector.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();
        String parentesco = tilParentesco.getEditText().getText().toString();
        String nombreEmpresa = tilNombreEmpresa.getEditText().getText().toString();
        String puestoEmpresa = tilPuestoEmpresa.getEditText().getText().toString();
        String direccionEmpresa = tilDireccionEmpresa.getEditText().getText().toString();
        String antiguedadEmpresa = tilAntiguedadEmpresa.getEditText().getText().toString();
        String telefonoEmpresa = tilTelefonoEmpresa.getEditText().getText().toString();
        String nombreJefe = tilNombreJefe.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilRFC, rfc);
        boolean c = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean d = ValidationUtils.esTextoValido(tilDireccionReferencia, referenciaDireccion);
        boolean e = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean f = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean g = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean h = ValidationUtils.esTextoValido(tilCURP, curp);
        boolean i = ValidationUtils.esTextoValido(tilClaveElector, claveElector);
        boolean j = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);
        boolean k = ValidationUtils.esTextoValido(tilParentesco, fechaNacimiento);
        boolean l = ValidationUtils.esTextoValido(tilNombreEmpresa, fechaNacimiento);
        boolean m = ValidationUtils.esTextoValido(tilPuestoEmpresa, fechaNacimiento);
        boolean n = ValidationUtils.esTextoValido(tilDireccionEmpresa, fechaNacimiento);
        boolean o = ValidationUtils.esTextoValido(tilAntiguedadEmpresa, fechaNacimiento);
        boolean p = ValidationUtils.esTextoValido(tilTelefonoEmpresa, fechaNacimiento);
        boolean q = ValidationUtils.esTextoValido(tilNombreJefe, fechaNacimiento);

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && n && o && p && q) {
            ReferenciasPrestamos data = new ReferenciasPrestamos();
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_AVAL);
            data.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_INDIVIDUAL);
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setReferenciaDireccion(referenciaDireccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setRFC(rfc);
            data.setCURP(curp);
            data.setCorreoElectronico(correoElectronico);
            data.setClaveElector(claveElector);
            data.setParentesco(parentesco);
            data.setEmpresa(nombreEmpresa);
            data.setPuestoEmpresa(puestoEmpresa);
            data.setDireccionEmpresa(direccionEmpresa);
            data.setAntiguedadEmpresa(antiguedadEmpresa);
            data.setTelefonoEmpresa(telefonoEmpresa);
            data.setNombreJefe(nombreJefe);

            setReferenciaPromotor(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();
        String rfc = tilRFC.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();
        String referenciaDireccion = tilDireccionReferencia.getEditText().getText().toString();
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String curp = tilCURP.getEditText().getText().toString();
        String claveElector = tilClaveElector.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();
        String parentesco = tilParentesco.getEditText().getText().toString();
        String nombreEmpresa = tilNombreEmpresa.getEditText().getText().toString();
        String puestoEmpresa = tilPuestoEmpresa.getEditText().getText().toString();
        String direccionEmpresa = tilDireccionEmpresa.getEditText().getText().toString();
        String antiguedadEmpresa = tilAntiguedadEmpresa.getEditText().getText().toString();
        String telefonoEmpresa = tilTelefonoEmpresa.getEditText().getText().toString();
        String nombreJefe = tilNombreJefe.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilRFC, rfc);
        boolean c = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean d = ValidationUtils.esTextoValido(tilDireccionReferencia, referenciaDireccion);
        boolean e = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean f = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean g = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean h = ValidationUtils.esTextoValido(tilCURP, curp);
        boolean i = ValidationUtils.esTextoValido(tilClaveElector, claveElector);
        boolean j = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);
        boolean k = ValidationUtils.esTextoValido(tilParentesco, fechaNacimiento);
        boolean l = ValidationUtils.esTextoValido(tilNombreEmpresa, fechaNacimiento);
        boolean m = ValidationUtils.esTextoValido(tilPuestoEmpresa, fechaNacimiento);
        boolean n = ValidationUtils.esTextoValido(tilDireccionEmpresa, fechaNacimiento);
        boolean o = ValidationUtils.esTextoValido(tilAntiguedadEmpresa, fechaNacimiento);
        boolean p = ValidationUtils.esTextoValido(tilTelefonoEmpresa, fechaNacimiento);
        boolean q = ValidationUtils.esTextoValido(tilNombreJefe, fechaNacimiento);

        if (a && b && c && d && e && f && g && h && i && j && k && l && m && n && o && p && q) {
            ReferenciasPrestamos data = new ReferenciasPrestamos();
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_AVAL);
            data.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_INDIVIDUAL);
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setReferenciaDireccion(referenciaDireccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setRFC(rfc);
            data.setCURP(curp);
            data.setCorreoElectronico(correoElectronico);
            data.setClaveElector(claveElector);
            data.setParentesco(parentesco);
            data.setEmpresa(nombreEmpresa);
            data.setPuestoEmpresa(puestoEmpresa);
            data.setDireccionEmpresa(direccionEmpresa);
            data.setAntiguedadEmpresa(antiguedadEmpresa);
            data.setTelefonoEmpresa(telefonoEmpresa);
            data.setNombreJefe(nombreJefe);

            data.setIdUsuario(_avalActual.getIdUsuario());
            data.setIdEstatus(_avalActual.getIdEstatus());

            setReferenciaPromotor(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static void setReferenciaPromotor(ReferenciasPrestamos data) {
        _avalActual.setIdTipoReferencia(data.getIdTipoReferencia());
        _avalActual.setIdTipoPrestamo(data.getIdTipoPrestamo());
        _avalActual.setNombre(data.getNombre());
        _avalActual.setDireccion(data.getDireccion());
        _avalActual.setReferenciaDireccion(data.getReferenciaDireccion());
        _avalActual.setTelefonoCasa(data.getTelefonoCasa());
        _avalActual.setTelefonoCelular(data.getTelefonoCelular());
        _avalActual.setCorreoElectronico(data.getCorreoElectronico());
        _avalActual.setFechaNacimiento(data.getFechaNacimiento());
        _avalActual.setRFC(data.getRFC());
        _avalActual.setCURP(data.getCURP());
        _avalActual.setClaveElector(data.getClaveElector());
        _avalActual.setURLFoto(data.getURLFoto());
        _avalActual.setParentesco(data.getParentesco());
        _avalActual.setEmpresa(data.getNombre());
        _avalActual.setPuestoEmpresa(data.getPuestoEmpresa());
        _avalActual.setDireccionEmpresa(data.getDireccionEmpresa());
        _avalActual.setAntiguedadEmpresa(data.getAntiguedadEmpresa());
        _avalActual.setTelefonoEmpresa(data.getTelefonoEmpresa());
        _avalActual.setNombreJefe(data.getNombreJefe());

        _avalActual.setIdEstatus(data.getIdEstatus());
        _avalActual.setIdUsuario(data.getIdUsuario());
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
            case R.id.txt_fecha_nacimiento_aval_prestamo_individual:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
