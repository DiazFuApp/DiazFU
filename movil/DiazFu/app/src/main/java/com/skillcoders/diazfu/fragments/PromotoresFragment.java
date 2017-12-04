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
import com.skillcoders.diazfu.adapters.PromotoresAdapter;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
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

public class PromotoresFragment extends Fragment implements View.OnClickListener {

    private static List<Promotores> promotoresList;
    private static RecyclerView recyclerView;
    private static PromotoresAdapter promotoresAdapter;
    private static NavigationDrawerInterface activityInterface;
    public static LinearLayout linearLayout;

    /**
     * Implementaciones REST
     */
    private static PromotoresRest promotoresRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_promotores);
        promotoresAdapter = new PromotoresAdapter();
        promotoresAdapter.setOnClickListener(this);

        promotoresRest = ApiUtils.getPromotoresRest();

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
        listadoPromotores();
    }

    public static void listadoPromotores() {
        activityInterface.showProgressDialog();
        promotoresRest.getPromotores().enqueue(new Callback<List<Promotores>>() {
            @Override
            public void onResponse(Call<List<Promotores>> call, Response<List<Promotores>> response) {

                activityInterface.stopProgressDialog();
                if (response.isSuccessful()) {
                    promotoresAdapter = new PromotoresAdapter();
                    promotoresList = new ArrayList<>();
                    promotoresList.addAll(response.body());
                    onPreRenderListadoPromotores();

                    if (promotoresList.size() == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Promotores>> call, Throwable t) {
                activityInterface.stopProgressDialog();
            }
        });
    }

    private static void onPreRenderListadoPromotores() {
        Collections.sort(promotoresList, new Comparator<Promotores>() {
            @Override
            public int compare(Promotores o1, Promotores o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        promotoresAdapter.addAll(promotoresList);
        recyclerView.setAdapter(promotoresAdapter);

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
            case R.id.item_btn_editar_promotor:
                activityInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_promotor:
                activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea elminar?");
                break;
        }
    }
}
