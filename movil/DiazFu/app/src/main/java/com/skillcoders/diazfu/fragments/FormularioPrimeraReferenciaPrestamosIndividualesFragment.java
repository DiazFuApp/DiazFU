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
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPrestamosRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPrimeraReferenciaPrestamosIndividualesFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilDireccionReferencia, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilCURP,
            tilClaveElector;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static PrestamosIndividuales _prestamosIndividualesActual;
    public static ReferenciasPrestamos _referenciaActual;

    /**
     * Implementaciones REST
     */
    private ReferenciasPrestamosRest referenciasPrestamosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_individuales_primera_referencia_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_primera_referencia_prestamo_individual);
        tilRFC = (TextInputLayout) view.findViewById(R.id.rfc_primera_referencia_prestamo_individual);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_primera_referencia_prestamo_individual);
        tilDireccionReferencia = (TextInputLayout) view.findViewById(R.id.referencia_direccion_primera_referencia_prestamo_individual);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_primera_referencia_prestamo_individual);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_primera_referencia_prestamo_individual);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_primera_referencia_prestamo_individual);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_referencia);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_referencia);
        //tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_refe);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_primera_referencia_prestamo_individual);
        tilCURP = (TextInputLayout) view.findViewById(R.id.curp_primera_referencia_prestamo_individual);
        tilClaveElector = (TextInputLayout) view.findViewById(R.id.clave_elector_primera_referencia_prestamo_individual);

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
                this.obtenerprimera_referencia();
                break;
            case Constants.ACCION_REGISTRAR:
                _referenciaActual = new ReferenciasPrestamos();
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

    private void obtenerprimera_referencia() {
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
                        _referenciaActual = referenciasPrestamos.get(1);

                        //Asigna la fecha seleccionada a la instancia calendar.
                        Calendar calendar = DateTimeUtils.getParseTimeFromSQL(_referenciaActual.getFechaNacimiento());
                        myCalendar.setTime(calendar.getTime());
                        updateTxtDate();

                        tilNombre.getEditText().setText(_referenciaActual.getNombre());
                        tilRFC.getEditText().setText(_referenciaActual.getRFC());
                        tilDireccion.getEditText().setText(_referenciaActual.getDireccion());
                        tilDireccionReferencia.getEditText().setText(_referenciaActual.getReferenciaDireccion());
                        tilTelefonoCasa.getEditText().setText(_referenciaActual.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(_referenciaActual.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(_referenciaActual.getCorreoElectronico());
                        tilCURP.getEditText().setText(_referenciaActual.getCURP());
                        tilClaveElector.getEditText().setText(_referenciaActual.getClaveElector());
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

        if (a && b && c && d && e && f && g && h && i && j) {
            ReferenciasPrestamos data = new ReferenciasPrestamos();
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_CONOCIDO);
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

            setReferenciaPromotor(data);
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

        if (a && b && c && d && e && f && g && h && i && j) {
            ReferenciasPrestamos data = new ReferenciasPrestamos();
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_CONOCIDO);
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

            data.setIdUsuario(_referenciaActual.getIdUsuario());
            data.setIdEstatus(_referenciaActual.getIdEstatus());

            setReferenciaPromotor(data);
            valido = true;
        }

        return valido;
    }

    public static void setReferenciaPromotor(ReferenciasPrestamos data) {
        _referenciaActual.setIdTipoReferencia(data.getIdTipoReferencia());
        _referenciaActual.setIdTipoPrestamo(data.getIdTipoPrestamo());
        _referenciaActual.setNombre(data.getNombre());
        _referenciaActual.setDireccion(data.getDireccion());
        _referenciaActual.setReferenciaDireccion(data.getReferenciaDireccion());
        _referenciaActual.setTelefonoCasa(data.getTelefonoCasa());
        _referenciaActual.setTelefonoCelular(data.getTelefonoCelular());
        _referenciaActual.setCorreoElectronico(data.getCorreoElectronico());
        _referenciaActual.setFechaNacimiento(data.getFechaNacimiento());
        _referenciaActual.setRFC(data.getRFC());
        _referenciaActual.setCURP(data.getCURP());
        _referenciaActual.setClaveElector(data.getClaveElector());
        _referenciaActual.setURLFoto(data.getURLFoto());

        _referenciaActual.setIdEstatus(data.getIdEstatus());
        _referenciaActual.setIdUsuario(data.getIdUsuario());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_fecha_nacimiento_primera_referencia_prestamo_individual:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
