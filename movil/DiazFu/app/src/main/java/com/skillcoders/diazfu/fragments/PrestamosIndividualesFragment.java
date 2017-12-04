package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PrestamosIndividualesAdapter;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
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

public class PrestamosIndividualesFragment extends Fragment implements View.OnClickListener {

    private static List<PrestamosIndividuales> prestamosIndividualesList;
    private static RecyclerView recyclerView;
    private static PrestamosIndividualesAdapter adapter;
    private static NavigationDrawerInterface activityInterface;
    public static LinearLayout linearLayout;

    /**
     * Implementaciones REST
     */
    private static PrestamosIndividualesRest prestamosIndividualesRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos_individuales, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_prestamos_individuales);
        adapter = new PrestamosIndividualesAdapter();
        adapter.setOnClickListener(this);

        prestamosIndividualesRest = ApiUtils.getPrestamosIndividualesRest();

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
        listadoPrestamosIndividuales();
    }

    public static void listadoPrestamosIndividuales() {
        activityInterface.showProgressDialog();
        prestamosIndividualesRest.getPrestamosIndividuales().enqueue(new Callback<List<PrestamosIndividuales>>() {
            @Override
            public void onResponse(Call<List<PrestamosIndividuales>> call, Response<List<PrestamosIndividuales>> response) {

                activityInterface.stopProgressDialog();
                if (response.isSuccessful()) {
                    adapter = new PrestamosIndividualesAdapter();
                    prestamosIndividualesList = new ArrayList<>();
                    prestamosIndividualesList.addAll(response.body());
                    onPreRender();

                    if (prestamosIndividualesList.size() == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PrestamosIndividuales>> call, Throwable t) {
                activityInterface.stopProgressDialog();
            }
        });
    }

    private static void onPreRender() {
        Collections.sort(prestamosIndividualesList, new Comparator<PrestamosIndividuales>() {
            @Override
            public int compare(PrestamosIndividuales o1, PrestamosIndividuales o2) {
                return (o1.getCliente().compareTo(o2.getCliente()));
            }
        });

        adapter.addAll(prestamosIndividualesList);
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
            activityInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {

    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_pagar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_PAGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_entregar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_ENTREGAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_autorizar_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_AUTORIZAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_ver_prestamo_individual:
                activityInterface.openExternalActivity(Constants.ACCION_VER, MainRegisterActivity.class);
                break;
        }
    }
}
