package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.UsuariosRest;
import com.skillcoders.diazfu.utils.Constants;

import java.net.SocketException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * Controles de android
     **/
    private Button btnLogin;
    private EditText txtUsername, txtPassword;
    private ProgressDialog pDialog;

    /**
     * Implementaciones REST
     **/
    private UsuariosRest usuariosRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuariosRest = ApiUtils.getUsuariosRest();

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                validarIniciarSession();
                break;
            default:
                break;
        }
    }

    private void validarIniciarSession() {
        boolean valido = true;

        if (valido) {
            loginService(txtUsername.getText().toString(), txtPassword.getText().toString());
        }
    }

    private void loginService(String username, String password) {

        Usuarios usuario = new Usuarios();
        usuario.setNombre(username);
        usuario.setContrasena(password);

        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        usuariosRest.usuariosLogin(usuario).enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                pDialog.dismiss();

                if (response.isSuccessful()) {

                    Usuarios usuario = response.body();

                    if (null != usuario.getId()) {
                        openNavigation(usuario);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginActivity.this, "No es posible comunicarse con el servidor ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Unable to submit post to API v1.");
            }
        });
    }

    private void openNavigation(Usuarios usuario) {

        this.saveSessionPreferences(usuario);

        Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
        intent.putExtra(Constants.KEY_SESSION_USER, usuario);
        startActivity(intent);
        finish();
    }

    private void saveSessionPreferences(Usuarios usuario) {
        SharedPreferences prefsCredencials = getSharedPreferences(Constants.KEY_PREF_CREDENCIALS, Context.MODE_PRIVATE);

        SharedPreferences.Editor credencials = prefsCredencials.edit();
        credencials.putString(Constants.KEY_PREF_CREDENCIALS_USERNAME, usuario.getNombre());
        credencials.putInt(Constants.KEY_PREF_CREDENCIALS_USER_ID, usuario.getId());
        credencials.putInt(Constants.KEY_PREF_CREDENCIALS_TIPO_USER, usuario.getIdTipoActor());
        credencials.putBoolean(Constants.KEY_PREF_CREDENCIALS_SESSION, true);
        credencials.commit();
    }

    private void test() {
        usuariosRest.getUsuario(Long.valueOf(txtUsername.getText().toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Usuarios>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Usuarios usuario) {

                        /*
                        Log.i(TAG, "Id: " + response.body().getId());
                    Log.i(TAG, "IdActor: " + response.body().getIdActor());
                    Log.i(TAG, "IdTipoActor: " + response.body().getIdTipoActor());
                    Log.i(TAG, "Nombre: " + response.body().getNombre());
                    Log.i(TAG, "Contraseña: " + response.body().getContrasena());
                    Log.i(TAG, "IdEstatus: " + response.body().getIdEstatus());
                    Log.i(TAG, "IdUsuario: " + response.body().getIdUsuario());
                         */

                        Log.i(TAG, "Id: " + usuario.getId());
                        Log.i(TAG, "IdActor: " + usuario.getIdActor());
                        Log.i(TAG, "IdTipoActor: " + usuario.getIdTipoActor());
                        Log.i(TAG, "Nombre: " + usuario.getNombre());
                        Log.i(TAG, "Contraseña: " + usuario.getContrasena());
                        Log.i(TAG, "IdEstatus: " + usuario.getIdEstatus());
                        Log.i(TAG, "IdUsuario: " + usuario.getIdUsuario());

                    }
                });
    }

    private void testAll() {
        usuariosRest.getUsuarios().enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {

                if (response.isSuccessful()) {

                    List<Usuarios> usuarios = response.body();

                    for (Usuarios usuario : usuarios) {
                        Log.i(TAG, "Id: " + usuario.getId());
                        Log.i(TAG, "IdActor: " + usuario.getIdActor());
                        Log.i(TAG, "IdTipoActor: " + usuario.getIdTipoActor());
                        Log.i(TAG, "Nombre: " + usuario.getNombre());
                        Log.i(TAG, "Contraseña: " + usuario.getContrasena());
                        Log.i(TAG, "IdEstatus: " + usuario.getIdEstatus());
                        Log.i(TAG, "IdUsuario: " + usuario.getIdUsuario());
                    }
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }

            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {

            }
        });
    }


}

