package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.skillcoders.diazfu.adapters.AsignacionesAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.fragments.AsignacionGrupoFragment;
import com.skillcoders.diazfu.fragments.FormularioGruposFragment;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
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

public class MainRegisterActivity extends AppCompatActivity implements MainRegisterInterface, DialogInterface.OnClickListener {

    private static final String TAG = MainRegisterActivity.class.getSimpleName();

    private DecodeExtraHelper _MAIN_DECODE;
    private static DecodeItemHelper _decodeItem;
    private ProgressDialog pDialog;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private ReferenciasPromotoresRest referenciasPromotores;
    private ClientesRest clientesRest;
    private GruposRest gruposRest;
    private IntegrantesGruposRest integrantesGruposRest;

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
        clientesRest = ApiUtils.getClientesRest();
        gruposRest = ApiUtils.getGruposRest();
        integrantesGruposRest = ApiUtils.getIntegrantesGrupos();

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

    private void webServiceEditarPromotores(final PromotoresHelper promotoresHelper) {
        promotoresRest.editarPromotor(promotoresHelper.getPromotor()).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {

                    Promotores promotor = response.body();

                    if (null != promotor.getId()) {
                        List<ReferenciasPromotores> referencias = new ArrayList<>();

                        referencias.add(promotoresHelper.getPrimeraReferencia());
                        referencias.add(promotoresHelper.getSegundaReferencia());

                        for (ReferenciasPromotores referenciasPromotor : referencias) {
                            webServiceEditarRefererenciaPromotor(referenciasPromotor);
                        }

                        finish();
                        pDialog.dismiss();
                    }

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

    private void webServiceEditarRefererenciaPromotor(ReferenciasPromotores referenciaPromotor) {

        referenciasPromotores.editarReferenciaPromotor(referenciaPromotor).enqueue(new Callback<ReferenciasPromotores>() {
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
    public void registrarCliente(ClientesHelper clientesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarClientes(clientesHelper);
    }

    private void webServiceRegistrarClientes(ClientesHelper clientesHelper) {
        clientesRest.agregarCliente(clientesHelper.getCliente()).enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {

                if (response.isSuccessful()) {

                    Clientes cliente = response.body();

                    if (null != cliente.getId()) {

                        finish();
                        pDialog.dismiss();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {

            }
        });
    }

    @Override
    public void editarCliente(ClientesHelper clientesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEditarClientes(clientesHelper);
    }

    private void webServiceEditarClientes(ClientesHelper clientesHelper) {
        clientesRest.editarCliente(clientesHelper.getCliente()).enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {

                if (response.isSuccessful()) {

                    Clientes cliente = response.body();

                    if (null != cliente.getId()) {

                        finish();
                        pDialog.dismiss();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {

            }
        });
    }

    @Override
    public void registrarGrupo(GruposHelper gruposHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarGrupo(gruposHelper);
    }

    private void webServiceRegistrarGrupo(GruposHelper gruposHelper) {
        gruposRest.agregarGrupo(gruposHelper.getGrupo()).enqueue(new Callback<Grupos>() {
            @Override
            public void onResponse(Call<Grupos> call, Response<Grupos> response) {

                if (response.isSuccessful()) {

                    Grupos grupo = response.body();

                    if (null != grupo.getId()) {

                        finish();
                        pDialog.dismiss();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Grupos> call, Throwable t) {

            }
        });
    }

    @Override
    public void editarGrupo(GruposHelper gruposHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEditarGrupo(gruposHelper);
    }

    private void webServiceEditarGrupo(final GruposHelper gruposHelper) {
        gruposRest.editarGrupo(gruposHelper.getGrupo()).enqueue(new Callback<Grupos>() {
            @Override
            public void onResponse(Call<Grupos> call, Response<Grupos> response) {

                if (response.isSuccessful()) {

                    Grupos grupo = response.body();

                    if (null != grupo.getId()) {

                        List<IntegrantesGrupos> integrantesGrupos = gruposHelper.getIntegrantesGrupos();

                        for (IntegrantesGrupos integranteGrupo :
                                integrantesGrupos) {
                            integranteGrupo.setIdGrupo(grupo.getId());

                            switch (integranteGrupo.getIdEstatus()) {
                                case Constants.ACCION_REGISTRAR:
                                    webServiceAgregarIntegranteGrupo(integranteGrupo);
                                    break;
                                case Constants.ACCION_ELIMINAR:
                                    webServiceEliminarIntegranteGrupo(integranteGrupo);
                                    break;
                            }
                        }

                        finish();
                        pDialog.dismiss();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Grupos> call, Throwable t) {

            }
        });
    }

    private void webServiceAgregarIntegranteGrupo(IntegrantesGrupos integranteGrupo) {
        integrantesGruposRest.agregarIntegrante(integranteGrupo).enqueue(new Callback<IntegrantesGrupos>() {
            @Override
            public void onResponse(Call<IntegrantesGrupos> call, Response<IntegrantesGrupos> response) {

                if (response.isSuccessful()) {

                    IntegrantesGrupos grupo = response.body();

                    if (null != grupo.getId()) {

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<IntegrantesGrupos> call, Throwable t) {

            }
        });
    }

    private void webServiceEliminarIntegranteGrupo(IntegrantesGrupos integranteGrupo) {
        integrantesGruposRest.eliminarIntegrante(integranteGrupo).enqueue(new Callback<IntegrantesGrupos>() {
            @Override
            public void onResponse(Call<IntegrantesGrupos> call, Response<IntegrantesGrupos> response) {

                if (response.isSuccessful()) {

                    IntegrantesGrupos grupo = response.body();

                    if (null != grupo.getId()) {

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<IntegrantesGrupos> call, Throwable t) {

            }
        });
    }

    @Override
    public void setDecodeItem(DecodeItemHelper decodeItem) {
        _decodeItem = decodeItem;
    }

    @Override
    public void showQuestion(String titulo, String mensage) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle(titulo);
        ad.setMessage(mensage);
        ad.setCancelable(false);
        ad.setNegativeButton("Cancelar", this);
        ad.setPositiveButton("Aceptar", this);
        ad.show().getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.bootstrap_brand_danger));
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        {
            int operation = 0;

            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    switch (_decodeItem.getIdView()) {
                        case R.id.item_btn_eliminar_asignacion_grupo:
                            operation = Constants.WS_KEY_ELIMINAR_ASIGNACIONES_GRUPOS;
                            break;
                        case R.id.item_btn_responsable_asignacion_grupo:
                            operation = Constants.WS_KEY_AUTORIZAR_ASIGNACIONES_GRUPOS;
                            break;
                    }

                    this.webServiceOperations(operation);
            }

        }

    }

    private void webServiceOperations(int operation) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        switch (operation) {
            case Constants.WS_KEY_ELIMINAR_ASIGNACIONES_GRUPOS:
                this.webServiceDeletePromotor();
                break;
            case Constants.WS_KEY_AUTORIZAR_ASIGNACIONES_GRUPOS:
                this.webServiceAsignarPromotor();
                break;
        }

    }

    private void webServiceDeletePromotor() {
        Clientes cliente = (Clientes) _decodeItem.getItemModel();
        AsignacionGrupoFragment.asignacionesAdapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.remove(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        if (cliente.getIdEstatus().equals(Constants.ESTATUS_RESPONSABLE)) {
            FormularioGruposFragment._clienteResponsable = null;
        }

        IntegrantesGrupos integranteGrupo = new IntegrantesGrupos();
        List<IntegrantesGrupos> integrantesGrupos = AsignacionGrupoFragment.integrantesGrupos;

        for (IntegrantesGrupos integrante : integrantesGrupos) {

            if (integrante.getIdCliente().equals(cliente.getId())) {
                integranteGrupo.setId(integrante.getId());
                integranteGrupo.setIdEstatus(Constants.ACCION_ELIMINAR);
                integranteGrupo.setIdCliente(cliente.getId());
                integranteGrupo.setCliente(cliente.getNombre());
                AsignacionGrupoFragment.integrantesGrupos.add(integranteGrupo);
                break;
            }
        }

        pDialog.dismiss();
    }

    private void webServiceAsignarPromotor() {
        Clientes cliente = (Clientes) _decodeItem.getItemModel();

        AsignacionGrupoFragment.asignacionesAdapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.remove(cliente);
        cliente.setIdEstatus(Constants.ESTATUS_RESPONSABLE);
        AsignacionGrupoFragment.clientesList.add(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        FormularioGruposFragment._clienteResponsable = cliente.getId();

        pDialog.dismiss();
    }
}
