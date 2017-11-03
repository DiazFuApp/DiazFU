package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;


/**
 * Created by saurett on 24/02/2017.
 */

public class RegistroPrestamosGrupalesFragment extends Fragment {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    public static FrameLayout frameAutorizacion, frameEntrega;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_prestamos_grupales, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        frameAutorizacion = (FrameLayout) view.findViewById(R.id.fragment_autorizacion_prestamos_grupales_container);
        frameEntrega = (FrameLayout) view.findViewById(R.id.fragment_entrega_prestamos_grupales_container);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();

        mainFragment.replace(R.id.fragment_registro_prestamos_grupales_container, new FormularioPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES);
        //mainFragment.replace(R.id.fragment_asignaciones_grupo_container, new AsignacionesGruposFragment(), Constants.FORMULARIO_GRUPOS_ASIGNACIONES);
        mainFragment.replace(R.id.fragment_aval_prestamos_grupales_container, new FormularioAvalPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_AVAL);
        mainFragment.replace(R.id.fragment_primera_referencia_prestamos_grupales_container, new FormularioPrimeraReferenciaPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_PRIMER_REFERENCIA);
        mainFragment.replace(R.id.fragment_segunda_referencia_prestamos_grupales_container, new FormularioSegundaReferenciaPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_SEGUNDA_REFERENCIA);
        mainFragment.replace(R.id.fragment_autorizacion_prestamos_grupales_container, new FormularioAutorizacionPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_AUTORIZACION);
        mainFragment.replace(R.id.fragment_entrega_prestamos_grupales_container, new FormularioEntregaPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_ENTREGA);
        mainFragment.replace(R.id.fragment_acciones_prestamos_grupales_container, new AccionesPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_ACCIONES);

        mainFragment.commit();

        this.onPreRender();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (MainRegisterInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    public void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
                break;
            case Constants.ACCION_REGISTRAR:
                break;
            default:
                break;
        }
    }
}
