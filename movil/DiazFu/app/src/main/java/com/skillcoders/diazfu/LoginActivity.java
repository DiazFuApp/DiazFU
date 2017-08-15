package com.skillcoders.diazfu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.SOService;

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

    /**
     * Clases android
     **/
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mService = ApiUtils.getSOService();

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
            loginService(txtUsername.getText().toString(),txtPassword.getText().toString());
        }
    }

    private void loginService(String username, String password) {

        Usuarios usuario = new Usuarios();
        usuario.setNombre(username);
        usuario.setContrasena(password);

      mService.usuariosLogin(usuario).enqueue(new Callback<Usuarios>() {
          @Override
          public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
              Log.i(TAG, "post submitted to API." + response.body().toString());
          }

          @Override
          public void onFailure(Call<Usuarios> call, Throwable t) {
              Log.e(TAG, "Unable to submit post to API.");
          }
      });
    }

    private void test() {
        mService.getUsuario(Long.valueOf(txtUsername.getText().toString())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
        mService.getUsuarios().enqueue(new Callback<List<Usuarios>>() {
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

