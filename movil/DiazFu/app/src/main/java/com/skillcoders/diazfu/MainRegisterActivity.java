package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.skillcoders.diazfu.adapters.AsignacionesAdapter;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Comisiones;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.GruposHistorico;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.IntegrantesGruposHistorico;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ActividadesRest;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.ComisionesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposHistoricoRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposHistoricoRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.RedesSocialesRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPrestamosRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.UsuariosRest;
import com.skillcoders.diazfu.fragments.AsignacionGrupoFragment;
import com.skillcoders.diazfu.fragments.FormularioGruposFragment;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.ActividadesHelper;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.ComisionesHelper;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
import com.skillcoders.diazfu.helpers.PagosHelper;
import com.skillcoders.diazfu.helpers.PrestamosGrupalesHelper;
import com.skillcoders.diazfu.helpers.PrestamosIndividualesHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;
import com.skillcoders.diazfu.services.SharedPreferencesService;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 04/09/2017.
 */

public class MainRegisterActivity extends AppCompatActivity implements MainRegisterInterface, DialogInterface.OnClickListener {

    private static final String TAG = MainRegisterActivity.class.getSimpleName();

    private DecodeExtraHelper _MAIN_DECODE;
    private Usuarios _SESSION_USER;
    private static DecodeItemHelper _decodeItem;
    public static ProgressDialog pDialog;

