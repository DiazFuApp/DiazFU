package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skillcoders.diazfu.DocumentosActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.UsuariosRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.services.SharedPreferencesService;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.ValidationUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioPromotoresCredencialesFragment extends Fragment {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    private static TextInputLayout tilUsuario, tilPassword, tilConfirmarPassword;

    public static Usuarios _usuariosActual;

    /**
     * Implementaciones REST
     */
    private UsuariosRest usuariosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores_credenciales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());

        tilUsuario = (TextInputLayout) view.findViewById(R.id.usuario_promotor);
        tilPassword = (TextInputLayout) view.findViewById(R.id.password_promotor);
        tilConfirmarPassword = (TextInputLayout) view.findViewById(R.id.confirmar_password_promotor);

        usuariosRest = ApiUtils.getUsuariosRest();

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
        activityInterface.showProgressDialog();
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                this.obtenerUsuario();
                break;
            case Constants.ACCION_REGISTRAR:
                _usuariosActual = new Usuarios();
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

    private void obtenerUsuario() {
        Promotores promotor = ((Promotores) _MAIN_DECODE.getDecodeItem().getItemModel());

        Usuarios usuario = new Usuarios();
        usuario.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR);
        usuario.setIdActor(promotor.getId());

        usuariosRest.getUsuario(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Usuarios>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        activityInterface.stopProgressDialog();
                    }

                    @Override
                    public void onNext(Usuarios data) {

                        _usuariosActual = data;

                        tilUsuario.getEditText().setText(_usuariosActual.getNombre());
                        tilPassword.getEditText().setText(_usuariosActual.getContrasena());
                        tilConfirmarPassword.getEditText().setText(_usuariosActual.getContrasena());

                        activityInterface.stopProgressDialog();
                    }
                });
    }

    public static boolean validarDatosRegistro() {
        boolean valido = false;

        String usuario = tilUsuario.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        String confirmarPassword = tilConfirmarPassword.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilUsuario, usuario);
        boolean b = ValidationUtils.esTextoValido(tilPassword, password);
        boolean c = ValidationUtils.esTextoValido(tilConfirmarPassword, confirmarPassword);

        if (a && b && c) {
            valido = true;

            Usuarios data = new Usuarios();
            data.setNombre(usuario);
            data.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR);
            data.setContrasena(password);

            if (!data.getContrasena().equals(confirmarPassword)) {
                valido = false;
                Toast.makeText(tilConfirmarPassword.getContext(), "Las contraseñas deben de ser iguales", Toast.LENGTH_SHORT).show();
                ValidationUtils.esTextoValido(tilPassword, "");
                ValidationUtils.esTextoValido(tilConfirmarPassword, "");
            } else {
                setUsuario(data);
            }
        }

        return valido;
    }

    public static boolean validarDatosEdicion() {
        boolean valido = false;

        String usuario = tilUsuario.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        String confirmarPassword = tilConfirmarPassword.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilUsuario, usuario);
        boolean b = ValidationUtils.esTextoValido(tilPassword, password);
        boolean c = ValidationUtils.esTextoValido(tilConfirmarPassword, confirmarPassword);

        if (a && b && c) {
            valido = true;

            Usuarios data = new Usuarios();
            data.setNombre(usuario);
            data.setIdTipoActor(Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR);
            data.setContrasena(password);
            data.setIdEstatus(_usuariosActual.getIdEstatus());

            if (!data.getContrasena().equals(confirmarPassword)) {
                valido = false;
                Toast.makeText(tilConfirmarPassword.getContext(), "Las contraseñas deben de ser iguales", Toast.LENGTH_SHORT).show();
                ValidationUtils.esTextoValido(tilPassword, "");
                ValidationUtils.esTextoValido(tilConfirmarPassword, "");
            } else {
                setUsuario(data);
            }
        }

        return valido;
    }

    public static void setUsuario(Usuarios data) {
        _usuariosActual.setNombre(data.getNombre());
        _usuariosActual.setIdTipoActor(data.getIdTipoActor());
        _usuariosActual.setContrasena(data.getContrasena());

        _usuariosActual.setIdEstatus(data.getIdEstatus());
        _usuariosActual.setIdUsuario(_SESSION_USER.getId());
    }
}
