package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.GruposHistorico;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.IntegrantesGruposHistorico;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.GruposHistoricoRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.GruposHelper;
import com.skillcoders.diazfu.utils.Constants;

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

public class AccionesGruposFragment extends Fragment implements View.OnClickListener, AlertDialog.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static MainRegisterActivity activityInterface;
    private static Button btnRegistrar;

    /**
     * Implementaciones REST
     */
    private static PrestamosGrupalesRest prestamosGrupalesRest;
    private static GruposHistoricoRest gruposHistoricoRest;
    private static PagosRest pagosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grupos_acciones_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        btnRegistrar = (Button) view.findViewById(R.id.btn_accion_grupo);
        btnRegistrar.setOnClickListener(this);

        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();
        gruposHistoricoRest = ApiUtils.getGruposHistoricoRest();
        pagosRest = ApiUtils.getPagosRest();

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
                this.onPreRenderEditar();
                break;
            case Constants.ACCION_REGISTRAR:
                break;
            default:
                break;
        }
    }

    private void onPreRenderEditar() {
        btnRegistrar.setText("EDITAR GRUPO");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (MainRegisterActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accion_grupo:
                switch (_MAIN_DECODE.getAccionFragmento()) {
                    case Constants.ACCION_EDITAR:
                        this.showQuestion();
                        break;
                    case Constants.ACCION_REGISTRAR:
                        if (FormularioGruposFragment.validarDatosRegistro())
                            registrar();
                        break;
                }
                break;
        }
    }

    private void showQuestion() {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());

        ad.setTitle(_MAIN_DECODE.getTituloActividad());
        ad.setMessage("¿Esta seguro que desea editar?");
        ad.setCancelable(false);
        ad.setNegativeButton(getString(R.string.default_alert_dialog_cancelar), this);
        ad.setPositiveButton(getString(R.string.default_alert_dialog_aceptar), this);
        ad.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if (FormularioGruposFragment.validarDatosEdicion()) validarProximosPagosGrupo();
                break;
        }
    }

    private void validarProximosPagosGrupo() {

        GruposHistorico historico = new GruposHistorico();
        historico.setIdGrupo(FormularioGruposFragment._grupoActual.getId());

        gruposHistoricoRest.getGruposCreados(historico).enqueue(new Callback<List<GruposHistorico>>() {
            @Override
            public void onResponse(Call<List<GruposHistorico>> call, Response<List<GruposHistorico>> response) {

                if (response.isSuccessful()) {

                    if (response.body().size() > 0) {
                        obtenerPrestamos(response.body().get(0).getId());
                    } else {
                        editar();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GruposHistorico>> call, Throwable t) {
            }
        });
    }

    private void obtenerPrestamos(Integer id) {
        PrestamosGrupales prestamoGrupal = new PrestamosGrupales();
        prestamoGrupal.setIdGrupoHistorico(id);

        prestamosGrupalesRest.getPrestamosGrupales(prestamoGrupal).enqueue(new Callback<List<PrestamosGrupales>>() {
            @Override
            public void onResponse(Call<List<PrestamosGrupales>> call, Response<List<PrestamosGrupales>> response) {

                if (response.body().size() > 0) {
                    obtenerPagosPendientes(response.body().get(0).getId());
                } else {
                    editar();
                }
            }

            @Override
            public void onFailure(Call<List<PrestamosGrupales>> call, Throwable t) {
            }
        });
    }

    private void obtenerPagosPendientes(Integer idPrestamo) {
        Pagos pago = new Pagos();
        pago.setIdPrestamo(idPrestamo);
        pago.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_GRUPAL);

        pagosRest.getProximosPagos(pago).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Pagos>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Pagos> pagos) {

                        if (pagos.size() > 0) {
                            Toast.makeText(getActivity(), "No es posible modificar el grupo, existen pagos pendientes.", Toast.LENGTH_SHORT).show();
                        } else {
                            editar();
                        }

                    }
                });
    }

    private void registrar() {
        this.transformIntegrantesGrupo();
        
        GruposHelper helper = new GruposHelper();
        helper.setGrupo(FormularioGruposFragment._grupoActual);
        helper.setIntegrantesGrupos(AsignacionGrupoFragment.integrantesGrupos);

        activityInterface.registrarGrupo(helper);
    }

    private void editar() {

        this.transformIntegrantesGrupo();

        GruposHelper helper = new GruposHelper();
        helper.setGrupo(FormularioGruposFragment._grupoActual);
        helper.setIntegrantesGrupos(AsignacionGrupoFragment.integrantesGrupos);

        activityInterface.editarGrupo(helper);
    }

    private void transformIntegrantesGrupo() {
        AsignacionGrupoFragment.integrantesGrupos = new ArrayList<>();

        for (IntegrantesGruposHistorico data : AsignacionGrupoFragment.integrantesGruposHistorico) {

            IntegrantesGrupos integrate = new IntegrantesGrupos();

            integrate.setId(data.getId());
            integrate.setIdCliente(data.getIdCliente());
            integrate.setIdEstatus(data.getIdEstatus());
            integrate.setIdUsuario(data.getIdUsuario());

            AsignacionGrupoFragment.integrantesGrupos.add(integrate);
        }
    }


}
