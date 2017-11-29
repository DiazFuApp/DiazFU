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
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.RedesSocialesRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
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

public class FormularioReferenciaPromotoresFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilCURP,
            tilClaveElector;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static Promotores _promotorActual;
    public static ReferenciasPromotores _referenciaUnoActual;
    public static List<RedesSociales> _redesSocialesActuales;

    /**
     * Implementaciones REST
     */
    private ReferenciasPromotoresRest referenciasPromotoresRest;
    private RedesSocialesRest redesSocialesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores_referencia_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_referencia);
        tilRFC = (TextInputLayout) view.findViewById(R.id.rfc_referencia);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_referencia);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_referencia);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_referencia);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_referencia);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_referencia);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_referencia);
        tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_referencia);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_referencia);
        tilCURP = (TextInputLayout) view.findViewById(R.id.curp_referencia);
        tilClaveElector = (TextInputLayout) view.findViewById(R.id.clave_elector_referencia);

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

        referenciasPromotoresRest = ApiUtils.getReferenciasPromotoresRest();
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
                this.obtenerReferenciaUno();
                break;
            case Constants.ACCION_REGISTRAR:
                _referenciaUnoActual = new ReferenciasPromotores();
                _redesSocialesActuales = new ArrayList<>();
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM, Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR, ""));
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

    private void obtenerReferenciaUno() {
        _promotorActual = ((Promotores) _MAIN_DECODE.getDecodeItem().getItemModel());

        ReferenciasPromotores referenciasPromotores = new ReferenciasPromotores();
        referenciasPromotores.setIdActor(_promotorActual.getId());

        referenciasPromotoresRest.getReferenciaPromotor(referenciasPromotores)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ReferenciasPromotores>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ReferenciasPromotores> referenciasPromotores) {
                        _referenciaUnoActual = referenciasPromotores.get(0);

                        //Asigna la fecha seleccionada a la instancia calendar.
                        Calendar calendar = DateTimeUtils.getParseTimeFromSQL(_referenciaUnoActual.getFechaNacimiento());
                        myCalendar.setTime(calendar.getTime());
                        updateTxtDate();

                        tilNombre.getEditText().setText(_referenciaUnoActual.getNombre());
                        tilRFC.getEditText().setText(_referenciaUnoActual.getRFC());
                        tilDireccion.getEditText().setText(_referenciaUnoActual.getDireccion());
                        tilTelefonoCasa.getEditText().setText(_referenciaUnoActual.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(_referenciaUnoActual.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(_referenciaUnoActual.getCorreoElectronico());
                        tilCURP.getEditText().setText(_referenciaUnoActual.getCURP());
                        tilClaveElector.getEditText().setText(_referenciaUnoActual.getClaveElector());

                        obtenerRedesSociales();
                    }
                });
    }

    private void obtenerRedesSociales() {
        RedesSociales redSocial = new RedesSociales();
        redSocial.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR);
        redSocial.setIdActor(_referenciaUnoActual.getId());

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

            data.setIdUsuario(_referenciaUnoActual.getIdUsuario());
            data.setIdEstatus(_referenciaUnoActual.getIdEstatus());

            setReferenciaPromotor(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static void setReferenciaPromotor(ReferenciasPromotores data) {
        _referenciaUnoActual.setIdTipoReferencia(data.getIdTipoReferencia());
        _referenciaUnoActual.setIdActor(data.getIdActor());

        _referenciaUnoActual.setNombre(data.getNombre());
        _referenciaUnoActual.setDireccion(data.getDireccion());
        _referenciaUnoActual.setTelefonoCasa(data.getTelefonoCasa());
        _referenciaUnoActual.setTelefonoCelular(data.getTelefonoCelular());
        _referenciaUnoActual.setCorreoElectronico(data.getCorreoElectronico());
        _referenciaUnoActual.setFechaNacimiento(data.getFechaNacimiento());
        _referenciaUnoActual.setRFC(data.getRFC());
        _referenciaUnoActual.setCURP(data.getCURP());
        _referenciaUnoActual.setClaveElector(data.getClaveElector());

        _referenciaUnoActual.setIdEstatus(data.getIdEstatus());
        _referenciaUnoActual.setIdUsuario(data.getIdUsuario());
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
            case R.id.txt_fecha_nacimiento_referencia:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
