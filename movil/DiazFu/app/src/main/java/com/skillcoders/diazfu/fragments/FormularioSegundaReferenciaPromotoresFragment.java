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
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioSegundaReferenciaPromotoresFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilCURP,
            tilClaveElector;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static Promotores _promotorActual;
    public static ReferenciasPromotores _referenciaDosActual;

    /**
     * Implementaciones REST
     */
    private ReferenciasPromotoresRest referenciasPromotoresRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores_segunda_referencia_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_segunda_referencia);
        tilRFC = (TextInputLayout) view.findViewById(R.id.rfc_segunda_referencia);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_segunda_referencia);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_segunda_referencia);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_segunda_referencia);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_segunda_referencia);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_segunda_referencia);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_segunda_promotor);
        //tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_refe);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_segunda_referencia);
        tilCURP = (TextInputLayout) view.findViewById(R.id.curp_segunda_referencia);
        tilClaveElector = (TextInputLayout) view.findViewById(R.id.clave_elector_segunda_referencia);

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
                this.obtenerReferenciaDos();
                break;
            case Constants.ACCION_REGISTRAR:
                _referenciaDosActual = new ReferenciasPromotores();
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

    private void obtenerReferenciaDos() {
        _promotorActual = ((Promotores) _MAIN_DECODE.getDecodeItem().getItemModel());
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();
        String rfc = tilRFC.getEditText().getText().toString();
        String direccion = tilDireccion.getEditText().getText().toString();
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String curp = tilCURP.getEditText().getText().toString();
        String claveElector = tilClaveElector.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilRFC, rfc);
        boolean c = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean d = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean e = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean f = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean g = ValidationUtils.esTextoValido(tilCURP, curp);
        boolean h = ValidationUtils.esTextoValido(tilClaveElector, claveElector);
        boolean i = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);

        if (a && b && c && d && e && f && g && h && i) {
            ReferenciasPromotores data = new ReferenciasPromotores();
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_CONOCIDO);
            data.setNombre(nombre);
            data.setDireccion(direccion);
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
        String telefonoCasa = tilTelefonoCasa.getEditText().getText().toString();
        String telefonoCelular = tilTelefonoCelular.getEditText().getText().toString();
        String correoElectronico = tilCorreoElectronico.getEditText().getText().toString();
        String curp = tilCURP.getEditText().getText().toString();
        String claveElector = tilClaveElector.getEditText().getText().toString();
        String fechaNacimiento = tilFechaNacimiento.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);
        boolean b = ValidationUtils.esTextoValido(tilRFC, rfc);
        boolean c = ValidationUtils.esTextoValido(tilDireccion, direccion);
        boolean d = ValidationUtils.esTextoValido(tilTelefonoCasa, telefonoCasa);
        boolean e = ValidationUtils.esTextoValido(tilTelefonoCelular, telefonoCelular);
        boolean f = ValidationUtils.esTextoValido(tilCorreoElectronico, correoElectronico);
        boolean g = ValidationUtils.esTextoValido(tilCURP, curp);
        boolean h = ValidationUtils.esTextoValido(tilClaveElector, claveElector);
        boolean i = ValidationUtils.esTextoValido(tilFechaNacimiento, fechaNacimiento);

        if (a && b && c && d && e && f && g && h && i) {
            ReferenciasPromotores data = new ReferenciasPromotores();
            data.setIdActor(_promotorActual.getId());
            data.setIdTipoReferencia(Constants.TIPO_REFERENCIA_CONOCIDO);
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setRFC(rfc);
            data.setCURP(curp);
            data.setCorreoElectronico(correoElectronico);
            data.setClaveElector(claveElector);

            data.setIdUsuario(_referenciaDosActual.getIdUsuario());
            data.setIdEstatus(_referenciaDosActual.getIdEstatus());

            setReferenciaPromotor(data);
            valido = true;
        }

        return valido;
    }

    public static void setReferenciaPromotor(ReferenciasPromotores data) {
        _referenciaDosActual.setIdTipoReferencia(data.getIdTipoReferencia());
        _referenciaDosActual.setIdActor(data.getIdActor());

        _referenciaDosActual.setNombre(data.getNombre());
        _referenciaDosActual.setDireccion(data.getDireccion());
        _referenciaDosActual.setTelefonoCasa(data.getTelefonoCasa());
        _referenciaDosActual.setTelefonoCelular(data.getTelefonoCelular());
        _referenciaDosActual.setCorreoElectronico(data.getCorreoElectronico());
        _referenciaDosActual.setFechaNacimiento(data.getFechaNacimiento());
        _referenciaDosActual.setRFC(data.getRFC());
        _referenciaDosActual.setCURP(data.getCURP());
        _referenciaDosActual.setClaveElector(data.getClaveElector());
        _referenciaDosActual.setURLFoto(data.getURLFoto());

        _referenciaDosActual.setIdEstatus(data.getIdEstatus());
        _referenciaDosActual.setIdUsuario(data.getIdUsuario());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_fecha_nacimiento_segunda_referencia:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }

    }
}