    private int item;
    private List<Pagos> _plazos;
    private List<RedesSociales> _redesSociales;
    private List<ReferenciasPrestamos> _referencias;
    private Double _montoGeneral;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private ReferenciasPromotoresRest referenciasPromotoresRest;
    private ClientesRest clientesRest;
    private GruposRest gruposRest;
    private GruposHistoricoRest gruposHistoricoRest;
    private IntegrantesGruposRest integrantesGruposRest;
    private IntegrantesGruposHistoricoRest integrantesGruposHistoricoRest;
    private PrestamosGrupalesRest prestamosGrupalesRest;
    private PrestamosIndividualesRest prestamosIndividualesRest;
    private ReferenciasPrestamosRest referenciasPrestamosRest;
    private PagosRest pagosRest;
    private ActividadesRest actividadesRest;
    private ComisionesRest comisionesRest;
    private RedesSocialesRest redesSocialesRest;
    private UsuariosRest usuariosRest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main_register);
        setSupportActionBar(toolbar);

        _MAIN_DECODE = (DecodeExtraHelper) getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getApplicationContext());

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        promotoresRest = ApiUtils.getPromotoresRest();
        referenciasPromotoresRest = ApiUtils.getReferenciasPromotoresRest();
        clientesRest = ApiUtils.getClientesRest();
        gruposRest = ApiUtils.getGruposRest();
        gruposHistoricoRest = ApiUtils.getGruposHistoricoRest();
        integrantesGruposRest = ApiUtils.getIntegrantesGruposRest();
        integrantesGruposHistoricoRest = ApiUtils.getIntegrantesGruposHistoricoRest();
        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();
        prestamosIndividualesRest = ApiUtils.getPrestamosIndividualesRest();
        referenciasPrestamosRest = ApiUtils.getReferenciasPrestamosRest();
        pagosRest = ApiUtils.getPagosRest();
        actividadesRest = ApiUtils.getActividadesRest();
        comisionesRest = ApiUtils.getComisionesRest();
        redesSocialesRest = ApiUtils.getRedesSocialesRest();
        usuariosRest = ApiUtils.getUsuariosRest();

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

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main_register_container);
        if (null != fragment.getFragmentManager().getFragments()) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void onPreRender() {
        openFragment(_MAIN_DECODE.getFragmentTag());
    }

    /**
     * Abre el fragmento mediante el tag seleccionado
     **/
    private void openFragment(String tag) {

        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
        mainFragment.replace(R.id.fragment_main_register_container, Constants.TAG_FRAGMENT.get(tag), tag);
        mainFragment.addToBackStack(tag);
        mainFragment.commit();

        pDialog.dismiss();
    }

    @Override
    public void registrarPromotor(PromotoresHelper promotoresHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPromotores(promotoresHelper);
    }

    private void webServiceRegistrarPromotores(final PromotoresHelper promotoresHelper) {

        promotoresRest.agregarPromotor(promotoresHelper.getPromotor()).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {

                    Promotores data = response.body();

                    if (null != data.getId()) {

                        promotoresHelper.getPromotor().setId(data.getId());
                        promotoresHelper.getUsuario().setIdActor(data.getId());

                        webServiceRegistrarUsuario(promotoresHelper);
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

    private void webServiceRegistrarUsuario(final PromotoresHelper promotoresHelper) {

        usuariosRest.agregarUusuario(promotoresHelper.getUsuario()).enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                if (response.isSuccessful()) {

                    Usuarios data = response.body();

                    if (null != data.getId()) {
                        webServiceContenidoPromotor(promotoresHelper);
                    } else {
                        pDialog.dismiss();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceContenidoPromotor(PromotoresHelper promotoresHelper) {
        _redesSociales = promotoresHelper.getRedesSocialesPromotor();
        Promotores promotor = promotoresHelper.getPromotor();

        for (RedesSociales redSocial :
                _redesSociales) {

            switch (redSocial.getIdTipoActor()) {
                case Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR:
                    redSocial.setIdActor(promotor.getId());
                    break;
            }
        }

        promotoresHelper.getPrimeraReferencia().setIdActor(promotor.getId());
        promotoresHelper.getSegundaReferencia().setIdActor(promotor.getId());

        List<ReferenciasPromotores> referencias = new ArrayList<>();

        referencias.add(promotoresHelper.getPrimeraReferencia());
        referencias.add(promotoresHelper.getSegundaReferencia());

        _redesSociales.addAll(promotoresHelper.getRedesSocialesPrimerReferencia());
        _redesSociales.addAll(promotoresHelper.getRedesSocialesSegundaReferencia());

        for (ReferenciasPromotores referenciasPromotor : referencias) {
            webServiceRegistrarRefererenciaPromotor(referenciasPromotor);
        }

        item = 0;
        webServiceRegistrarRedesSociales();

    }

    private void webServiceRegistrarRefererenciaPromotor(ReferenciasPromotores referenciaPromotor) {

        referenciasPromotoresRest.agregarReferenciaPromotor(referenciaPromotor).enqueue(new Callback<ReferenciasPromotores>() {
            @Override
            public void onResponse(Call<ReferenciasPromotores> call, Response<ReferenciasPromotores> response) {

                if (response.isSuccessful()) {

                    ReferenciasPromotores referenciaPromotor = response.body();

                    if (null != referenciaPromotor.getId()) {
                        Integer id = referenciaPromotor.getId();
                        Integer tempItem = 0;

                        for (RedesSociales redSocial :
                                _redesSociales) {

                            if (tempItem >= 3) break;

                            switch (redSocial.getIdTipoActor()) {
                                case Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR:
                                    if (null == redSocial.getIdActor()) {
                                        redSocial.setIdActor(id);
                                        tempItem++;
                                        break;
                                    }
                            }
                        }
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
                        webServiceUpdateUsuario(promotoresHelper);
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

    private void webServiceUpdateUsuario(final PromotoresHelper promotoresHelper) {

        usuariosRest.editarUsuario(promotoresHelper.getUsuario()).enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {

                if (response.isSuccessful()) {

                    Usuarios data = response.body();

                    if (null != data.getId()) {
                        webServiceEditarContenidoPromotor(promotoresHelper);
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceEditarContenidoPromotor(PromotoresHelper promotoresHelper) {
        List<ReferenciasPromotores> referencias = new ArrayList<>();

        referencias.add(promotoresHelper.getPrimeraReferencia());
        referencias.add(promotoresHelper.getSegundaReferencia());

        for (ReferenciasPromotores referenciasPromotor : referencias) {
            webServiceEditarRefererenciaPromotor(referenciasPromotor);
        }

        item = 0;
        _redesSociales = promotoresHelper.getRedesSocialesPromotor();
        _redesSociales.addAll(promotoresHelper.getRedesSocialesPrimerReferencia());
        _redesSociales.addAll(promotoresHelper.getRedesSocialesSegundaReferencia());
        webServiceEditarRedesSociales();
    }

    private void webServiceEditarRefererenciaPromotor(ReferenciasPromotores referenciaPromotor) {

        referenciasPromotoresRest.editarReferenciaPromotor(referenciaPromotor).enqueue(new Callback<ReferenciasPromotores>() {
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

    private void webServiceRegistrarClientes(final ClientesHelper clientesHelper) {
        clientesRest.agregarCliente(clientesHelper.getCliente()).enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {

                if (response.isSuccessful()) {

                    Clientes cliente = response.body();
                    item = 0;

                    if (null != cliente.getId()) {
                        Integer id = cliente.getId();
                        _redesSociales = clientesHelper.getRedesSociales();

                        for (RedesSociales redSocial :
                                _redesSociales) {

                            switch (redSocial.getIdTipoActor()) {
                                case Constants.DIAZFU_WEB_TIPO_ACTOR_CLIENTE:
                                    redSocial.setIdActor(id);
                                    break;
                            }
                        }

                        webServiceRegistrarRedesSociales();
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

    private void webServiceRegistrarRedesSociales() {
        redesSocialesRest.agregarRedSocial(_redesSociales.get(item)).enqueue(new Callback<RedesSociales>() {
            @Override
            public void onResponse(Call<RedesSociales> call, Response<RedesSociales> response) {

                if (response.isSuccessful()) {

                    RedesSociales data = response.body();
                    item++;

                    if (null != data.getId()) {
                        if (item < _redesSociales.size()) {
                            webServiceRegistrarRedesSociales();
                        } else {
                            finish();
                            pDialog.dismiss();
                        }
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<RedesSociales> call, Throwable t) {

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

    private void webServiceEditarClientes(final ClientesHelper clientesHelper) {
        clientesRest.editarCliente(clientesHelper.getCliente()).enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {

                if (response.isSuccessful()) {

                    Clientes cliente = response.body();

                    if (null != cliente.getId()) {

                        item = 0;
                        _redesSociales = clientesHelper.getRedesSociales();
                        webServiceEditarRedesSociales();
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

    private void webServiceEditarRedesSociales() {
        redesSocialesRest.editarRedSocial(_redesSociales.get(item)).enqueue(new Callback<RedesSociales>() {
            @Override
            public void onResponse(Call<RedesSociales> call, Response<RedesSociales> response) {

                if (response.isSuccessful()) {

                    RedesSociales data = response.body();
                    item++;

                    if (null != data.getId()) {
                        if (item < _redesSociales.size()) {
                            webServiceEditarRedesSociales();
                        } else {
                            finish();
                            pDialog.dismiss();
                        }
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<RedesSociales> call, Throwable t) {

            }
        });
    }

    @Override
    public void registrarActividad(ActividadesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarActividad(helper);

    }

    private void webServiceRegistrarActividad(ActividadesHelper helper) {
        actividadesRest.agregarActividad(helper.getActividades()).enqueue(new Callback<Actividades>() {
            @Override
            public void onResponse(Call<Actividades> call, Response<Actividades> response) {

                if (response.isSuccessful()) {

                    Actividades data = response.body();

                    if (null != data.getId()) {

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
            public void onFailure(Call<Actividades> call, Throwable t) {

            }
        });
    }

    @Override
    public void editarActividad(ActividadesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEditarActividad(helper);
    }

    private void webServiceEditarActividad(ActividadesHelper helper) {
        actividadesRest.editarActividad(helper.getActividades()).enqueue(new Callback<Actividades>() {
            @Override
            public void onResponse(Call<Actividades> call, Response<Actividades> response) {

                if (response.isSuccessful()) {

                    Actividades data = response.body();

                    if (null != data.getId()) {

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
            public void onFailure(Call<Actividades> call, Throwable t) {

            }
        });
    }


    @Override
    public void registrarComision(ComisionesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarComision(helper);
    }

    private void webServiceRegistrarComision(ComisionesHelper helper) {
        comisionesRest.agregarComision(helper.getComisiones()).enqueue(new Callback<Comisiones>() {
            @Override
            public void onResponse(Call<Comisiones> call, Response<Comisiones> response) {

                if (response.isSuccessful()) {

                    Comisiones data = response.body();

                    if (null != data.getId()) {

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
            public void onFailure(Call<Comisiones> call, Throwable t) {

            }
        });

    }

    @Override
    public void pagarComision(ComisionesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEditarComision(helper);
    }

    private void webServiceEditarComision(ComisionesHelper helper) {
        comisionesRest.editarComision(helper.getComisiones()).enqueue(new Callback<Comisiones>() {
            @Override
            public void onResponse(Call<Comisiones> call, Response<Comisiones> response) {

                if (response.isSuccessful()) {

                    Comisiones data = response.body();

                    if (null != data.getId()) {

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
            public void onFailure(Call<Comisiones> call, Throwable t) {

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

    private void webServiceRegistrarGrupo(final GruposHelper gruposHelper) {
        gruposRest.agregarGrupo(gruposHelper.getGrupo()).enqueue(new Callback<Grupos>() {
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

                        gruposHelper.setGrupo(grupo);
                        webServiceRegistrarGrupoHistorico(gruposHelper);

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

    private void webServiceRegistrarGrupoHistorico(final GruposHelper gruposHelper) {
        GruposHistorico historico = new GruposHistorico();
        final Grupos grupo = gruposHelper.getGrupo();

        historico.setIdClienteResponsable(grupo.getIdClienteResponsable());
        historico.setIdGrupo(grupo.getId());
        historico.setIdPromotor(grupo.getIdPromotor());
        historico.setIdEstatus(grupo.getIdEstatus());
        historico.setNombre(grupo.getNombre());
        historico.setIdUsuario(grupo.getIdUsuario());

        gruposHistoricoRest.agregarGrupo(historico).enqueue(new Callback<GruposHistorico>() {
            @Override
            public void onResponse(Call<GruposHistorico> call, Response<GruposHistorico> response) {

                if (response.isSuccessful()) {

                    GruposHistorico data = response.body();

                    if (null != data.getId()) {
                        List<IntegrantesGrupos> integrantesGrupos = gruposHelper.getIntegrantesGrupos();

                        for (IntegrantesGrupos integranteGrupo :
                                integrantesGrupos) {
                            integranteGrupo.setIdGrupo(data.getIdGrupo());

                            switch (integranteGrupo.getIdEstatus()) {
                                case Constants.ACCION_REGISTRAR:
                                    webServiceAgregarIntegranteGrupoHistorico(integranteGrupo);
                                    break;
                                case Constants.ACCION_ELIMINAR:
                                    webServiceEliminarIntegranteGrupoHistorico(integranteGrupo);
                                    break;
                            }
                        }

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<GruposHistorico> call, Throwable t) {

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

                        gruposHelper.setGrupo(grupo);
                        webServiceRegistrarGrupoHistorico(gruposHelper);

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

    private void webServiceAgregarIntegranteGrupoHistorico(IntegrantesGrupos integranteGrupo) {
        IntegrantesGruposHistorico historico = new IntegrantesGruposHistorico();
        historico.setIdCliente(integranteGrupo.getIdCliente());
        historico.setIdGrupoHistorico(integranteGrupo.getIdGrupo());
        historico.setIdEstatus(integranteGrupo.getIdEstatus());


        integrantesGruposHistoricoRest.agregarIntegrante(historico).enqueue(new Callback<IntegrantesGruposHistorico>() {
            @Override
            public void onResponse(Call<IntegrantesGruposHistorico> call, Response<IntegrantesGruposHistorico> response) {

                if (response.isSuccessful()) {

                    IntegrantesGruposHistorico grupo = response.body();

                    if (null != grupo.getId()) {

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<IntegrantesGruposHistorico> call, Throwable t) {

            }
        });
    }

    private void webServiceEliminarIntegranteGrupoHistorico(IntegrantesGrupos integranteGrupo) {
        IntegrantesGruposHistorico historico = new IntegrantesGruposHistorico();
        historico.setId(integranteGrupo.getId());
        historico.setIdCliente(integranteGrupo.getIdCliente());
        historico.setIdGrupoHistorico(integranteGrupo.getIdGrupo());
        historico.setIdEstatus(integranteGrupo.getIdEstatus());

        integrantesGruposHistoricoRest.eliminarIntegrante(historico).enqueue(new Callback<IntegrantesGruposHistorico>() {
            @Override
            public void onResponse(Call<IntegrantesGruposHistorico> call, Response<IntegrantesGruposHistorico> response) {

                if (response.isSuccessful()) {

                    IntegrantesGruposHistorico grupo = response.body();

                    if (null != grupo.getId()) {
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<IntegrantesGruposHistorico> call, Throwable t) {

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
    public void registrarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPrestamoGrupal(prestamosGrupalesHelper);
    }

    private void webServiceRegistrarPrestamoGrupal(final PrestamosGrupalesHelper helper) {
        prestamosGrupalesRest.agregarPrestamoGrupal(helper.getPrestamoGrupal()).enqueue(new Callback<PrestamosGrupales>() {
            @Override
            public void onResponse(Call<PrestamosGrupales> call, Response<PrestamosGrupales> response) {

                if (response.isSuccessful()) {

                    PrestamosGrupales prestamoGrupal = response.body();

                    if (null != prestamoGrupal.getId()) {

                        _referencias = new ArrayList<>();
                        _redesSociales = helper.getRedesSociales();

                        helper.getAval().setIdPrestamo(prestamoGrupal.getId());
                        helper.getPrimeraReferencia().setIdPrestamo(prestamoGrupal.getId());
                        helper.getSegundaReferencia().setIdPrestamo(prestamoGrupal.getId());

                        _referencias.add(helper.getAval());
                        _referencias.add(helper.getPrimeraReferencia());
                        _referencias.add(helper.getSegundaReferencia());

                        //Se usa primero para registrar referencias
                        item = 0;
                        webServiceRegistrarReferenciaPrestamoGrupal(_referencias.get(0));
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<PrestamosGrupales> call, Throwable t) {

            }
        });
    }

    private void webServiceRegistrarReferenciaPrestamoGrupal(final ReferenciasPrestamos referenciaPrestamo) {
        referenciasPrestamosRest.agregarReferenciaPrestamo(referenciaPrestamo).enqueue(new Callback<ReferenciasPrestamos>() {
            @Override
            public void onResponse(Call<ReferenciasPrestamos> call, Response<ReferenciasPrestamos> response) {

                if (response.isSuccessful()) {

                    ReferenciasPrestamos data = response.body();

                    if (null != data.getId()) {
                        Integer id = data.getId();
                        Integer tempItem = 0;

                        for (RedesSociales redSocial :
                                _redesSociales) {

                            if (tempItem >= 3) break;

                            switch (redSocial.getIdTipoActor()) {
                                case Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO:
                                    if (null == redSocial.getIdActor()) {
                                        redSocial.setIdActor(id);
                                        tempItem++;
                                        break;
                                    }
                            }
                        }

                        item++;
                        /**Al completar las referencias se usa para las redes sociales*/
                        if (item == 3) {
                            item = 0;
                            webServiceRegistrarRedesSociales();
                        } else {
                            webServiceRegistrarReferenciaPrestamoGrupal(_referencias.get(item));
                        }
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReferenciasPrestamos> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void autorizarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceAutorizarrPrestamoGrupal(prestamosGrupalesHelper);
    }

    private void webServiceAutorizarrPrestamoGrupal(final PrestamosGrupalesHelper prestamosGrupalesHelper) {
        integrantesGruposRest.getIntegrantesGrupo(prestamosGrupalesHelper.getPrestamoGrupal().getIdGrupoHistorico())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<IntegrantesGrupos>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<IntegrantesGrupos> integrantesGruposes) {

                        _plazos = new ArrayList<>();
                        item = 0;

                        for (IntegrantesGrupos integrante :
                                integrantesGruposes) {

                            Pagos proximoPago = new Pagos();

                            List<Pagos> data = prestamosGrupalesHelper.getPagosProgramados();

                            for (Pagos pago : data) {
                                if (pago.getIdCliente().equals(integrante.getIdCliente())) {
                                    proximoPago = pago;
                                    break;
                                }
                            }


                            int plazos = Integer.valueOf(proximoPago.getPlazo());
                            int day = 6;

                            for (int i = 1; i <= plazos; i++) {
                                Pagos pago = new Pagos();
                                pago.setIdPrestamo(proximoPago.getIdPrestamo());
                                pago.setIdCliente(integrante.getIdCliente());
                                pago.setIdTipoPrestamo(proximoPago.getIdTipoPrestamo());
                                pago.setMontoAPagar(proximoPago.getMontoAPagar());
                                pago.setMontoPagado(0.0);
                                pago.setPlazo(i + "/" + plazos);
                                pago.setTipoPago("");
                                pago.setFechaProgramada(DateTimeUtils.addDay(day));
                                pago.setFechaPago(pago.getFechaPago());
                                pago.setMorosidad(0.0);
                                pago.setDescripcion("");
                                pago.setIdEstatus(Constants.DIAZFU_WEB_PENDIENTE);
                                pago.setIdUsuario(proximoPago.getIdUsuario());

                                day = day + 7;
                                _plazos.add(pago);
                            }
                        }

                        webServiceAgregarPlazos();

                        Double cantidad = prestamosGrupalesHelper.getPrestamoGrupal().getCantidadOtorgada();
                        Double porcentaje = (0.10 * cantidad);
                        prestamosGrupalesHelper.getPrestamoGrupal().setIdEstatus(Constants.DIAZFU_WEB_AUTORIZADO);
                        prestamosGrupalesHelper.getPrestamoGrupal().setAnticipo(porcentaje);

                        webServiceEditarPrestamoGrupal(prestamosGrupalesHelper.getPrestamoGrupal());
                    }
                });
    }

    private void webServiceAgregarPlazos() {
        pagosRest.agregarPago(_plazos.get(item)).enqueue(new Callback<Pagos>() {
            @Override
            public void onResponse(Call<Pagos> call, Response<Pagos> response) {

                if (response.isSuccessful()) {

                    Pagos referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {
                        item++;
                        webServiceAgregarPlazo(_plazos.get(item));
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pagos> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceAgregarPlazo(Pagos pago) {
        pagosRest.agregarPago(pago).enqueue(new Callback<Pagos>() {
            @Override
            public void onResponse(Call<Pagos> call, Response<Pagos> response) {

                if (response.isSuccessful()) {

                    Pagos referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {
                        item++;
                        if (item < _plazos.size()) {
                            webServiceAgregarPlazo(_plazos.get(item));
                        } else {
                            finish();
                            pDialog.dismiss();
                        }
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pagos> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceEditarPrestamoGrupal(PrestamosGrupales prestamoGrupal) {
        prestamosGrupalesRest.editarPrestamoGrupal(prestamoGrupal).enqueue(new Callback<PrestamosGrupales>() {
            @Override
            public void onResponse(Call<PrestamosGrupales> call, Response<PrestamosGrupales> response) {

                if (response.isSuccessful()) {

                    PrestamosGrupales referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {

                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrestamosGrupales> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void entregarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEntregarPrestamoGrupal(prestamosGrupalesHelper.getPrestamoGrupal());
    }

    private void webServiceEntregarPrestamoGrupal(PrestamosGrupales prestamosGrupales) {
        prestamosGrupalesRest.editarPrestamoGrupal(prestamosGrupales).enqueue(new Callback<PrestamosGrupales>() {
            @Override
            public void onResponse(Call<PrestamosGrupales> call, Response<PrestamosGrupales> response) {

                if (response.isSuccessful()) {

                    PrestamosGrupales referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {

                    }

                    finish();
                    pDialog.dismiss();

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrestamosGrupales> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceEntregarPrestamosIndividuales(PrestamosIndividuales prestamosIndividuales) {
        prestamosIndividualesRest.editarPrestamoIndividual(prestamosIndividuales).enqueue(new Callback<PrestamosIndividuales>() {
            @Override
            public void onResponse(Call<PrestamosIndividuales> call, Response<PrestamosIndividuales> response) {

                if (response.isSuccessful()) {

                    PrestamosIndividuales prestamoIndividual = response.body();

                    if (null != prestamoIndividual.getId()) {

                    }

                    finish();
                    pDialog.dismiss();

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrestamosIndividuales> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void registrarPagoGrupal(PagosHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPagoGrupal(helper);
    }

    @Override
    public void registrarPagoIndividual(PagosHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPagoIndividual(helper);
    }

    @Override
    public void registrarPrestamoIndividual(PrestamosIndividualesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPrestamoIndividual(helper);
    }

    private void webServiceRegistrarPrestamoIndividual(final PrestamosIndividualesHelper helper) {
        prestamosIndividualesRest.agregarPrestamoIndividual(helper.getPrestamoIndividual())
                .enqueue(new Callback<PrestamosIndividuales>() {
                    @Override
                    public void onResponse(Call<PrestamosIndividuales> call, Response<PrestamosIndividuales> response) {

                        if (response.isSuccessful()) {

                            PrestamosIndividuales prestamoIndividual = response.body();

                            if (null != prestamoIndividual.getId()) {

                                _referencias = new ArrayList<>();
                                _redesSociales = helper.getRedesSociales();

                                helper.getAval().setIdPrestamo(prestamoIndividual.getId());
                                helper.getPrimeraReferencia().setIdPrestamo(prestamoIndividual.getId());
                                helper.getSegundaReferencia().setIdPrestamo(prestamoIndividual.getId());

                                _referencias.add(helper.getAval());
                                _referencias.add(helper.getPrimeraReferencia());
                                _referencias.add(helper.getSegundaReferencia());

                                //Se usa primero para registrar referencias
                                item = 0;
                                webServiceRegistrarReferenciaPrestamoIndividual(_referencias.get(0));
                            }

                            Log.i(TAG, "post submitted to API." + response.body().toString());
                        } else {
                            int statusCode = response.code();
                            Log.e(TAG, "CODIGO: " + statusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<PrestamosIndividuales> call, Throwable t) {

                    }
                });
    }

    private void webServiceRegistrarReferenciaPrestamoIndividual(ReferenciasPrestamos referenciasPrestamos) {
        referenciasPrestamosRest.agregarReferenciaPrestamo(referenciasPrestamos).enqueue(new Callback<ReferenciasPrestamos>() {
            @Override
            public void onResponse(Call<ReferenciasPrestamos> call, Response<ReferenciasPrestamos> response) {

                if (response.isSuccessful()) {

                    ReferenciasPrestamos data = response.body();

                    if (null != data.getId()) {
                        Integer id = data.getId();
                        Integer tempItem = 0;

                        for (RedesSociales redSocial :
                                _redesSociales) {

                            if (tempItem >= 3) break;

                            switch (redSocial.getIdTipoActor()) {
                                case Constants.DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO:
                                    if (null == redSocial.getIdActor()) {
                                        redSocial.setIdActor(id);
                                        tempItem++;
                                        break;
                                    }
                            }
                        }

                        item++;
                        /**Al completar las referencias se usa para las redes sociales*/
                        if (item == 3) {
                            item = 0;
                            webServiceRegistrarRedesSociales();
                        } else {
                            webServiceRegistrarReferenciaPrestamoIndividual(_referencias.get(item));
                        }
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReferenciasPrestamos> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void autorizarPrestamoIndividual(PrestamosIndividualesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceAutorizarrPrestamoIndividual(helper);
    }

    private void webServiceAutorizarrPrestamoIndividual(final PrestamosIndividualesHelper helper) {
        clientesRest.getCliente(helper.getPrestamoIndividual().getIdCliente())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Clientes>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Clientes cliente) {

                        _plazos = new ArrayList<>();
                        item = 0;

                        Pagos data = helper.getPagos();
                        int plazos = Integer.valueOf(data.getPlazo());
                        int day = 6;

                        for (int i = 1; i <= plazos; i++) {
                            Pagos pago = new Pagos();
                            pago.setIdPrestamo(data.getIdPrestamo());
                            pago.setIdCliente(cliente.getId());
                            pago.setIdTipoPrestamo(data.getIdTipoPrestamo());
                            pago.setMontoAPagar(data.getMontoAPagar());
                            pago.setMontoPagado(0.0);
                            pago.setPlazo(i + "/" + plazos);
                            pago.setTipoPago("");
                            pago.setFechaProgramada(DateTimeUtils.addDay(day));
                            pago.setFechaPago(pago.getFechaPago());
                            pago.setMorosidad(0.0);
                            pago.setDescripcion("");
                            pago.setIdEstatus(Constants.DIAZFU_WEB_PENDIENTE);
                            pago.setIdUsuario(pago.getIdUsuario());

                            day = day + 7;
                            _plazos.add(pago);
                        }

                        webServiceAgregarPlazos();

                        Double cantidad = helper.getPrestamoIndividual().getCantidadOtorgada();
                        Double porcentaje = (0.10 * cantidad);
                        helper.getPrestamoIndividual().setIdEstatus(Constants.DIAZFU_WEB_AUTORIZADO);
                        helper.getPrestamoIndividual().setAnticipo(porcentaje);

                        webServiceEditarPrestamoIndividual(helper.getPrestamoIndividual());
                    }
                });
    }

    private void webServiceEditarPrestamoIndividual(PrestamosIndividuales prestamoIndividual) {
        prestamosIndividualesRest.editarPrestamoIndividual(prestamoIndividual)
                .enqueue(new Callback<PrestamosIndividuales>() {
                    @Override
                    public void onResponse(Call<PrestamosIndividuales> call, Response<PrestamosIndividuales> response) {

                        if (response.isSuccessful()) {

                            PrestamosIndividuales prestamosIndividuales = response.body();

                            if (null != prestamosIndividuales.getId()) {

                            }

                            Log.i(TAG, "post submitted to API." + response.body().toString());
                        } else {
                            int statusCode = response.code();
                            Log.e(TAG, "CODIGO: " + statusCode);
                            Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PrestamosIndividuales> call, Throwable t) {
                        Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void entregarPrestamoIndividual(PrestamosIndividualesHelper helper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceEntregarPrestamoIndividual(helper.getPrestamoIndividual());
    }

    private void webServiceEntregarPrestamoIndividual(PrestamosIndividuales prestamoIndividual) {
        prestamosIndividualesRest.editarPrestamoIndividual(prestamoIndividual).enqueue(new Callback<PrestamosIndividuales>() {
            @Override
            public void onResponse(Call<PrestamosIndividuales> call, Response<PrestamosIndividuales> response) {

                if (response.isSuccessful()) {

                    PrestamosIndividuales referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {

                    }

                    finish();
                    pDialog.dismiss();

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrestamosIndividuales> call, Throwable t) {
                Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webServiceRegistrarPagoGrupal(final PagosHelper helper) {
        _montoGeneral = helper.getPago().getMontoAPagar();
        Pagos pago = new Pagos();
        Double montoPagado = 0.0;

        if (item < helper.getPagos().size()) {
            pago = helper.getPagos().get(item);

            Double montoAPagar = (pago.getMontoPagado() > 0) ? pago.getMontoAPagar() - pago.getMontoPagado() : pago.getMontoAPagar();
            montoPagado = (helper.getPago().getMontoAPagar().compareTo(montoAPagar) >= 0)
                    ? montoAPagar : helper.getPago().getMontoAPagar();

            pago.setFechaPago(DateTimeUtils.getActualTime());
            pago.setTipoPago(helper.getPago().getTipoPago());
            pago.setIdEstatus((helper.getPago().getMontoAPagar().compareTo(montoAPagar) >= 0)
                    ? Constants.DIAZFU_WEB_PAGADO : Constants.DIAZFU_WEB_PENDIENTE);
            pago.setMontoPagado(pago.getMontoPagado() + montoPagado);
            helper.getPago().setMontoAPagar(helper.getPago().getMontoAPagar() - montoPagado);

            if (helper.getPago().getPlazo().equals(pago.getPlazo()) && (helper.getPago().getMontoAPagar() > 0)) {
                helper.getPago().setMontoAPagar(helper.getPago().getMontoAPagar() + montoPagado);
                montoPagado = helper.getPago().getMontoAPagar();
                pago.setMontoPagado(montoPagado);
            }
        }

        if (montoPagado > 0) {
            pagosRest.editarPago(pago).enqueue(new Callback<Pagos>() {
                @Override
                public void onResponse(Call<Pagos> call, Response<Pagos> response) {

                    if (response.isSuccessful()) {
                        item++;
                        Pagos data = response.body();

                        if (null != data.getId()) {
                            if (helper.getPago().getPlazo().equals(data.getPlazo())
                                    && data.getMontoPagado().compareTo(data.getMontoAPagar()) >= 0) {
                                helper.getPrestamosGrupales().setIdEstatus(Constants.DIAZFU_WEB_PAGADO);
                                webServiceEntregarPrestamoGrupal(helper.getPrestamosGrupales());
                            } else {
                                webServiceRegistrarPagoGrupal(helper);
                            }
                        } else {
                            finish();
                            pDialog.dismiss();
                        }

                        Log.i(TAG, "post submitted to API." + response.body().toString());
                    } else {
                        int statusCode = response.code();
                        Log.e(TAG, "CODIGO: " + statusCode);
                        Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();

                        finish();
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Pagos> call, Throwable t) {
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            finish();
            pDialog.dismiss();
        }
    }

    private void webServiceRegistrarPagoIndividual(final PagosHelper helper) {
        _montoGeneral = helper.getPago().getMontoAPagar();
        Pagos pago = new Pagos();
        Double montoPagado = 0.0;

        if (item < helper.getPagos().size()) {
            pago = helper.getPagos().get(item);

            Double montoAPagar = (pago.getMontoPagado() > 0) ? pago.getMontoAPagar() - pago.getMontoPagado() : pago.getMontoAPagar();
            montoPagado = (helper.getPago().getMontoAPagar().compareTo(montoAPagar) >= 0)
                    ? montoAPagar : helper.getPago().getMontoAPagar();

            pago.setFechaPago(DateTimeUtils.getActualTime());
            pago.setTipoPago(helper.getPago().getTipoPago());
            pago.setIdEstatus((helper.getPago().getMontoAPagar().compareTo(montoAPagar) >= 0)
                    ? Constants.DIAZFU_WEB_PAGADO : Constants.DIAZFU_WEB_PENDIENTE);
            pago.setMontoPagado(pago.getMontoPagado() + montoPagado);
            helper.getPago().setMontoAPagar(helper.getPago().getMontoAPagar() - montoPagado);

            if (helper.getPago().getPlazo().equals(pago.getPlazo()) && (helper.getPago().getMontoAPagar() > 0)) {
                helper.getPago().setMontoAPagar(helper.getPago().getMontoAPagar() + montoPagado);
                montoPagado = helper.getPago().getMontoAPagar();
                pago.setMontoPagado(montoPagado);
            }
        }

        if (montoPagado > 0) {
            pagosRest.editarPago(pago).enqueue(new Callback<Pagos>() {
                @Override
                public void onResponse(Call<Pagos> call, Response<Pagos> response) {

                    if (response.isSuccessful()) {
                        item++;
                        Pagos data = response.body();

                        if (null != data.getId()) {
                            if (helper.getPago().getPlazo().equals(data.getPlazo())
                                    && data.getMontoPagado().compareTo(data.getMontoAPagar()) >= 0) {
                                helper.getPrestamosIndividuales().setIdEstatus(Constants.DIAZFU_WEB_PAGADO);
                                webServiceEntregarPrestamosIndividuales(helper.getPrestamosIndividuales());
                            } else {
                                webServiceRegistrarPagoIndividual(helper);
                            }
                        } else {
                            finish();
                            pDialog.dismiss();
                        }

                        Log.i(TAG, "post submitted to API." + response.body().toString());
                    } else {
                        int statusCode = response.code();
                        Log.e(TAG, "CODIGO: " + statusCode);
                        Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, codigo " + statusCode, Toast.LENGTH_SHORT).show();

                        finish();
                        pDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Pagos> call, Throwable t) {
                    Toast.makeText(MainRegisterActivity.this, "Se ha presentado un error, intente más tarde ...", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            finish();
            pDialog.dismiss();
        }

    }

    @Override
    public void setDecodeItem(DecodeItemHelper decodeItem) {
        _decodeItem = decodeItem;
    }

    @Override
    public void openExternalActivity(int action, Class<?> externalActivity) {
        DecodeExtraHelper extraParams = new DecodeExtraHelper();

        extraParams.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(_decodeItem.getIdView())));
        extraParams.setTituloFormulario(getString(Constants.TITLE_FORM_ACTION.get(action)));
        extraParams.setAccionFragmento(action);
        extraParams.setFragmentTag(Constants.ITEM_FRAGMENT.get(_decodeItem.getIdView()));
        extraParams.setDecodeItem(_decodeItem);

        Intent intent = new Intent(this, externalActivity);
        intent.putExtra(Constants.KEY_MAIN_DECODE, extraParams);
        //intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
        startActivity(intent);
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
        AsignacionGrupoFragment.adapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.remove(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        if (cliente.getIdEstatus().equals(Constants.ESTATUS_RESPONSABLE)) {
            FormularioGruposFragment._clienteResponsable = null;
        }

        IntegrantesGruposHistorico integranteGrupo = new IntegrantesGruposHistorico();
        List<IntegrantesGruposHistorico> integrantesGrupos = AsignacionGrupoFragment.integrantesGruposHistorico;

        for (IntegrantesGruposHistorico integrante : integrantesGrupos) {

            if (integrante.getIdCliente().equals(cliente.getId())) {
                integranteGrupo.setId(integrante.getId());
                integranteGrupo.setIdEstatus(Constants.ACCION_ELIMINAR);
                integranteGrupo.setIdCliente(cliente.getId());
                integranteGrupo.setCliente(cliente.getNombre());
                integrante.setIdUsuario(_SESSION_USER.getId());
                AsignacionGrupoFragment.integrantesGruposHistorico.add(integranteGrupo);
                break;
            }
        }

        pDialog.dismiss();
    }

    private void webServiceAsignarPromotor() {
        Clientes cliente = (Clientes) _decodeItem.getItemModel();

        AsignacionGrupoFragment.adapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.remove(cliente);

        for (Clientes integrante : AsignacionGrupoFragment.clientesList) {
            if (integrante.getIdEstatus().equals(Constants.ESTATUS_RESPONSABLE)) {
                integrante.setIdEstatus(Constants.ESTATUS_NO_RESPONSABLE);
            }
        }

        cliente.setIdEstatus(Constants.ESTATUS_RESPONSABLE);
        AsignacionGrupoFragment.clientesList.add(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        FormularioGruposFragment._clienteResponsable = cliente.getId();

        pDialog.dismiss();
    }

    @Override
    public void showProgressDialog() {
        if (null == pDialog) {
            pDialog = new ProgressDialog(MainRegisterActivity.this);
            pDialog.setMessage(getString(R.string.default_loading_msg));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @Override
    public void stopProgressDialog() {
        if (null != pDialog) pDialog.dismiss();
    }
}

