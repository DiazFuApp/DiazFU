package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 04/09/2017.
 */

public class ListadoProximosPagosFragment extends Fragment {

    private static Usuarios _SESSION_USER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_proximos_pagos, container, false);

        _SESSION_USER = (Usuarios) getActivity().getIntent().getSerializableExtra(Constants.KEY_SESSION_USER);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_proximos_pagos_container, new ProximosPagosFragment(), Constants.FRAGMENT_LISTADO_PROXIMOS_PAGOS);
        mainFragment.commit();

        getActivity().setTitle(getString(R.string.default_item_menu_title_proximos_pagos));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
