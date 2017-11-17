package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;


/**
 * Created by saurett on 24/02/2017.
 */

public class PreListadoProximosPagosFragment extends Fragment implements View.OnClickListener {

    private Button btnRegistrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pre_listado_proximos_pagos, container, false);

        btnRegistrar = (Button) view.findViewById(R.id.btn_buscar_proximo_pago);
        btnRegistrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.pre_listado_proximos_pagos_container, new BusquedaProximosPagosFragment(), Constants.FRAGMENT_BUSQUEDA_PROXIMOS_PAGOS);
        mainFragment.commit();

        getActivity().setTitle(getString(R.string.default_activity_title_proximos_pagos));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buscar_proximo_pago:
                this.openFragment(v);
                break;
        }
    }

    public void openFragment(View v) {
        DecodeExtraHelper extra = new DecodeExtraHelper();

        extra.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(v.getId())));
        extra.setTituloFormulario(getString(R.string.default_form_title_new));
        extra.setAccionFragmento(Constants.ACCION_VER);
        extra.setFragmentTag(Constants.ITEM_FRAGMENT.get(v.getId()));

        Intent intent = new Intent(getActivity(), MainRegisterActivity.class);
        intent.putExtra(Constants.KEY_MAIN_DECODE, extra);
        intent.putExtra(Constants.KEY_MAIN_BUSQUEDA, BusquedaProximosPagosFragment.pagosBusqueda());
        startActivity(intent);
    }
}
