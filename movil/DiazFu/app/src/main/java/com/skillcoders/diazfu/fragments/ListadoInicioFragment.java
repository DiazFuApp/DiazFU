package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 04/09/2017.
 */

public class ListadoInicioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_inicio, container, false);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_inicio_container, new ActividadesFragment(), Constants.FRAGMENT_INICIOS);
        mainFragment.commit();

        getActivity().setTitle(getString(R.string.default_item_menu_title_inicio));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
