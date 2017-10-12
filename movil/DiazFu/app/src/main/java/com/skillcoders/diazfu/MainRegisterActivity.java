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
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;

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
    public void registrarPromotor(Promotores promotor) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistroPromotores(promotor);
    }

    private void webServiceRegistroPromotores(Promotores promotor) {

        promotoresRest.agregarPromotor(promotor).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {

                    Promotores promotor = response.body();

                    if (null != promotor.getId()) {
                        //TODO call
                    } else {
                        Toast.makeText(MainRegisterActivity.this, "Notifique a fred", Toast.LENGTH_SHORT).show();
                    }

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

    @Override
    public void editarPromotor(Promotores promotor) {

    }
}
