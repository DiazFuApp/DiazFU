package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.utils.Constants;


/**
 * Created by saurett on 24/02/2017.
 */

public class ListadoClientesFragment extends Fragment implements View.OnClickListener {

    private static NavigationDrawerInterface navigationDrawerInterface;
    //private static Usuarios _SESSION_USER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listado_clientes, container, false);

        return view;
    }

    @Override
    public void onStart() {
        /**Remueve fragmentos secundarios**/
        //navigationDrawerInterface.removeSecondaryFragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_clientes_container, new ClientesFragment(), Constants.FRAGMENT_CLIENTES);
        mainFragment.commit();

        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //navigationDrawerInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {


    }
}
