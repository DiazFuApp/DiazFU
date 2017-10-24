package com.skillcoders.diazfu.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;

/**
 * Created by jvier on 04/09/2017.
 */

public class ListadoPromotoresFragment extends Fragment implements View.OnClickListener {

    private static Usuarios _SESSION_USER;
    private Button btnRegistrar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_promotores, container, false);

        _SESSION_USER = (Usuarios) getActivity().getIntent().getSerializableExtra(Constants.KEY_SESSION_USER);

        btnRegistrar = (Button) view.findViewById(R.id.btn_registrar_promotor);
        btnRegistrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_proveedores_container, new PromotoresFragment(), Constants.FRAGMENT_PROMOTORES);
        mainFragment.commit();

        getActivity().setTitle(getString(R.string.default_item_menu_title_promotores));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registrar_promotor:
                DecodeExtraHelper extra = new DecodeExtraHelper();

                extra.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(v.getId())));
                extra.setTituloFormulario(getString(R.string.default_form_title_new));
                extra.setAccionFragmento(Constants.ACCION_REGISTRAR);
                extra.setFragmentTag(Constants.ITEM_FRAGMENT.get(v.getId()));

                Intent intent = new Intent(getActivity(), MainRegisterActivity.class);
                intent.putExtra(Constants.KEY_MAIN_DECODE, extra);
                intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
                startActivity(intent);
                break;
        }
    }
}
