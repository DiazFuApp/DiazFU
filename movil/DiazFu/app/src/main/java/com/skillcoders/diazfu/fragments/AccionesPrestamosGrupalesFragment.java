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
import android.widget.ScrollView;
import android.widget.Toast;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.GruposHistorico;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.RedesSociales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.GruposHistoricoRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.PrestamosGrupalesHelper;
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

public class AccionesPrestamosGrupalesFragment extends Fragment implements View.OnClickListener, AlertDialog.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static MainRegisterActivity activityInterface;
    private static Button btnRegistrar;

    private int _idBtnOrigen;

    /**
     * Implementaciones REST
     */
    private static PrestamosGrupalesRest prestamosGrupalesRest;
    private static GruposHistoricoRest gruposHistoricoRest;
    private static PagosRest pagosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_grupales_acciones_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        btnRegistrar = (Button) view.findViewById(R.id.btn_accion_prestamo_grupal);
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
            case Constants.ACCION_VER:
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_ENTREGAR:
                this.onPreRenderEditar();
                break;
            case Constants.ACCION_REGISTRAR:
                break;
            default:
                break;
        }
    }

    private void onPreRenderEditar() {

        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                btnRegistrar.setText("EDITAR GRUPO");
                break;
            case Constants.ACCION_VER:
                btnRegistrar.setVisibility(View.GONE);
                break;
            case Constants.ACCION_AUTORIZAR:
                btnRegistrar.setText("AUTORIZAR GRUPO");
                break;
            case Constants.ACCION_ENTREGAR:
                btnRegistrar.setText("ENTREGAR PRESTAMO");
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
            activityInterface = (MainRegisterActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        _idBtnOrigen = _MAIN_DECODE.getAccionFragmento();
        switch (v.getId()) {
            case R.id.btn_accion_prestamo_grupal:
                switch (_MAIN_DECODE.getAccionFragmento()) {
                    case Constants.ACCION_ENTREGAR:
                        this.showQuestion("¿Esta seguro que desea entregar?");
                        break;
                    case Constants.ACCION_AUTORIZAR:
                        this.showQuestion("¿Esta seguro que desea autorizar?");
                        break;
                    case Constants.ACCION_REGISTRAR:
                        if (FormularioPrestamosGrupalesFragment.validarDatosRegistro()
                                && FormularioAvalPrestamosGrupalesFragment.validarDatosRegistro()
                                && FormularioPrimeraReferenciaPrestamosGrupalesFragment.validarDatosRegistro()
                                && FormularioSegundaReferenciaPrestamosGrupalesFragment.validarDatosRegistro())
                            registrar();
                        break;
                }
                break;
        }
    }

    private void showQuestion(String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());

        ad.setTitle(_MAIN_DECODE.getTituloActividad());
        ad.setMessage(message);
        ad.setCancelable(false);
        ad.setNegativeButton(getString(R.string.default_alert_dialog_cancelar), this);
        ad.setPositiveButton(getString(R.string.default_alert_dialog_aceptar), this);
        ad.show();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                switch (_idBtnOrigen) {
                    case Constants.ACCION_ENTREGAR:
                        if (FormularioEntregaPrestamosGrupalesFragment.validarDatosEntrega())
                            validarProximosPagosGrupo();
                        break;
                    case Constants.ACCION_AUTORIZAR:
                        if (FormularioAutorizacionPrestamosGrupalesFragment.validarDatosAutorizacionIntegrantes())
                            autorizar();
                        break;
                }
                break;
        }
    }

    private void validarProximosPagosGrupo() {
        PrestamosGrupales prestamoGrupal = new PrestamosGrupales();
        prestamoGrupal.setIdGrupoHistorico(FormularioPrestamosGrupalesFragment._prestamoGrupalActual.getIdGrupoHistorico());

        prestamosGrupalesRest.getPrestamosGrupales(prestamoGrupal).enqueue(new Callback<List<PrestamosGrupales>>() {
            @Override
            public void onResponse(Call<List<PrestamosGrupales>> call, Response<List<PrestamosGrupales>> response) {

                if (response.body().size() > 0) {
                    obtenerPagosPendientes(response.body().get(0).getId());
                } else {
                    entregar();
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
                            Toast.makeText(getActivity(), "No es posible entregar el prestamo, existen pagos pendientes.", Toast.LENGTH_SHORT).show();
                        } else {
                            entregar();
                        }

                    }
                });
    }

    private void registrar() {
        PrestamosGrupalesHelper helper = new PrestamosGrupalesHelper();
        helper.setPrestamoGrupal(FormularioPrestamosGrupalesFragment._prestamoGrupalActual);
        helper.setAval(FormularioAvalPrestamosGrupalesFragment._avalActual);
        helper.setPrimeraReferencia(FormularioPrimeraReferenciaPrestamosGrupalesFragment._referenciaActual);
        helper.setSegundaReferencia(FormularioSegundaReferenciaPrestamosGrupalesFragment._referenciaActual);
        helper.setRedesSociales(new ArrayList<RedesSociales>());
        helper.getRedesSociales().addAll(FormularioAvalPrestamosGrupalesFragment._redesSocialesActuales);
        helper.getRedesSociales().addAll(FormularioPrimeraReferenciaPrestamosGrupalesFragment._redesSocialesActuales);
        helper.getRedesSociales().addAll(FormularioSegundaReferenciaPrestamosGrupalesFragment._redesSocialesActuales);

        activityInterface.registrarPrestamoGrupal(helper);
    }

    private void autorizar() {
        PrestamosGrupalesHelper helper = new PrestamosGrupalesHelper();
        helper.setPrestamoGrupal(FormularioPrestamosGrupalesFragment._prestamoGrupalActual);
        helper.setPagosProgramados(FormularioAutorizacionPrestamosGrupalesFragment._pagosProgramadosActual);

        activityInterface.autorizarPrestamoGrupal(helper);
    }

    private void entregar() {
        PrestamosGrupalesHelper helper = new PrestamosGrupalesHelper();
        helper.setPrestamoGrupal(FormularioPrestamosGrupalesFragment._prestamoGrupalActual);

        activityInterface.entregarPrestamoGrupal(helper);
    }


}
