package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPrestamosRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.fragments.AsignacionGrupoFragment;
import com.skillcoders.diazfu.fragments.FormularioGruposFragment;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
import com.skillcoders.diazfu.helpers.PrestamosGrupalesHelper;
import com.skillcoders.diazfu.helpers.PromotoresHelper;
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
    private static DecodeItemHelper _decodeItem;
    public static ProgressDialog pDialog;

    private int item;
    private List<Pagos> _plazos;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private ReferenciasPromotoresRest referenciasPromotoresRest;
    private ClientesRest clientesRest;
    private GruposRest gruposRest;
    private IntegrantesGruposRest integrantesGruposRest;
    private PrestamosGrupalesRest prestamosGrupalesRest;
    private ReferenciasPrestamosRest referenciasPrestamosRest;
    private PagosRest pagosRest;

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
        referenciasPromotoresRest = ApiUtils.getReferenciasPromotoresRest();
        clientesRest = ApiUtils.getClientesRest();
        gruposRest = ApiUtils.getGruposRest();
        integrantesGruposRest = ApiUtils.getIntegrantesGruposRest();
        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();
        referenciasPrestamosRest = ApiUtils.getReferenciasPrestamosRest();
        pagosRest = ApiUtils.getPagosRest();

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

        referenciasPromotoresRest.agregarReferenciaPromotor(referenciaPromotor).enqueue(new Callback<ReferenciasPromotores>() {
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
    public void registrarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper) {
        pDialog = new ProgressDialog(MainRegisterActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        webServiceRegistrarPrestamoGrupal(prestamosGrupalesHelper);
    }

    private void webServiceRegistrarPrestamoGrupal(final PrestamosGrupalesHelper prestamosGrupalesHelper) {
        prestamosGrupalesRest.agregarPrestamoGrupal(prestamosGrupalesHelper.getPrestamoGrupal()).enqueue(new Callback<PrestamosGrupales>() {
            @Override
            public void onResponse(Call<PrestamosGrupales> call, Response<PrestamosGrupales> response) {

                if (response.isSuccessful()) {

                    PrestamosGrupales prestamoGrupal = response.body();

                    if (null != prestamoGrupal.getId()) {

                        List<ReferenciasPrestamos> referencias = new ArrayList<>();

                        prestamosGrupalesHelper.getAval().setIdPrestamo(prestamoGrupal.getId());
                        prestamosGrupalesHelper.getPrimeraReferencia().setIdPrestamo(prestamoGrupal.getId());
                        prestamosGrupalesHelper.getSegundaReferencia().setIdPrestamo(prestamoGrupal.getId());

                        referencias.add(prestamosGrupalesHelper.getAval());
                        referencias.add(prestamosGrupalesHelper.getPrimeraReferencia());
                        referencias.add(prestamosGrupalesHelper.getSegundaReferencia());

                        for (ReferenciasPrestamos referenciaPrestamo :
                                referencias) {
                            webServiceRegistrarReferenciaPrestamo(referenciaPrestamo);
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
            public void onFailure(Call<PrestamosGrupales> call, Throwable t) {

            }
        });
    }

    private void webServiceRegistrarReferenciaPrestamo(ReferenciasPrestamos referenciaPrestamo) {
        referenciasPrestamosRest.agregarReferenciaPrestamo(referenciaPrestamo).enqueue(new Callback<ReferenciasPrestamos>() {
            @Override
            public void onResponse(Call<ReferenciasPrestamos> call, Response<ReferenciasPrestamos> response) {

                if (response.isSuccessful()) {

                    ReferenciasPrestamos referenciasPrestamos = response.body();

                    if (null != referenciasPrestamos.getId()) {
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
        integrantesGruposRest.getIntegrantesGrupo(prestamosGrupalesHelper.getPrestamoGrupal().getIdGrupo())
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

                            Pagos data = prestamosGrupalesHelper.getPagos();
                            int plazos = Integer.valueOf(data.getPlazo());
                            int day = 6;

                            for (int i = 1; i <= plazos; i++) {
                                Pagos pago = new Pagos();
                                pago.setIdPrestamo(data.getIdPrestamo());
                                pago.setIdCliente(integrante.getIdCliente());
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
                            //PrestamosGrupales prestamosGrupales = new PrestamosGrupales();
                            //webServiceEditarPrestamoGrupal(prestamosGrupales);
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

        webServiceEntregarPrestamoGrupal(prestamosGrupalesHelper);
    }

    private void webServiceEntregarPrestamoGrupal(PrestamosGrupalesHelper prestamosGrupalesHelper) {
        prestamosGrupalesRest.editarPrestamoGrupal(prestamosGrupalesHelper.getPrestamoGrupal()).enqueue(new Callback<PrestamosGrupales>() {
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

        AsignacionGrupoFragment.adapter = new AsignacionesAdapter();
        AsignacionGrupoFragment.clientesList.remove(cliente);
        cliente.setIdEstatus(Constants.ESTATUS_RESPONSABLE);
        AsignacionGrupoFragment.clientesList.add(cliente);
        AsignacionGrupoFragment.onPreRenderListadoIntegrantes();

        FormularioGruposFragment._clienteResponsable = cliente.getId();

        pDialog.dismiss();
    }
}

