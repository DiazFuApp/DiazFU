package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PromotoresAdapter;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
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

public class FormularioPromotoresFragment extends Fragment {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilNombre, tilRFC, tilDireccion, tilTelefonoCasa, tilTelefonoCelular,
            tilCorreoElectronico, tilFacebook, tilTwitter, tilInstagram, tilFechaNacimiento, tilCURP,
            tilClaveElector;
    public static Promotores _promotorActual;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;

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
            case Constants.ACCION_EDITAR:
                this.obtenerPromotor();
                break;
            case Constants.ACCION_REGISTRAR:
                _promotorActual = new Promotores();
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
                        tilNombre.getEditText().setText(promotor.getNombre());
                        tilRFC.getEditText().setText(promotor.getRFC());
                        tilDireccion.getEditText().setText(promotor.getDireccion());
                        tilTelefonoCasa.getEditText().setText(promotor.getTelefonoCasa());
                        tilTelefonoCelular.getEditText().setText(promotor.getTelefonoCelular());
                        tilCorreoElectronico.getEditText().setText(promotor.getCorreoElectronico());
                        tilFechaNacimiento.getEditText().setText(promotor.getFechaNacimiento());
                        tilCURP.getEditText().setText(promotor.getCURP());
                        tilClaveElector.getEditText().setText(promotor.getClaveElector());
                    }
                });
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String nombre = tilNombre.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilNombre, nombre);

        if (a) {
            Promotores data = new Promotores();
            data.setNombre(nombre);
            setPromotores(data);
            valido = true;
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        return false;
    }

    public static void setPromotores(Promotores data) {
        _promotorActual.setNombre(data.getNombre());
        _promotorActual.setDireccion(data.getDireccion());
        _promotorActual.setTelefonoCasa(data.getTelefonoCasa());
        _promotorActual.setTelefonoCelular(data.getTelefonoCelular());
        _promotorActual.setFechaNacimiento(data.getFechaNacimiento());
        _promotorActual.setRFC(data.getRFC());
        _promotorActual.setCURP(data.getCURP());
        _promotorActual.setClaveElector(data.getClaveElector());
        _promotorActual.setURLFoto(data.getURLFoto());

        _promotorActual.setIdEstatus(data.getIdEstatus());
        _promotorActual.setIdUsuario(data.getIdUsuario());
    }
}
