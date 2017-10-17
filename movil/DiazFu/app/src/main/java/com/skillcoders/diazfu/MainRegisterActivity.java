package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jvier on 04/09/2017.
 */

public class MainRegisterActivity extends AppCompatActivity implements MainRegisterInterface {

    private static final String TAG = MainRegisterActivity.class.getSimpleName();

    private DecodeExtraHelper _MAIN_DECODE;
    private ProgressDialog pDialog;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private ReferenciasPromotoresRest referenciasPromotores;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_register);
        setSupportActionBar(toolbar);

        _MAIN_DECODE = (DecodeExtraHelper) getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        promotoresRest = ApiUtils.getPromotoresRest();
        referenciasPromotores = ApiUtils.getReferenciasPromotores();

        this.onPreRender();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(_MAIN_DECODE.getTituloActividad());
        getSupportActionBar().setSubtitle(_MAIN_DECODE.getTituloFormulario());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void onPreRender() {
        openFragment(_MAIN_DECODE.getFragmentTag());
    }

    /**
     * Abre el fragmento mediante el tag seleccionado
     **/
    private void openFragment(String tag) {
        FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
        mainFragment.replace(R.id.fragment_main_register_container, Constants.TAG_FRAGMENT.get(tag), tag);
        mainFragment.addToBackStack(tag);
        mainFragment.commit();
    }

    @Override
    public void registrarPromotor(PromotoresHelper promotoresHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistroPromotores(promotoresHelper);
    }

    private void webServiceRegistroPromotores(final PromotoresHelper promotoresHelper) {

        promotoresRest.agregarPromotor(promotoresHelper.getPromotor()).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {

                    Promotores promotor = response.body();

                    if (null != promotor.getId()) {
                        promotoresHelper.getPrimeraReferencia().setIdActor(promotor.getId());
                        promotoresHelper.getSegundaReferencia().setIdActor(promotor.getId());

                        List<ReferenciasPromotores> referencias = new ArrayList<>();

                        referencias.add(promotoresHelper.getPrimeraReferencia());
                        referencias.add(promotoresHelper.getSegundaReferencia());

                        for (ReferenciasPromotores referenciasPromotor : referencias) {
                            webServiceRegistrarRefererenciaPromotor(referenciasPromotor);
                        }

                        finish();
                        pDialog.dismiss();

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    pDialog.dismiss();
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Promotores> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void webServiceRegistrarRefererenciaPromotor(ReferenciasPromotores referenciaPromotor) {

        referenciasPromotores.agregarReferenciaPromotor(referenciaPromotor).enqueue(new Callback<ReferenciasPromotores>() {
            @Override
            public void onResponse(Call<ReferenciasPromotores> call, Response<ReferenciasPromotores> response) {

                if (response.isSuccessful()) {

                    ReferenciasPromotores referenciaPromotor = response.body();

                    if (null != referenciaPromotor.getId()) {
                        //TODO GUARDAR REDES SOCIALES
                        //TODO DOCUMENTOS REFERENCIA
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReferenciasPromotores> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void editarPromotor(PromotoresHelper promotoresHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEditarPromotores(promotoresHelper);
    }

    private void webServiceEditarPromotores(PromotoresHelper promotoresHelper) {
        promotoresRest.editarPromotor(promotoresHelper.getPromotor()).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Promotores promotor = response.body();

                    if (null != promotor.getId()) {
                        //TODO call
                    } else {
                        Toast.makeText(MainRegisterActivity.this, "Notifique a fred", Toast.LENGTH_SHORT).show();
                    }

                    finish();

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Promotores> call, Throwable t) {

            }
        });
    }
}
