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

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.PrestamosIndividualesHelper;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 03/10/2017.
 */

public class AccionesPrestamosIndividualesFragment extends Fragment implements View.OnClickListener, AlertDialog.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static MainRegisterActivity activityInterface;
    private static Button btnRegistrar;

    private int _idBtnOrigen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_individuales_acciones_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        btnRegistrar = (Button) view.findViewById(R.id.btn_accion_prestamo_individual);
        btnRegistrar.setOnClickListener(this);

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
                btnRegistrar.setText("EDITAR");
                break;
            case Constants.ACCION_VER:
                btnRegistrar.setVisibility(View.GONE);
                break;
            case Constants.ACCION_AUTORIZAR:
                btnRegistrar.setText("AUTORIZAR");
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
            case R.id.btn_accion_prestamo_individual:
                switch (_MAIN_DECODE.getAccionFragmento()) {
                    case Constants.ACCION_ENTREGAR:
                        this.showQuestion("¿Esta seguro que desea enregar?");
                        break;
                    case Constants.ACCION_AUTORIZAR:
                        this.showQuestion("¿Esta seguro que desea autorizar?");
                        break;
                    case Constants.ACCION_REGISTRAR:
                        if (FormularioPrestamosIndividualesFragment.validarDatosRegistro()
                                && FormularioAvalPrestamosIndividualesFragment.validarDatosRegistro()
                                && FormularioPrimeraReferenciaPrestamosIndividualesFragment.validarDatosRegistro()
                                && FormularioSegundaReferenciaPrestamosIndividualesFragment.validarDatosRegistro())
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
                        if (FormularioEntregaPrestamosIndividualesFragment.validarDatosEntrega())
                            entregar();
                        break;
                    case Constants.ACCION_AUTORIZAR:
                        if (FormularioAutorizacionPrestamosIndividualesFragment.validarDatosAutorizacion())
                            autorizar();
                        break;
                }
                break;
        }
    }

    private void registrar() {
        PrestamosIndividualesHelper helper = new PrestamosIndividualesHelper();
        helper.setPrestamoIndividual(FormularioPrestamosIndividualesFragment._prestamoIndividualActual);
        helper.setAval(FormularioAvalPrestamosIndividualesFragment._avalActual);
        helper.setPrimeraReferencia(FormularioPrimeraReferenciaPrestamosIndividualesFragment._referenciaActual);
        helper.setSegundaReferencia(FormularioSegundaReferenciaPrestamosIndividualesFragment._referenciaActual);

        activityInterface.registrarPrestamoIndividual(helper);
    }

    private void autorizar() {
        PrestamosIndividualesHelper helper = new PrestamosIndividualesHelper();
        helper.setPrestamoIndividual(FormularioPrestamosIndividualesFragment._prestamoIndividualActual);
        helper.setPagos(FormularioAutorizacionPrestamosIndividualesFragment._pagosActual);

        activityInterface.autorizarPrestamoIndividual(helper);
    }

    private void entregar() {
        PrestamosIndividualesHelper helper = new PrestamosIndividualesHelper();
        helper.setPrestamoIndividual(FormularioPrestamosIndividualesFragment._prestamoIndividualActual);

        activityInterface.entregarPrestamoIndividual(helper);
    }


}
