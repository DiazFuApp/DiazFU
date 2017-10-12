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

public class RegistroPromotoresFragment extends Fragment {

    private MainRegisterInterface activityInterface;

    private static DecodeExtraHelper _MAIN_DECODE;
    private static Usuarios _SESSION_USER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_promotores, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);
        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();

        mainFragment.replace(R.id.fragment_registro_promotor_container, new FormularioPromotoresFragment(), Constants.FORMULARIO_PROMOTORES);
        mainFragment.replace(R.id.fragment_primera_referencia_promotor_container, new FormularioReferenciaPromotoresFragment(), Constants.FORMULARIO_PROMOTORES_REFERENCIA);
        mainFragment.replace(R.id.fragment_segunda_referencia_promotor_container, new FormularioSegundaReferenciaPromotoresFragment(), Constants.FORMULARIO_PROMOTORES_SEGUNDA_REFERENCIA);
        mainFragment.replace(R.id.fragment_acciones_promotor_container, new AccionesPromotoresFragment(), Constants.FORMULARIO_PROMOTORES_ACCIONES);

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
