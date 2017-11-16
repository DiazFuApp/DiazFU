package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.ComisionesAdapter;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Comisiones;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ComisionesRest;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by saurett on 24/02/2017.
 */

public class ComisionesFragment extends Fragment implements View.OnClickListener {

    private static List<Comisiones> comisionesList;
    private static RecyclerView recyclerView;
    private static ComisionesAdapter adapter;
    private static NavigationDrawerInterface navigationDrawerInterface;
    private View view;


    /**
     * Implementaciones REST
     */
    private static ComisionesRest comisionesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_comisiones, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_comisiones);
        adapter = new ComisionesAdapter();
        adapter.setOnClickListener(this);

        comisionesRest = ApiUtils.getComisionesRest();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        listadoActividades();
    }

    public static void listadoActividades() {
        comisionesRest.getComisiones().enqueue(new Callback<List<Comisiones>>() {
            @Override
            public void onResponse(Call<List<Comisiones>> call, Response<List<Comisiones>> response) {

                if (response.isSuccessful()) {
                    adapter = new ComisionesAdapter();
                    comisionesList = new ArrayList<>();
                    comisionesList.addAll(response.body());
                    onPreRenderActividades();
                }
            }

            @Override
            public void onFailure(Call<List<Comisiones>> call, Throwable t) {

            }
        });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private static void onPreRenderActividades() {

        Collections.sort(comisionesList, new Comparator<Comisiones>() {
            @Override
            public int compare(Comisiones o1, Comisiones o2) {
                return (o1.getPromotor().compareTo(o2.getPromotor()));
            }
        });

        adapter.addAll(comisionesList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            navigationDrawerInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        navigationDrawerInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_pagar_comision:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_PAGAR, MainRegisterActivity.class);
                break;
        }
    }
}
