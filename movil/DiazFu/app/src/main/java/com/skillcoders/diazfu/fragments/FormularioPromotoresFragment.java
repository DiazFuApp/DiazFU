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
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
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

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPromotoresFragment extends Fragment implements View.OnClickListener {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilCURP,
            tilClaveElector;

    private static Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;

    public static Promotores _promotorActual;
    public static List<RedesSociales> _redesSocialesActuales;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private RedesSocialesRest redesSocialesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        tilNombre = (TextInputLayout) view.findViewById(R.id.nombre_promotor);
        tilRFC = (TextInputLayout) view.findViewById(R.id.rfc_promotor);
        tilDireccion = (TextInputLayout) view.findViewById(R.id.direccion_promotor);
        tilTelefonoCasa = (TextInputLayout) view.findViewById(R.id.telefono_casa_promotor);
        tilTelefonoCelular = (TextInputLayout) view.findViewById(R.id.telefono_celular_promotor);
        tilCorreoElectronico = (TextInputLayout) view.findViewById(R.id.correo_electronico_promotor);
        tilFacebook = (TextInputLayout) view.findViewById(R.id.facebook_promotor);
        tilTwitter = (TextInputLayout) view.findViewById(R.id.twitter_promotor);
        tilInstagram = (TextInputLayout) view.findViewById(R.id.instagram_promotor);
        tilFechaNacimiento = (TextInputLayout) view.findViewById(R.id.fecha_nacimiento_promotor);
        tilCURP = (TextInputLayout) view.findViewById(R.id.curp_promotor);
        tilClaveElector = (TextInputLayout) view.findViewById(R.id.clave_elector_promotor);

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
                this.obtenerPromotor();
                break;
            case Constants.ACCION_REGISTRAR:
                _promotorActual = new Promotores();
                _redesSocialesActuales = new ArrayList<>();
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK, Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER, Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR, ""));
                _redesSocialesActuales.add(new RedesSociales(Constants.DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM, Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR, ""));
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

    private void obtenerPromotor() {
        Promotores promotor = ((Promotores) _MAIN_DECODE.getDecodeItem().getItemModel());

        promotoresRest.getPromotor(Long.valueOf(promotor.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Promotores>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Promotores promotor) {

                        _promotorActual = promotor;

                        //Asigna la fecha seleccionada a la instancia calendar.
                        Calendar calendar = DateTimeUtils.getParseTimeFromSQL(promotor.getFechaNacimiento());
                        myCalendar.setTime(calendar.getTime());
                        updateTxtDate();

                        tilNombre.getEditText().setText(promotor.getNombre());
                        tilRFC.getEditText().setText(promotor.getRFC());
                        tilDireccion.getEditText().setText(promotor.getDireccion());
                        tilTelefonoCasa.getEditText().setText(promotor.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(promotor.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(promotor.getCorreoElectronico());
                        tilCURP.getEditText().setText(promotor.getCURP());
                        tilClaveElector.getEditText().setText(promotor.getClaveElector());

                        obtenerRedesSociales();
                    }
                });
    }

    private void obtenerRedesSociales() {
        RedesSociales redSocial = new RedesSociales();
        redSocial.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR);
        redSocial.setIdActor(_promotorActual.getId());

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
            Promotores data = new Promotores();
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setRFC(rfc);
            data.setCURP(curp);
            data.setCorreoElectronico(correoElectronico);
            data.setClaveElector(claveElector);

            setPromotores(data);
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
            Promotores data = new Promotores();
            data.setNombre(nombre);
            data.setDireccion(direccion);
            data.setTelefonoCasa(telefonoCasa);
            data.setTelefonoCelular(telefonoCelular);
            data.setFechaNacimiento(DateTimeUtils.getParseTimeToSQL(myCalendar));
            data.setRFC(rfc);
            data.setCURP(curp);
            data.setCorreoElectronico(correoElectronico);
            data.setClaveElector(claveElector);

            data.setIdUsuario(_promotorActual.getIdUsuario());
            data.setIdEstatus(_promotorActual.getIdEstatus());

            setPromotores(data);
            setRedesSociales();
            valido = true;
        }

        return valido;
    }

    public static void setPromotores(Promotores data) {
        _promotorActual.setNombre(data.getNombre());
        _promotorActual.setDireccion(data.getDireccion());
        _promotorActual.setTelefonoCasa(data.getTelefonoCasa());
        _promotorActual.setTelefonoCelular(data.getTelefonoCelular());
        _promotorActual.setCorreoElectronico(data.getCorreoElectronico());
        _promotorActual.setFechaNacimiento(data.getFechaNacimiento());
        _promotorActual.setRFC(data.getRFC());
        _promotorActual.setCURP(data.getCURP());
        _promotorActual.setClaveElector(data.getClaveElector());
        _promotorActual.setURLFoto(data.getURLFoto());

        _promotorActual.setIdEstatus(data.getIdEstatus());
        _promotorActual.setIdUsuario(data.getIdUsuario());
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
            case R.id.txt_fecha_nacimiento_promotor:
                new DatePickerDialog(getContext(), R.style.MyCalendarTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
