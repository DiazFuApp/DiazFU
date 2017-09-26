package com.skillcoders.diazfu.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 04/09/2017.
 */

public class ListadoPromotoresFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_promotores, container, false);
        return view;
    }

    @Override
    public void onStart() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_proveedores_container, new PromotoresFragment(), Constants.FRAGMENT_PROMOTORES);
        mainFragment.commit();

        getActivity().setTitle(getString(R.string.default_item_menu_title_promotores));

        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {

    }
}
