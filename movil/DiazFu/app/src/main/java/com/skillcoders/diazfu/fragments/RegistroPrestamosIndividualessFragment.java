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

public class RegistroPrestamosIndividualessFragment extends Fragment {

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    public static FrameLayout frameAutorizacion, frameEntrega;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_prestamos_individuales, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        frameAutorizacion = (FrameLayout) view.findViewById(R.id.fragment_autorizacion_prestamos_individuales_container);
        frameEntrega = (FrameLayout) view.findViewById(R.id.fragment_entrega_prestamos_individuales_container);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();

        mainFragment.replace(R.id.fragment_registro_prestamos_individuales_container, new FormularioPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES);
        //mainFragment.replace(R.id.fragment_asignaciones_grupo_container, new AsignacionesGruposFragment(), Constants.FORMULARIO_GRUPOS_ASIGNACIONES);
        mainFragment.replace(R.id.fragment_aval_prestamos_individuales_container, new FormularioAvalPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_AVAL);
        mainFragment.replace(R.id.fragment_primera_referencia_prestamos_individuales_container, new FormularioPrimeraReferenciaPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_PRIMER_REFERENCIA);
        mainFragment.replace(R.id.fragment_segunda_referencia_prestamos_individuales_container, new FormularioSegundaReferenciaPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_SEGUNDA_REFERENCIA);
        mainFragment.replace(R.id.fragment_autorizacion_prestamos_individuales_container, new FormularioAutorizacionPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_AUTORIZACION);
        mainFragment.replace(R.id.fragment_entrega_prestamos_individuales_container, new FormularioEntregaPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_ENTREGA);
        mainFragment.replace(R.id.fragment_acciones_prestamos_individuales_container, new AccionesPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_ACCIONES);

        mainFragment.commit();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
