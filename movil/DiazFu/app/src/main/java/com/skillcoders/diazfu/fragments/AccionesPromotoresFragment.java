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
import com.skillcoders.diazfu.helpers.PromotoresHelper;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 03/10/2017.
 */

public class AccionesPromotoresFragment extends Fragment implements View.OnClickListener, AlertDialog.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static MainRegisterActivity activityInterface;
    private static Button btnRegistrar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores_acciones_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        btnRegistrar = (Button) view.findViewById(R.id.btn_accion_promotor);
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
                this.onPreRenderEditar();
                break;
            case Constants.ACCION_REGISTRAR:
                break;
            default:
                break;
        }
    }

    private void onPreRenderEditar() {
        btnRegistrar.setText("EDITAR PROMOTOR");
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
            case R.id.btn_accion_promotor:
                switch (_MAIN_DECODE.getAccionFragmento()) {
                    case Constants.ACCION_EDITAR:
                        this.showQuestion();
                        break;
                    case Constants.ACCION_REGISTRAR:
                        if (FormularioPromotoresFragment.validarDatosRegistro()
                                && FormularioPromotoresCredencialesFragment.validarDatosRegistro()
                                && FormularioReferenciaPromotoresFragment.validarDatosRegistro()
                                && FormularioSegundaReferenciaPromotoresFragment.validarDatosRegistro())
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
                if (FormularioPromotoresFragment.validarDatosEdicion()
                        && FormularioPromotoresCredencialesFragment.validarDatosEdicion()
                        && FormularioReferenciaPromotoresFragment.validarDatosEdicion()
                        && FormularioSegundaReferenciaPromotoresFragment.validarDatosEdicion())
                    editar();
                break;
        }
    }

    private void registrar() {
        PromotoresHelper helper = new PromotoresHelper();
        helper.setPromotor(FormularioPromotoresFragment._promotorActual);
        helper.setUsuario(FormularioPromotoresCredencialesFragment._usuariosActual);
        helper.setPrimeraReferencia(FormularioReferenciaPromotoresFragment._referenciaUnoActual);
        helper.setSegundaReferencia(FormularioSegundaReferenciaPromotoresFragment._referenciaDosActual);
        helper.setRedesSocialesPromotor(FormularioPromotoresFragment._redesSocialesActuales);
        helper.setRedesSocialesPrimerReferencia(FormularioReferenciaPromotoresFragment._redesSocialesActuales);
        helper.setRedesSocialesSegundaReferencia(FormularioSegundaReferenciaPromotoresFragment._redesSocialesActuales);

        activityInterface.registrarPromotor(helper);
    }

    private void editar() {
        PromotoresHelper helper = new PromotoresHelper();
        helper.setPromotor(FormularioPromotoresFragment._promotorActual);
        helper.setUsuario(FormularioPromotoresCredencialesFragment._usuariosActual);
        helper.setPrimeraReferencia(FormularioReferenciaPromotoresFragment._referenciaUnoActual);
        helper.setSegundaReferencia(FormularioSegundaReferenciaPromotoresFragment._referenciaDosActual);
        helper.setRedesSocialesPromotor(FormularioPromotoresFragment._redesSocialesActuales);
        helper.setRedesSocialesPrimerReferencia(FormularioReferenciaPromotoresFragment._redesSocialesActuales);
        helper.setRedesSocialesSegundaReferencia(FormularioSegundaReferenciaPromotoresFragment._redesSocialesActuales);

        activityInterface.editarPromotor(helper);
    }
}
