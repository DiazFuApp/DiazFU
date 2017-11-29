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
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.model.ReferenciasPrestamos;
import com.skillcoders.diazfu.data.model.ReferenciasPromotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ActividadesRest;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.ComisionesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.data.remote.rest.RedesSocialesRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPrestamosRest;
import com.skillcoders.diazfu.data.remote.rest.ReferenciasPromotoresRest;
import com.skillcoders.diazfu.fragments.AsignacionGrupoFragment;
import com.skillcoders.diazfu.fragments.FormularioGruposFragment;
import com.skillcoders.diazfu.fragments.interfaces.DocumentosInterface;
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

public class DocumentosActivity extends AppCompatActivity implements DocumentosInterface, DialogInterface.OnClickListener {

    private static final String TAG = DocumentosActivity.class.getSimpleName();

    private DecodeExtraHelper _MAIN_DECODE;
    private static DecodeItemHelper _decodeItem;
    public static ProgressDialog pDialog;

    /**
     * Implementaciones REST
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_documentos);
        setSupportActionBar(toolbar);

        _MAIN_DECODE = (DecodeExtraHelper) getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

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
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_documentos_register_container);
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

        pDialog = new ProgressDialog(DocumentosActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
        mainFragment.replace(R.id.fragment_documentos_register_container, Constants.TAG_FRAGMENT.get(tag), tag);
        mainFragment.addToBackStack(tag);
        mainFragment.commit();

        pDialog.dismiss();
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
        pDialog = new ProgressDialog(DocumentosActivity.this);
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
        //TODO CHECAR RESPONSABLE CLIENTES
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

