package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PrestamosGrupalesAdapter;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
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
 * Created by jvier on 05/09/2017.
 */

public class PrestamosGrupalesFragment extends Fragment implements View.OnClickListener {

    private static List<PrestamosGrupales> prestamosGrupalesList;
    private static RecyclerView recyclerView;
    private static PrestamosGrupalesAdapter adapter;
    private static NavigationDrawerInterface navigationDrawerInterface;

    /**
     * Implementaciones REST
     */
    private static PrestamosGrupalesRest prestamosGrupalesRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_grupales, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_prestamos_grupales);
        adapter = new PrestamosGrupalesAdapter();
        adapter.setOnClickListener(this);

        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();

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
        listadoGrupos();
    }

    public static void listadoGrupos() {
        prestamosGrupalesRest.getPrestamosGrupales().enqueue(new Callback<List<PrestamosGrupales>>() {
            @Override
            public void onResponse(Call<List<PrestamosGrupales>> call, Response<List<PrestamosGrupales>> response) {

                if (response.isSuccessful()) {
                    adapter = new PrestamosGrupalesAdapter();
                    prestamosGrupalesList = new ArrayList<>();
                    prestamosGrupalesList.addAll(response.body());
                    onPreRender();
                }
            }

            @Override
            public void onFailure(Call<List<PrestamosGrupales>> call, Throwable t) {

            }
        });
    }

    private static void onPreRender() {
        Collections.sort(prestamosGrupalesList, new Comparator<PrestamosGrupales>() {
            @Override
            public int compare(PrestamosGrupales o1, PrestamosGrupales o2) {
                return (o1.getGrupo().compareTo(o2.getGrupo()));
            }
        });

        adapter.addAll(prestamosGrupalesList);
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

    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        navigationDrawerInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_pagar_prestamo_grupal:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_PAGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_entregar_prestamo_grupal:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_ENTREGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_autorizar_prestamo_grupal:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_AUTORIZAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_ver_prestamo_grupal:
                navigationDrawerInterface.openExternalActivity(Constants.ACCION_VER, MainRegisterActivity.class);
                break;
        }
    }
}
